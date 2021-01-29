package com.uvalet.users.api.exceptions;

public class InvalidAddressException extends RuntimeException{

    public InvalidAddressException() {
        super(String.format("Could not find the coordinates for the provided address!"));
    }
}
