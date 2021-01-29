package com.uvalet.users.api.mapping;

public interface Mappable<TARGET> {

    TARGET mapToDtoOrDomain();
}
