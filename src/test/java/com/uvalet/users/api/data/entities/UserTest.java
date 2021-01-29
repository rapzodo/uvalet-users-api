package com.uvalet.users.api.data.entities;

import com.uvalet.users.api.domains.entities.Customer;
import com.uvalet.users.api.domains.entities.Valet;
import com.uvalet.users.api.domains.valueobjects.Review;
import com.uvalet.users.api.factory.TestsUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import shaded.com.google.common.collect.ImmutableList;

public class UserTest {

    @Test
    public void shouldReturnCustomerUserAvgRating() {
        final ImmutableList<Review> reviews = ImmutableList.of(new Review(3.0, "1000"),
                new Review(3.0, "1000"), new Review(3.0, "1000"));
        final Customer customer = TestsUtil.buildACustomer("1000", "device1");
        customer.setReviews(reviews);
        Assertions.assertThat(customer.getAverageRating()).isEqualTo(3);
    }

    @Test
    public void shouldReturnValetUserAvgRating() {
        final ImmutableList<Review> reviews = ImmutableList.of(new Review(3.0, "1000"),
                new Review(3.0, "1000"), new Review(3.0, "1000"));
        final Valet user = TestsUtil.buildAValet("1000", "device1");
        user.setReviews(reviews);
        Assertions.assertThat(user.getAverageRating()).isEqualTo(3);
    }
}