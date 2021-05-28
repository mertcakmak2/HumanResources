package com.company.hrms.Business.Concretes;

import com.company.hrms.Business.Abstracts.JobSeekerService;
import com.company.hrms.Business.Abstracts.ConfirmTokenService;
import com.company.hrms.Business.Abstracts.RegisterJobSeekerService;
import com.company.hrms.Business.Abstracts.UserService;
import com.company.hrms.Core.Email.Abstracts.MailService;
import com.company.hrms.DataAccess.Abstracts.ConfirmationJobSeekerDao;
import com.company.hrms.Entities.Concretes.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterJobSeekerManager implements RegisterJobSeekerService {

    private final UserService userService;
    private final JobSeekerService jobSeekerService;
    private final ConfirmTokenService confirmTokenService;
    private final ConfirmationJobSeekerDao confirmationJobSeekerDao;
    @Qualifier(value = "MailManager")
    private final MailService mailManager;

    @Override
    public JobSeeker registerJobSeeker(JobSeeker jobSeeker) throws Exception {

        JobSeeker savedJobSeeker = jobSeekerService.saveJobSeeker(jobSeeker);
        RegisterConfirmToken registerConfirmToken = confirmTokenService.saveMailConfirmToken(savedJobSeeker);

        ConfirmationJobSeeker confirmationJobSeeker = new ConfirmationJobSeeker(registerConfirmToken, savedJobSeeker);
        confirmationJobSeekerDao.save(confirmationJobSeeker);

        // Todo: Send Confirmation Mail
        // Todo: mailLink => http://localhost:5002/api/register/job-seeker/confirm?token=894b3e96-7a9b-40aa-8a25-b039f74e27c2

        return savedJobSeeker;
    }

    @Override
    public String confirmJobSeekerTokenWithEmail(String token) {
        RegisterConfirmToken confirmedToken = confirmTokenService.confirmMailToken(token);
        userService.confirmUser(confirmedToken.getUser());
        return confirmedToken.getUser().getEmail()+" has been successfully confirmed with email.";
    }

}
