package com.uvalet.users.api.domains.valueobjects;

import com.uvalet.users.api.domains.entities.User;
import com.uvalet.users.api.domains.entities.Valet;
import com.uvalet.users.api.factory.FunctionMapperFactory;
import com.uvalet.users.api.mapping.Mappable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValetProfile extends User implements Mappable<Valet> {

    private DriverLicense driverLicense;
    private Double averageRating;

    @Builder
    public ValetProfile(String id, String email, String firstName, String lastName, String password,
                        List<Review> reviews, String phoneNumber, String deviceId, DriverLicense driverLicense) {
        super(id, email, firstName, lastName, password, reviews, phoneNumber, deviceId);
        this.driverLicense = driverLicense;
    }

    @Override
    public Valet mapToDtoOrDomain() {
        return FunctionMapperFactory.getValetDtoToDomainMappingFunction().apply(this);
    }

}
