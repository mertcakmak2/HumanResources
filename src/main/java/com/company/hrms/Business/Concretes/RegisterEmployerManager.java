package com.company.hrms.Business.Concretes;

import com.company.hrms.Business.Abstracts.*;
import com.company.hrms.Core.Email.Abstracts.MailService;
import com.company.hrms.DataAccess.Abstracts.ConfirmationEmployerDao;
import com.company.hrms.Entities.Concretes.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
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

        Employer savedEmployer = employerService.saveEmployer(employer);
        RegisterConfirmToken registerConfirmToken = confirmTokenService.saveMailConfirmToken(savedEmployer);

        ConfirmationEmployer confirmationEmployer= new ConfirmationEmployer(registerConfirmToken, savedEmployer);
        confirmationEmployerDao.save(confirmationEmployer);

        // Todo: Send Confirmation Mail
        // Todo: mailLink => http://localhost:5002/api/register/employer/confirm?token=894b3e96-7a9b-40aa-8a25-b039f74e27c2

        return savedEmployer;
    }

    @Override
    public String confirmWithEmail(String token) throws Exception {

        RegisterConfirmToken confirmedToken = confirmTokenService.confirmMailToken(token);

        Employer employer = employerService.findEmployerById(confirmedToken.getUser().getId());

        ConfirmationEmployer confirmationEmployer = confirmationEmployerDao.findByEmployer_Id(employer.getId());

        if(confirmationEmployer.getSystemUser() != null){
            userService.confirmUser(confirmedToken.getUser());
        }
        return confirmedToken.getUser().getEmail()+" has been successfully confirmed with email.";
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
        return confirmedToken.getUser().getEmail()+" has been successfully confirmed from "+ systemUser.getEmail();
    }
}
