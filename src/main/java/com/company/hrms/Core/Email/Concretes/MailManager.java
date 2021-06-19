package com.company.hrms.Core.Email.Concretes;

import com.company.hrms.Core.Email.Abstracts.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
@Qualifier(value = "MailManager")
@RequiredArgsConstructor
public class MailManager implements MailService {

    private final JavaMailSender mailSender;

    @Override
    public void sendConfirmationMail(String email, String url ,String token) {
        String confirmationLink = url+"?token="+token;
        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setFrom("cakmakforbusiness@gmail.com");
        mail.setTo(email);
        mail.setSubject("HRMS Aktivasyon Maili");
        mail.setText(confirmationLink);

        mailSender.send(mail);
        System.out.println("Doğrulama maili gönderildi. Email: "+email+" Token: "+token+" ConfirmationLink: "+confirmationLink);
    }
}
