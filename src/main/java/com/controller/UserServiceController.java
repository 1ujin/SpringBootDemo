package com.controller;

import com.pojo.User;
import com.service.UserService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;

@RestController
public class UserServiceController {
    @Autowired
    UserService userService;

    @RequestMapping(value = {"/user/{id}", "/user"})
    public ResponseEntity<Object> getUser(@PathVariable(required = false) Long id, HttpServletRequest request) {
        System.out.println("Cookies:");
        Arrays.stream(request.getCookies()).forEach(c -> System.out.println(c.getName() + " : " + c.getValue()));
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    // 此处可以直接用 PutMapping
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateUser(@PathVariable("id") Long id, @Valid @RequestBody User user, BindingResult bs) throws MethodArgumentNotValidException, NoSuchMethodException {
        if (bs.hasErrors()) {
            throw new MethodArgumentNotValidException(new MethodParameter(User.class.getConstructor(Long.class, String.class, Integer.class), 1), bs);
        }
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
    // 加BindingResult后不会自动抛出异常，可以根据需要手动创建异常并抛出
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<Object> createUser(@Validated @RequestBody User user/*, BindingResult bs*/) {
        // if (bs.hasErrors()) {
        //     return new ResponseEntity<>(bs.getAllErrors(), HttpStatus.UNPROCESSABLE_ENTITY);
        // }
        System.out.println(user.getName());
        userService.createUser(user);
        return new ResponseEntity<>("user is created successfully", HttpStatus.CREATED);
    }
}
