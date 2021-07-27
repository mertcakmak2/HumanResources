package com.company.hrms.Business.Concretes;

import com.company.hrms.Business.Abstracts.JobSeekerService;
import com.company.hrms.Business.Abstracts.ConfirmTokenService;
import com.company.hrms.Business.Abstracts.RegisterJobSeekerService;
import com.company.hrms.Business.Abstracts.UserService;
import com.company.hrms.DataAccess.Abstracts.ConfirmationJobSeekerDao;
import com.company.hrms.Entities.Concretes.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterJobSeekerManager implements RegisterJobSeekerService {

    private final UserService userService;
    private final JobSeekerService jobSeekerService;
    private final ConfirmTokenService confirmTokenService;
    private final ConfirmationJobSeekerDao confirmationJobSeekerDao;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ObjectMapper mapper;

    @Override
    public JobSeeker registerJobSeeker(JobSeeker jobSeeker) throws Exception {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        jobSeeker.setPassword(bCryptPasswordEncoder.encode(jobSeeker.getPassword()));

        JobSeeker savedJobSeeker = jobSeekerService.saveJobSeeker(jobSeeker);
        RegisterConfirmToken registerConfirmToken = confirmTokenService.saveMailConfirmToken(savedJobSeeker);

        ConfirmationJobSeeker confirmationJobSeeker = new ConfirmationJobSeeker(registerConfirmToken, savedJobSeeker);
        confirmationJobSeekerDao.save(confirmationJobSeeker);

        // Todo: Send Confirmation Mail
        Mail mail = Mail.builder()
                .url("http://165.22.30.3/account/job-seeker/confirm")
                .toMail(savedJobSeeker.getEmail())
                .token(registerConfirmToken.getToken())
                .build();
        kafkaTemplate.send("confirmation-mail-topic",mapper.writeValueAsString(mail));
        return savedJobSeeker;
    }

    @Override
    public String confirmJobSeekerTokenWithEmail(String token) {
        RegisterConfirmToken confirmedToken = confirmTokenService.confirmMailToken(token);
        userService.confirmUser(confirmedToken.getUser());
        return confirmedToken.getUser().getEmail()+" aktivasyon işlemi başarılı.";
    }

}
