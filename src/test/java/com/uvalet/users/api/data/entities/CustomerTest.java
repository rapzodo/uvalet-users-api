package com.uvalet.users.api.data.entities;

import com.uvalet.users.api.domains.entities.Customer;
import com.uvalet.users.api.domains.valueobjects.CustomerProfile;
import com.uvalet.users.api.factory.TestsUtil;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class CustomerTest {

    @Test
    public void shouldMapToDto() throws NoSuchMethodException {
        final String[] fields = {"id", "email", "firstName",
                "lastName", "deviceId", "password",
                "paymentMethods", "reviews"};
        final Customer customer = TestsUtil.buildACustomer("1", "d1");
        final CustomerProfile customerProfile = customer.mapToDtoOrDomain();
        Assertions.assertThat(customerProfile).extracting(fields).isEqualTo(customer);
    }
}
