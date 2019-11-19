package com.service;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Date;
import java.util.Properties;

@Service
public class EmailService {
    public void send() throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.qq.com");
        properties.put("mail.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // return super.getPasswordAuthentication();
                return new PasswordAuthentication("821251250@qq.com", "nvhsoewdvdrtbeac");
            }
        });

        MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.setContent("Spring Boot Email", "text/html");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(bodyPart);

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("821251250@qq.com", false));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("821251250@qq.com"));
        message.setSubject("无主题");
        message.setContent("测试", "text/html");
        message.setSentDate(new Date());
        message.setContent(multipart);
        Transport.send(message);
    }
}
