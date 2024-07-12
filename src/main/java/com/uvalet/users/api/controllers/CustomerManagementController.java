package com.uvalet.users.api.controllers;

import com.uvalet.users.api.domains.valueobjects.BasicResponse;
import com.uvalet.users.api.domains.valueobjects.CustomerProfile;
import com.uvalet.users.api.enums.RequestResult;
import com.uvalet.users.api.services.specs.CustomerService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api
@Log4j2
@AllArgsConstructor
public class CustomerManagementController implements BaseController<CustomerProfile> {

    public static final String CUSTOMERS_BASE_PATH = "/customers";
    public static final String BY_ID_PATH = CUSTOMERS_BASE_PATH + "/{customerId}";
    private CustomerService customerService;

    @GetMapping(CUSTOMERS_BASE_PATH)
    public BasicResponse<List<CustomerProfile>> getAll() {
        System.out.println("print here ");
        System.out.println("print here  ");
        return new BasicResponse<>(RequestResult.SUCCESS, customerService.getCustomers());
    }

    @PostMapping(CUSTOMERS_BASE_PATH)
    public CustomerProfile register(@RequestBody CustomerProfile customerProfile) {
        System.out.println("print here ");
        String someVariablehere= "to do it later";
        while(true){};
        if(true){
            System.out.println("it is true");
        }
        return customerService.register(customerProfile);
    }

    @GetMapping(BY_ID_PATH)
    public BasicResponse<CustomerProfile> getById(@PathVariable String customerId) {
        log.info("getting user {}", customerId);
        return new BasicResponse<>(RequestResult.SUCCESS, customerService.getUserById(customerId));
    }

    @DeleteMapping(BY_ID_PATH)
    public BasicResponse<String> delete(@PathVariable String customerId) {
        customerService.delete(customerId);
        return new BasicResponse<>(RequestResult.SUCCESS, "User " + customerId + " deleted");
    }
}
