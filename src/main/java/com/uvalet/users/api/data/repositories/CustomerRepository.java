package com.uvalet.users.api.data.repositories;

import com.uvalet.users.api.data.projections.UserView;
import com.uvalet.users.api.domains.entities.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CustomerRepository extends MongoRepository<Customer, String> {

    Optional<Customer> findByFirstNameAndLastName(String firstNane, String lastName);

    Optional<UserView> findByEmail(String email);

    Optional<Customer> findByEmailAndPassword(String username, String encode);

}
