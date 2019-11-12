package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
    @RequestMapping(value = "/view-users")
    public String viewUsers() {
        return "view-users";
    }

    @RequestMapping(value = "/add-user")
    public String addUser() {
        return "add-user";
    }
}
