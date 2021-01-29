package com.uvalet.users.api.services.specs;

import com.uvalet.users.api.domains.entities.User;

public interface UserService<MODEL extends User> {

    MODEL register(MODEL dto);

    MODEL getUserById(String id);

    MODEL update(MODEL dto);

    void delete(String id);
}
