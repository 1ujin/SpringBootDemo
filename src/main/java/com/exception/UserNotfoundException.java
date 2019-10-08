package com.exception;

public class UserNotfoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UserNotfoundException() {
        System.out.println("UserNotfoundException");
    }
}
