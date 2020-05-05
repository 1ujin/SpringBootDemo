package com.controller;

import com.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SwaggerAPIController {
    // @Autowired
    // UserService userService;
    //
    // @ApiOperation(value="获取用户列表", notes="返回全部用户，容器为数组。")
    // @ApiImplicitParam(name = "无", required = false, dataType = "User")
    // @RequestMapping(value = "/api/user", method = RequestMethod.GET)
    // public List<Object> getUsers() {
    //     return new ArrayList<>(userService.getUsers());
    // }
}
