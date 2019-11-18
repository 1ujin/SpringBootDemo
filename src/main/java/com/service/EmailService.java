package com.service;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Date;
import java.util.Properties;

@Service
public class EmailService {
    public void send() throws MessagingException {
        // Gmail无法连接
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.protocol", "smtp");
        properties.put("mail.username", "lujin19950710@gmail.com");
        properties.put("mail.password", "19950710");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.fallback", "false");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // return super.getPasswordAuthentication();
                return new PasswordAuthentication("lujin19950710@gmail.com", "19950710");
            }
        });

        MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.setContent("Spring Boot Email", "text/html");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(bodyPart);

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("lujin19950710@gmail.com", false));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("lujin.vip@foxmail.com"));
        message.setSubject("无主题");
        message.setContent("测试", "text/html");
        message.setSentDate(new Date());
        message.setContent(multipart);
        Transport.send(message);
    }
}
