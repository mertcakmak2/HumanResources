package com.company.hrms.Business.Concretes;

import com.company.hrms.Business.Abstracts.JobSeekerService;
import com.company.hrms.Core.IdentityVerificationService;
import com.company.hrms.Core.ProfilePictureService;
import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Core.Utilities.Result.SuccessDataResult;
import com.company.hrms.Core.Utilities.Util;
import com.company.hrms.DataAccess.Abstracts.JobSeekerDao;
import com.company.hrms.DataAccess.Abstracts.ProfilePictureDao;
import com.company.hrms.Entities.Concretes.JobSeeker;
import com.company.hrms.Entities.Concretes.ProfilePicture;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityExistsException;
import javax.xml.bind.ValidationException;
import java.util.List;

@Service
@AllArgsConstructor
public class JobSeekerManager implements JobSeekerService {

    private final JobSeekerDao jobSeekerDao;
    @Qualifier(value = "MernisIdendityAdapter")
    private final IdentityVerificationService mernisVerificationAdapter;
    @Qualifier(value = "CloudinaryAdapter")
    private final ProfilePictureService cloudinaryAdapter;
    private final ProfilePictureDao profilePictureDao;

    @Override
    public JobSeeker saveJobSeeker(JobSeeker jobSeeker) throws Exception {

        validateJobSeeker(jobSeeker);

        return jobSeekerDao.save(jobSeeker);
    }

    @Override
    public JobSeeker findJobSeekerById(int id) throws NotFoundException {
        return jobSeekerDao.findById(id).orElseThrow(() -> new NotFoundException("İş arayan bulunamadı."));
    }

    @Override
    public DataResult<List<JobSeeker>> findAllJobSeekers() {
        return new SuccessDataResult<List<JobSeeker>>(jobSeekerDao.findAll(), "İş Arayanlar Listelendi");
    }

    @Override
    public JobSeeker deleteJobSeeker(JobSeeker jobSeeker) {
        jobSeekerDao.delete(jobSeeker);
        return jobSeeker;
    }

    @Override
    public DataResult<JobSeeker> saveJobSeekerProfilePic(MultipartFile picture, int jobSeekerId) throws NotFoundException {
        String profilePicturePath = cloudinaryAdapter.saveProfilePicture(picture);
        ProfilePicture savedProfilePicture = profilePictureDao.save(new ProfilePicture(profilePicturePath));

        JobSeeker jobSeeker = findJobSeekerById(jobSeekerId);
        jobSeeker.setProfilePicture(savedProfilePicture);

        return new SuccessDataResult<JobSeeker>(jobSeekerDao.save(jobSeeker),"Profil resmi eklendi.");
    }

    public void validateJobSeeker(JobSeeker jobSeeker) throws Exception {
        // Mernis Verification
        if (!mernisVerificationAdapter.verificationUser(jobSeeker)) {
            throw new EntityExistsException("Mernis kimlik bilgilerini doğrulayamadı");
        }
        // Email Regex
        if (!Util.checkUserEmail(jobSeeker.getEmail())) {
            throw new ValidationException("Lütfen geçerli bir email adresi giriniz");
        }
        // Email and Nationality Number exist record control
        if (jobSeekerDao.findByNationalityIdOrEmail(jobSeeker.getNationalityId(), jobSeeker.getEmail()) != null) {
            throw new EntityExistsException("Bu email yada kimlik numarası kullanılmakta.");
        }
    }
}
