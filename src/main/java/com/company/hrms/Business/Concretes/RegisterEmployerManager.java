package com.company.hrms.Business.Concretes;

import com.company.hrms.Business.Abstracts.*;
import com.company.hrms.Core.Email.Abstracts.MailService;
import com.company.hrms.DataAccess.Abstracts.ConfirmationEmployerDao;
import com.company.hrms.Entities.Concretes.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterEmployerManager implements RegisterEmployerService {

    private final UserService userService;
    private final EmployerService employerService;
    private final SystemUserService systemUserService;
    private final ConfirmTokenService confirmTokenService;
    private final ConfirmationEmployerDao confirmationEmployerDao;
    @Qualifier(value = "MailManager")
    private final MailService mailManager;

    @Override
    public Employer registerEmployer(Employer employer) throws Exception {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        employer.setPassword(bCryptPasswordEncoder.encode(employer.getPassword()));

        Employer savedEmployer = employerService.saveEmployer(employer);
        RegisterConfirmToken registerConfirmToken = confirmTokenService.saveMailConfirmToken(savedEmployer);

        ConfirmationEmployer confirmationEmployer= new ConfirmationEmployer(registerConfirmToken, savedEmployer);
        confirmationEmployerDao.save(confirmationEmployer);

        // Todo: Send Confirmation Mail
        mailManager.sendConfirmationMail(savedEmployer.getEmail(),
                "http://165.22.30.3/employer/account/confirm",
                registerConfirmToken.getToken()
        );

        return savedEmployer;
    }

    @Override
    public String confirmEmployerTokenWithEmail(String token) throws Exception {

        RegisterConfirmToken confirmedToken = confirmTokenService.confirmMailToken(token);
        return confirmedToken.getUser().getEmail()+" email aktivasyon işlemi başarılı.";
    }

    @Override
    public String confirmEmployerWithSystemUser(int employerId, int systemUserId) throws Exception {

        RegisterConfirmToken confirmedToken = confirmTokenService.findByUserId(employerId);
        SystemUser systemUser = systemUserService.findSystemUserById(systemUserId);

        ConfirmationEmployer confirmationEmployer = confirmationEmployerDao.findByEmployer_Id(employerId);
        confirmationEmployer.setSystemUser(systemUser);

        if(confirmedToken.getConfirmedAt() != null){
            userService.confirmUser(confirmedToken.getUser());
            confirmationEmployerDao.save(confirmationEmployer);
        }
        return confirmedToken.getUser().getEmail()+" kullanıcısı "+ systemUser.getEmail() +" tarafından aktive edildi.";
    }
}
