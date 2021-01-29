package com.uvalet.users.api.domains.valueobjects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KeySafeDevice {
    private String deviceSerialNum;
    private String tokenCode;
}
