package com.uvalet.users.api.domains.valueobjects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
    private String address1;
    private String address2;
    private String number;
    private String city;
    private String state;
    private String country;
    private String postalCode;
}
