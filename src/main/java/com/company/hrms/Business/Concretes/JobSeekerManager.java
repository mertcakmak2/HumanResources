package com.company.hrms.Business.Concretes;

import com.company.hrms.Business.Abstracts.JobSeekerService;
import com.company.hrms.Core.IdentityVerificationService;
import com.company.hrms.Core.Util.Util;
import com.company.hrms.DataAccess.Abstracts.JobSeekerDao;
import com.company.hrms.Entities.Concretes.JobSeeker;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.security.auth.login.AccountNotFoundException;
import javax.xml.bind.ValidationException;
import java.util.List;

@Service
@AllArgsConstructor
public class JobSeekerManager implements JobSeekerService {

    private final JobSeekerDao jobSeekerDao;
    @Qualifier(value = "MernisIdendityAdapter")
    private final IdentityVerificationService mernisVerificationAdapter;

    @Override
    public JobSeeker saveJobSeeker(JobSeeker jobSeeker) throws Exception {

        validateJobSeeker(jobSeeker);

        return jobSeekerDao.save(jobSeeker);
    }

    @Override
    public JobSeeker findJobSeekerById(int id) throws NotFoundException {
        return jobSeekerDao.findById(id).orElseThrow(() -> new NotFoundException("not found job seeker"));
    }

    @Override
    public List<JobSeeker> findAllJobSeekers() {
        return jobSeekerDao.findAll();
    }

    @Override
    public JobSeeker deleteJobSeeker(JobSeeker jobSeeker) {
        jobSeekerDao.delete(jobSeeker);
        return jobSeeker;
    }

    public boolean validateJobSeeker(JobSeeker jobSeeker) throws Exception {
        // Mernis Verification
        if (!mernisVerificationAdapter.verificationUser(jobSeeker)) {
            throw new EntityExistsException("Mernis kimlik bilgilerini doğrulayamadı");
        }
        // Email Regex
        if (!Util.checkUserEmail(jobSeeker.getEmail())) {
            throw new IllegalStateException("Lütfen geçerli bir email adresi giriniz");
        }
        // Field controls
        if (jobSeeker.getFirstName().isEmpty() || jobSeeker.getLastName().isEmpty() || jobSeeker.getNationalityId() == null
                || jobSeeker.getBirthDate() == null || jobSeeker.getPassword() == null) {
            throw new ValidationException("Tüm alanları doldurduğunuzdan emin olunuz.");
        }
        // Email and Nationality Number exist record control
        if (jobSeekerDao.findByNationalityIdOrEmail(jobSeeker.getNationalityId(), jobSeeker.getEmail()) != null) {
            throw new EntityExistsException("Bu email yada kimlik numarası kullanılmakta.");
        }
        return true;
    }
}
