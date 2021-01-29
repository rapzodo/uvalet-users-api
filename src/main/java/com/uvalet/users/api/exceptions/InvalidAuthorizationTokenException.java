package com.uvalet.users.api.exceptions;

public class InvalidAuthorizationTokenException extends Exception {

    public InvalidAuthorizationTokenException() {
        super("Authorization token is not valid!");
    }
}
