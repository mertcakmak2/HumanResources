package com.company.hrms.Core.Email.Abstracts;

import org.springframework.stereotype.Component;

@Component
public interface MailService {

    void sendConfirmationMail(String email, String token);
}
