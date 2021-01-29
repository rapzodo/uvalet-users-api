package com.uvalet.users.api.domains.valueobjects;

import lombok.Data;

import java.util.Date;

@Data
public abstract class Card implements PaymentMethod {

    private String cardNumber;
    private String cardHolder;
    private String securityCode;
    private Date expireDate;
    private String issuer;
}
