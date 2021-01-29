package com.uvalet.users.api.domains.valueobjects;

import com.uvalet.users.api.enums.GasTank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle implements Serializable {

    private String type;
    private String plate;
    private String color;
    private String brand;
    private String model;
    private String year;
    private Double millage;
    private GasTank gasTank;
    private Boolean locked;
    private KeySafeDevice keySafeDevice;

    public Boolean isLocked() {
        return locked;
    }

    public void lock() {
        locked = true;
    }

    public void unlock() {
        locked = false;
    }
}
