package com.uvalet.users.api.services.specs;

import com.uvalet.users.api.domains.valueobjects.CustomerProfile;
import com.uvalet.users.api.domains.valueobjects.Vehicle;

import java.util.List;

public interface CustomerService extends UserService<CustomerProfile> {

    void addCustomerVehicle(String userId, Vehicle vehicle);

    List<CustomerProfile> getCustomers();
}
