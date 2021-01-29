package com.uvalet.users.api.factory;

import com.uvalet.users.api.domains.entities.Customer;
import com.uvalet.users.api.domains.entities.Valet;
import com.uvalet.users.api.domains.valueobjects.DriverLicense;
import com.uvalet.users.api.domains.valueobjects.KeySafeDevice;
import com.uvalet.users.api.domains.valueobjects.Review;
import com.uvalet.users.api.domains.valueobjects.Vehicle;

import java.time.LocalDate;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestsUtil {

    public static Valet buildAValet(String id, String deviceId) {
        return Valet.builder()
                .id(id)
                .email("iam@m.com")
                .password("secret")
                .driverLicense(new DriverLicense("11111", LocalDate.now(), LocalDate.of(2030, 1, 1), "IL"))
                .firstName("I")
                .lastName("am")
                .reviews(Collections.singletonList(new Review(3.5, "1")))
                .phoneNumber("(111)222-2222")
                .deviceId(deviceId)
                .build();
    }

    public static Customer buildACustomer(String id, String deviceId) {
        return Customer.builder()
                .id(id)
                .firstName("john")
                .lastName("doe")
                .deviceId(deviceId)
                .email("johndoe@mail.com")
                .password("secret")
                .vehicles(Stream.of(buildAVehicle()).collect(Collectors.toList()))
                .reviews(Collections.singletonList(new Review(3.5, "1")))
                .build();
    }

    public static Vehicle buildAVehicle() {
        return Vehicle.builder()
                .plate("AAA123")
                .brand("Ford")
                .model("Edge")
                .color("black")
                .year("2017")
                .keySafeDevice(KeySafeDevice.builder().deviceSerialNum("111").tokenCode("validToken").build())
                .build();
    }
}
