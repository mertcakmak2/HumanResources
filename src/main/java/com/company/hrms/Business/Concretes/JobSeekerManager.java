package com.company.hrms.Business.Concretes;

import com.company.hrms.Business.Abstracts.JobSeekerService;
import com.company.hrms.Core.IdentityVerificationService;
import com.company.hrms.Core.Util.Util;
import com.company.hrms.DataAccess.Abstracts.JobSeekerDao;
import com.company.hrms.DataAccess.Abstracts.UserDao;
import com.company.hrms.Entities.Concretes.JobSeeker;
import com.company.hrms.Entities.Concretes.User;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sun.security.validator.ValidatorException;

import javax.persistence.EntityExistsException;
import javax.xml.bind.ValidationException;
import java.util.List;

@Service
@AllArgsConstructor
public class JobSeekerManager implements JobSeekerService {

    private final JobSeekerDao jobSeekerDao;
    private final UserDao userDao;
    @Qualifier(value = "MernisIdendityAdapter")
    private final IdentityVerificationService identityVerificationService;

    @Override
    public JobSeeker saveJobSeeker(JobSeeker jobSeeker) throws Exception {

        validateJobSeeker(jobSeeker);

        User user = userDao.save(new User(jobSeeker.getEmail(), jobSeeker.getPassword()));
        jobSeeker.setUser(user);

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

    @Override
    public boolean validateJobSeeker(JobSeeker jobSeeker) throws Exception {
        // Mernis Verification
        if(!identityVerificationService.verificationUser(jobSeeker)){
            throw new UsernameNotFoundException("Mernis kimlik bilgilerini doğrulayamadı");
        }
        // Email Regex
        if(!Util.checkUserEmail(jobSeeker.getEmail())){
            throw new IllegalStateException("Lütfen geçerli bir email adresi giriniz");
        }
        // Field controls
        if(jobSeeker.getFirstName().isEmpty() || jobSeeker.getLastName().isEmpty() || jobSeeker.getNationalityId() == null
                || jobSeeker.getBirthDate() == null || jobSeeker.getPassword() == null ){
            throw new ValidationException("Tüm alanları doldurduğunuzdan emin olunuz.");
        }
        // Email and Nationality Number exist record control
        if(jobSeekerDao.findByNationalityIdOrEmail(jobSeeker.getNationalityId(), jobSeeker.getEmail()) != null){
            throw new EntityExistsException("Bu email yada kimlik numarası kullanılmakta.");
        }
        return true;
    }
}
