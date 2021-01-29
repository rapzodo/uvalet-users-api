package com.uvalet.users.api.domains.entities;

import com.uvalet.users.api.domains.valueobjects.CustomerProfile;
import com.uvalet.users.api.domains.valueobjects.PaymentMethod;
import com.uvalet.users.api.domains.valueobjects.Review;
import com.uvalet.users.api.domains.valueobjects.Vehicle;
import com.uvalet.users.api.factory.FunctionMapperFactory;
import com.uvalet.users.api.mapping.Mappable;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Document("costumers")
@NoArgsConstructor
public class Customer extends User implements Mappable<CustomerProfile> {

    private List<PaymentMethod> paymentMethods;
    private List<Vehicle> vehicles;

    @Builder
    public Customer(String id, String email, String firstName, String lastName, String password,
                    List<Review> reviews, String phoneNumber, List<PaymentMethod> paymentMethods,
                    List<Vehicle> vehicles, String deviceId) {
        super(id, email, firstName, lastName, password, reviews, phoneNumber, deviceId);
        this.paymentMethods = paymentMethods;
        this.vehicles = vehicles;
    }

    public void addVehicle(Vehicle vehicle) {
        if (vehicles == null) {
            vehicles = Stream.of(vehicle).collect(Collectors.toList());
        } else {
            vehicles.add(vehicle);
        }
    }

    @Override
    public CustomerProfile mapToDtoOrDomain() {
        return FunctionMapperFactory.getCustomerToDtoMappingFunction().apply(this);
    }
}
