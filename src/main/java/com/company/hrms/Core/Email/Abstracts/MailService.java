package com.company.hrms.Core.Email.Abstracts;

import com.company.hrms.Entities.Concretes.Mail;
import org.springframework.stereotype.Component;

@Component
public interface MailService {

    void sendConfirmationMail(Mail mail);
}
