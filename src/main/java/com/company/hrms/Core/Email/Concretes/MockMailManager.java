package com.company.hrms.Core.Email.Concretes;

import com.company.hrms.Core.Email.Abstracts.MailService;
import com.company.hrms.Entities.Concretes.Mail;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
@Qualifier(value = "MockMailManager")
public class MockMailManager implements MailService {

    @Override
    public void sendConfirmationMail(Mail mail) {
        String confirmationLink = mail.getUrl()+"?token="+mail.getToken();
        System.out.println("Mock Doğrulama maili gönderildi. " +
                "Email: "+mail.getToMail()+" " +
                "Token: "+mail.getToken()+" " +
                "ConfirmationLink: "+confirmationLink);
    }
}
