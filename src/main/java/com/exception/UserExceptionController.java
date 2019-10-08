package com.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class UserExceptionController {
    @ResponseBody
    @ExceptionHandler(value = UserNotfoundException.class)
    public ResponseEntity<Object> exception(UserNotfoundException e) {
        System.out.println("UserExceptionController");
        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }
}
