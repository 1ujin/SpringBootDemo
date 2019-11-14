package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/view-users")
    public String viewUsers() {
        System.out.println(messageSource.getMessage("welcome.text", null, LocaleContextHolder.getLocale()));
        return "view-users";
    }

    @RequestMapping(value = "/add-user")
    public String addUser() {
        return "add-user";
    }
}
