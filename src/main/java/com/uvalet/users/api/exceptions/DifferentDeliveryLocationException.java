package com.uvalet.users.api.exceptions;

public class DifferentDeliveryLocationException extends Exception {

    public DifferentDeliveryLocationException() {
        super("Valet location is different from destination on checkout");
    }
}
