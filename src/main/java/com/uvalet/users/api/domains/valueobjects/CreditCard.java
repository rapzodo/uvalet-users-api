package com.uvalet.users.api.domains.valueobjects;

public class CreditCard extends Card {
    @Override
    public void pay() {
        System.out.println("Paid with debit card > " + getCardNumber().substring(12));
    }
}
