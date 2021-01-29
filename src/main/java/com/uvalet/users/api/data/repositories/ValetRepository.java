package com.uvalet.users.api.data.repositories;

import com.uvalet.users.api.data.projections.UserView;
import com.uvalet.users.api.domains.entities.Valet;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ValetRepository extends MongoRepository<Valet, String> {
    Valet findByDriverLicenseNumber(String driverLicense);

    Optional<UserView> findByEmail(String email);
}
