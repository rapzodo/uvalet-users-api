package com.uvalet.users.api.domains.valueobjects;

public class Cash implements PaymentMethod {
    @Override
    public void pay() {
        System.out.println("paid in CASH $$$$$");
    }
}
