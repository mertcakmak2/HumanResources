package com.company.hrms.Core.Email.Concretes;

import com.company.hrms.Core.Email.Abstracts.MailService;
import com.company.hrms.Entities.Concretes.Mail;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
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
    private final ObjectMapper mapper;

    @KafkaListener(
            topics = "confirmation-mail-topic",
            groupId = "group-id")
    public void consumeConfirmationEmail(String mailToString) throws JsonProcessingException {
        Mail mail = mapper.readValue(mailToString, Mail.class);
        sendConfirmationMail(mail);
    }

    @Override
    public void sendConfirmationMail(Mail mail) {
        String confirmationLink = mail.getUrl() + "?token=" + mail.getToken();

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("cakmakforbusiness@gmail.com");
        simpleMailMessage.setTo(mail.getToMail());
        simpleMailMessage.setSubject("HRMS Aktivasyon Maili");
        simpleMailMessage.setText(confirmationLink);

        mailSender.send(simpleMailMessage);
        System.out.println("Doğrulama maili gönderildi. " +
                "Email: " + mail.getToMail() + " " +
                "Token: " + mail.getToken() + " " +
                "ConfirmationLink: " + confirmationLink);
    }

}
