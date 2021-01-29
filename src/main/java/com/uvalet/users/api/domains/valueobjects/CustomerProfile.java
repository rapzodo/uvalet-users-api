package com.uvalet.users.api.domains.valueobjects;

import com.uvalet.users.api.domains.entities.Customer;
import com.uvalet.users.api.domains.entities.User;
import com.uvalet.users.api.factory.FunctionMapperFactory;
import com.uvalet.users.api.mapping.Mappable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerProfile extends User implements Mappable<Customer> {

    private Double averageRating;
    private List<PaymentMethod> paymentMethods;

    @Builder
    public CustomerProfile(String email, String firstName, String lastName, String password,
                           List<Review> reviews, String phoneNumber, String deviceId,
                           List<PaymentMethod> paymentMethods, String id) {
        super(id, email, firstName, lastName, password, reviews, phoneNumber, deviceId);
        this.paymentMethods = paymentMethods;
    }

    @Override
    public Customer mapToDtoOrDomain() {
        return FunctionMapperFactory.getDtoToCustomerUserMappingFunction().apply(this);
    }

}
