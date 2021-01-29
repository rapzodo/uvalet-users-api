package com.uvalet.users.api.factory;

import com.uvalet.users.api.domains.entities.Customer;
import com.uvalet.users.api.domains.entities.Valet;
import com.uvalet.users.api.domains.valueobjects.CustomerProfile;
import com.uvalet.users.api.domains.valueobjects.ValetProfile;

import java.util.function.Function;

public class FunctionMapperFactory {

    public static Function<CustomerProfile, Customer> getDtoToCustomerUserMappingFunction() {
        return customerUserDto -> Customer.builder()
                .email(customerUserDto.getEmail())
                .firstName(customerUserDto.getFirstName())
                .lastName(customerUserDto.getLastName())
                .password(customerUserDto.getPassword())
                .phoneNumber(customerUserDto.getPhoneNumber())
                .deviceId(customerUserDto.getDeviceId())
                .paymentMethods(customerUserDto.getPaymentMethods())
                .build();
    }

    public static Function<Customer, CustomerProfile> getCustomerToDtoMappingFunction() {
        return customerUser -> CustomerProfile.builder()
                .id(customerUser.getId())
                .email(customerUser.getEmail())
                .firstName(customerUser.getFirstName())
                .lastName(customerUser.getLastName())
                .deviceId(customerUser.getDeviceId())
                .password(customerUser.getPassword())
                .paymentMethods(customerUser.getPaymentMethods())
                .reviews(customerUser.getReviews())
                .build();
    }

    public static Function<Valet, ValetProfile> getValetToDtoMappingFunction() {
        return valet -> ValetProfile.builder()
                .id(valet.getId())
                .deviceId(valet.getDeviceId())
                .email(valet.getEmail())
                .password(valet.getPassword())
                .firstName(valet.getFirstName())
                .lastName(valet.getLastName())
                .phoneNumber(valet.getPhoneNumber())
                .driverLicense(valet.getDriverLicense())
                .reviews(valet.getReviews())
                .build();
    }

    public static Function<ValetProfile, Valet> getValetDtoToDomainMappingFunction() {
        return valetDto -> Valet.builder()
                .id(valetDto.getId())
                .driverLicense(valetDto.getDriverLicense())
                .email(valetDto.getEmail())
                .password(valetDto.getPassword())
                .firstName(valetDto.getFirstName())
                .lastName(valetDto.getLastName())
                .deviceId(valetDto.getDeviceId())
                .phoneNumber(valetDto.getPhoneNumber())
                .build();
    }
}
