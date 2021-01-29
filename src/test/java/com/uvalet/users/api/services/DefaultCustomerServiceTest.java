package com.uvalet.users.api.services;

import com.uvalet.users.api.data.repositories.CustomerRepository;
import com.uvalet.users.api.domains.entities.Customer;
import com.uvalet.users.api.domains.valueobjects.CustomerProfile;
import com.uvalet.users.api.domains.valueobjects.Vehicle;
import com.uvalet.users.api.factory.TestsUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DefaultCustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private DefaultCustomerService service;

    @Test
    public void shouldSaveCustomerAndSetNewId() {
        String expectedId = "1";
        Customer customer = TestsUtil.buildACustomer("100","d1");
        final String encodedPwd = "encodedPwd";

        when(customerRepository.save(any())).thenAnswer(a -> {
            customer.setId(expectedId) ;
            return customer;
        });

        final CustomerProfile savedCustomer = service.register(customer.mapToDtoOrDomain());
        assertThat(savedCustomer.getId())
                .isEqualTo(expectedId);
        assertThat(savedCustomer.getPassword()).isEqualTo(encodedPwd);
    }

    @Test
    public void getCustomerById() {
        Customer customer = TestsUtil.buildACustomer("100","device1");
        when(customerRepository.findById(anyString())).thenReturn(Optional.of(customer));
        assertThat(service.getUserById("100").getEmail()).isNotNull();
    }

    @Test
    public void shouldGetAllCustomers(){
        service.getCustomers();
        verify(customerRepository, only()).findAll();
    }

    @Test
    public void shouldAddNewVehicle(){
        Vehicle vehicle = TestsUtil.buildAVehicle();
        Customer theCustomer = TestsUtil.buildACustomer("100","d1");
        when(customerRepository.findById(anyString())).thenReturn(Optional.of(theCustomer));
        service.addCustomerVehicle("100",vehicle);
        assertThat(theCustomer.getVehicles()).hasSize(2);
        assertThat(theCustomer.getVehicles().get(0)).hasFieldOrPropertyWithValue("plate",vehicle.getPlate());

    }
}