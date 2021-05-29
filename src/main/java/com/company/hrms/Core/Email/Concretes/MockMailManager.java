package com.company.hrms.Core.Email.Concretes;

import com.company.hrms.Core.Email.Abstracts.MailService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
@Qualifier(value = "MockMailManager")
public class MockMailManager implements MailService {

    @Override
    public void sendConfirmationMail(String email, String url ,String token) {
        String confirmationLink = url+"?token="+token;
        System.out.println("Mock Doğrulama maili gönderildi. Email: "+email+" Token: "+token+" ConfirmationLink: "+confirmationLink);
    }
}
