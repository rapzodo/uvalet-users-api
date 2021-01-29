package com.uvalet.users.api.domains.valueobjects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DriverLicense {
    private String number;
    private LocalDate issueDate;
    private LocalDate expireDate;
    private String issuerState;
}
