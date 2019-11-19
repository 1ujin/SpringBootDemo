package com.controller;

import com.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
public class EmailController {
    @Autowired
    EmailService emailService;

    @RequestMapping(value = "/sendEmail")
    public String send() throws MessagingException {
        emailService.send();
        return "Email sent successfully.";
    }
}
