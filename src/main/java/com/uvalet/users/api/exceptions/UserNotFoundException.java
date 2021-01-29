package com.uvalet.users.api.exceptions;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException() {
        super("user was not found!");
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
