package com.uvalet.users.api.exceptions;

public class DeviceNotFoundException extends RuntimeException {
    public DeviceNotFoundException() {
        super("Device not found, invalid serial number");
    }
}
