package com.uvalet.users.api.exceptions;

public class InvalidCredentialsException extends Exception {

    public InvalidCredentialsException() {
        super("Ops! the Email and/or Password entered doesn't/don't match with or records");
    }
}
