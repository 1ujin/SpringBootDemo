package com.controller;

import com.pojo.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserServiceController {
    @Autowired
    UserService userService;

    @RequestMapping(value = {"/user/{id}", "/user"})
    public ResponseEntity<Object> getUser(@PathVariable(required = false) Long id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    // 此处可以直接用 PutMapping
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        userService.updateUser(id, user);
        return new ResponseEntity<>("user is updated successfully", HttpStatus.OK);
    }

    // 此处可以直接用 DeleteMapping
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>("user is deleted successfully", HttpStatus.OK);
    }

    // 此处可以直接用 PostMapping
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        System.out.println(user.getName());
        userService.createUser(user);
        return new ResponseEntity<>("user is created successfully", HttpStatus.CREATED);
    }
}
