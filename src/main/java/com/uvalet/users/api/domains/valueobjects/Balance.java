package com.uvalet.users.api.domains.valueobjects;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class Balance {
    @Value("${valet.balance.currency:USD}")
    private String currency;
    private BigDecimal amount;

    public Balance(BigDecimal amount) {
        this.amount = amount;
    }
}
