package com.controller;

import com.pojo.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class UserServiceController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/user")
    public ResponseEntity<Object> getuser() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object>
    updateUser(@PathVariable("id") Long id, @RequestBody User user) {

        userService.updateUser(id, user);
        return new ResponseEntity<>("user is updated successsfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>("user is deleted successsfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/userlist", method = RequestMethod.POST)
    public ResponseEntity<Object> createuser(@RequestBody User user) {
        userService.createUser(user);
        return new ResponseEntity<>("user is created successfully", HttpStatus.CREATED);
    }
}
