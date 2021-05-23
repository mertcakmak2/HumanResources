package com.company.hrms.Business.Concretes;

import com.company.hrms.Business.Abstracts.JobSeekerService;
import com.company.hrms.Core.IdentityVerificationService;
import com.company.hrms.Core.Util.Util;
import com.company.hrms.DataAccess.Abstracts.JobSeekerDao;
import com.company.hrms.DataAccess.Abstracts.UserDao;
import com.company.hrms.Entities.Concretes.JobSeeker;
import com.company.hrms.Entities.Concretes.User;
import com.company.hrms.Entities.Dto.JobSeeker.JobSeekerRegisterDto;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.xml.bind.ValidationException;
import java.util.List;

@Service
@AllArgsConstructor
public class JobSeekerManager implements JobSeekerService {

    private final JobSeekerDao jobSeekerDao;
    private final UserDao userDao;
    @Qualifier(value = "MernisIdendityAdapter")
    private final IdentityVerificationService mernisVerificationAdapter;
    private final ModelMapper mapper;

    @Override
    public JobSeeker saveJobSeeker(JobSeekerRegisterDto jobSeekerRegisterDto) throws Exception {
        JobSeeker jobSeeker = mapper.map(jobSeekerRegisterDto, JobSeeker.class);
        validateJobSeeker(jobSeekerRegisterDto);

        User user = userDao.save(new User(jobSeeker.getEmail(), jobSeekerRegisterDto.getPassword()));
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

    public boolean validateJobSeeker(JobSeekerRegisterDto jobSeekerRegisterDto) throws Exception {
        // Mernis Verification
        if(!mernisVerificationAdapter.verificationUser(jobSeekerRegisterDto)){
            throw new UsernameNotFoundException("Mernis kimlik bilgilerini doğrulayamadı");
        }
        // Email Regex
        if(!Util.checkUserEmail(jobSeekerRegisterDto.getEmail())){
            throw new IllegalStateException("Lütfen geçerli bir email adresi giriniz");
        }
        // Field controls
        if(jobSeekerRegisterDto.getFirstName().isEmpty() || jobSeekerRegisterDto.getLastName().isEmpty() || jobSeekerRegisterDto.getNationalityId() == null
                || jobSeekerRegisterDto.getBirthDate() == null || jobSeekerRegisterDto.getPassword() == null ){
            throw new ValidationException("Tüm alanları doldurduğunuzdan emin olunuz.");
        }
        // Email and Nationality Number exist record control
        if(jobSeekerDao.findByNationalityIdOrEmail(jobSeekerRegisterDto.getNationalityId(), jobSeekerRegisterDto.getEmail()) != null){
            throw new EntityExistsException("Bu email yada kimlik numarası kullanılmakta.");
        }
        return true;
    }
}
