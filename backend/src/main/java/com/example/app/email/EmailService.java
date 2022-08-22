// This class is a Service.
// Services are used to write business logic in a different layer, separated from Controller class file.
// All the methods for the endpoints are implemented here and are called by the Controller class.
package com.example.app.email;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class EmailService implements EmailSender{

    // With this Logger we can use this class to log when we send an email
    private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender mailSender;

    @Override
    @Async
    public void send(String to, String email) {
        try{
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject("[SH-TOOL] Confirme ton adresse mail!");
            helper.setFrom("yocosi.dev@gmail.com");
            mailSender.send(mimeMessage);
        } catch (MessagingException e){
            LOGGER.error("error: failed to send email", e);
            throw new IllegalStateException("error: failed to send email");
        }
    }
}
