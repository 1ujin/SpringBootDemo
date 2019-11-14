package com.controller;

import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SwaggerAPIController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/api/user")
    public List<Object> getUsers() {
        return new ArrayList<>(userService.getUsers());
    }
}
