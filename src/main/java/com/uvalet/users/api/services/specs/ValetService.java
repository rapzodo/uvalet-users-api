package com.uvalet.users.api.services.specs;

import com.uvalet.users.api.domains.valueobjects.ValetProfile;

import java.util.List;

public interface ValetService extends UserService<ValetProfile> {

    List<ValetProfile> getValets();
}
