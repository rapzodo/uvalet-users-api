package com.uvalet.users.api.exceptions;

public class RequestOrderNotFoundException extends Exception{
    public RequestOrderNotFoundException() {
        super("Request Order not found");
    }
}
