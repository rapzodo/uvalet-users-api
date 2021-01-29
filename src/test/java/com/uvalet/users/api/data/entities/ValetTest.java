package com.uvalet.users.api.data.entities;

import com.uvalet.users.api.domains.entities.Valet;
import com.uvalet.users.api.domains.valueobjects.Balance;
import com.uvalet.users.api.factory.TestsUtil;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class ValetTest {

    @Test
    public void shouldAddToValetBalance() {
        Valet valet = aValet();
        valet.creditToBalance(BigDecimal.TEN);
        assertThat(valet.getBalance().getAmount().intValue()).isEqualTo(20);
    }

    @Test
    public void withDrawlFromBalance() {
        Valet valet = aValet();
        valet.withdrawlFromBalance(BigDecimal.ONE);
        assertThat(valet.getBalance().getAmount().intValue()).isEqualTo(9);

    }

    private Valet aValet() {
        final Valet valet = TestsUtil.buildAValet("1", "d1");
        valet.setBalance(new Balance(BigDecimal.TEN));
        return valet;
    }
}