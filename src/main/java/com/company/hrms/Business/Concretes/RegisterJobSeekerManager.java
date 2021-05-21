package com.company.hrms.Business.Concretes;

import com.company.hrms.Business.Abstracts.JobSeekerService;
import com.company.hrms.Business.Abstracts.RegisterJobSeekerService;
import com.company.hrms.DataAccess.Abstracts.ConfirmationJobSeekerDao;
import com.company.hrms.DataAccess.Abstracts.RegisterConfirmTokenDao;
import com.company.hrms.DataAccess.Abstracts.UserDao;
import com.company.hrms.Entities.Concretes.ConfirmationJobSeeker;
import com.company.hrms.Entities.Concretes.JobSeeker;
import com.company.hrms.Entities.Concretes.RegisterConfirmToken;
import com.company.hrms.Entities.Concretes.User;
import com.company.hrms.Entities.Dto.JobSeekerDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegisterJobSeekerManager implements RegisterJobSeekerService {

    private final JobSeekerService jobSeekerService;
    private final RegisterConfirmTokenDao registerConfirmTokenDao;
    private final ConfirmationJobSeekerDao confirmationJobSeekerDao;
    private final ModelMapper mapper;
    private final UserDao userDao;

    @Override
    public JobSeekerDto registerJobSeeker(JobSeeker jobSeeker) throws Exception {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        jobSeeker.setPassword(bCryptPasswordEncoder.encode(jobSeeker.getPassword()));

        JobSeeker savedJobSeeker = jobSeekerService.saveJobSeeker(jobSeeker);
        RegisterConfirmToken registerConfirmToken = registerConfirmTokenDao.save(new RegisterConfirmToken(
                UUID.randomUUID().toString(),
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(30L),
                savedJobSeeker.getUser()
        ));
        ConfirmationJobSeeker confirmationJobSeeker = new ConfirmationJobSeeker(registerConfirmToken, savedJobSeeker);
        confirmationJobSeekerDao.save(confirmationJobSeeker);

        // Todo: Send Confirmation Mail
        // Todo: mailLink => http://localhost:5002/api/register/job-seeker/confirm?token=894b3e96-7a9b-40aa-8a25-b039f74e27c2

        return mapper.map(savedJobSeeker, JobSeekerDto.class);
    }

    @Override
    public String confirmWithEmail(String token) {

        RegisterConfirmToken confirmationToken = registerConfirmTokenDao.findByToken(token)
                .orElseThrow(() -> new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        User user = confirmationToken.getUser();
        user.setEnabled(true);

        confirmationToken.setConfirmedAt(LocalDateTime.now());

        registerConfirmTokenDao.save(confirmationToken);
        userDao.save(user);

        return user.getEmail()+" has been successfully confirmed with email.";
    }
}
