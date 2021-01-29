package com.uvalet.users.api.domains.valueobjects;

public class NullCustomerProfile extends CustomerProfile {

    @Override
    public String getId() {
        return "we can return a specific id or error in order to specify a fallback problem";
    }
}
