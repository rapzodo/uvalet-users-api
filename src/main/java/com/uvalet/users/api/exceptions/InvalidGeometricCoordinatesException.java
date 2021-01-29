package com.uvalet.users.api.exceptions;

public class InvalidGeometricCoordinatesException extends RuntimeException{
    public InvalidGeometricCoordinatesException() {
        super("Could not find a valida address using provided coordinates");
    }
}
