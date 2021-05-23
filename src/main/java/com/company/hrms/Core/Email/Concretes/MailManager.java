package com.company.hrms.Core.Email.Concretes;

import com.company.hrms.Core.Email.Abstracts.MailService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
@Qualifier(value = "MailManager")
public class MailManager implements MailService {

    @Override
    public void sendConfirmationMail(String email, String token) {
        System.out.println("Doğrulama maili gönderildi. Email: "+email+" Token: "+token);
    }
}
