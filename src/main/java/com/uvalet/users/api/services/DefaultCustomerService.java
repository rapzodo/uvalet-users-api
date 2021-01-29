package com.uvalet.users.api.services;

import com.uvalet.users.api.data.repositories.CustomerRepository;
import com.uvalet.users.api.domains.valueobjects.CustomerProfile;
import com.uvalet.users.api.domains.valueobjects.Vehicle;
import com.uvalet.users.api.exceptions.UserNotFoundException;
import com.uvalet.users.api.services.specs.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultCustomerService implements CustomerService {

    private final CustomerRepository customerRepository;


    @Override
    public CustomerProfile register(CustomerProfile dto) {
        dto.setId(customerRepository.save(dto.mapToDtoOrDomain()).getId());
        return dto;
    }

    @Override
    public CustomerProfile getUserById(String id) throws UserNotFoundException {
        return customerRepository.findById(id).orElseThrow(UserNotFoundException::new).mapToDtoOrDomain();
    }

    @Override
    public CustomerProfile update(CustomerProfile dto) {
        customerRepository.save(dto.mapToDtoOrDomain());
        return dto;
    }

    @Override
    public void delete(String id) {
        customerRepository.deleteById(id);
    }

    @Override
    public void addCustomerVehicle(String userId, Vehicle vehicle) throws UserNotFoundException {
        customerRepository.findById(userId).orElseThrow(UserNotFoundException::new).addVehicle(vehicle);
    }

    @Override
    public List<CustomerProfile> getCustomers() {
        return customerRepository.findAll().stream()
                .map(customer -> customer.mapToDtoOrDomain())
                .collect(Collectors.toList());
    }

}
