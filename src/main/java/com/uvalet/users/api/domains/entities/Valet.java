package com.uvalet.users.api.domains.entities;


import com.uvalet.users.api.domains.valueobjects.Balance;
import com.uvalet.users.api.domains.valueobjects.DriverLicense;
import com.uvalet.users.api.domains.valueobjects.Review;
import com.uvalet.users.api.domains.valueobjects.ValetProfile;
import com.uvalet.users.api.factory.FunctionMapperFactory;
import com.uvalet.users.api.mapping.Mappable;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Data
@Document("valets")
@NoArgsConstructor
public class Valet extends User implements Mappable<ValetProfile> {

    private DriverLicense driverLicense;
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private GeoJsonPoint currentLocation;
    @NonNull
    private Balance balance;

    @Builder
    public Valet(String email, String firstName, String lastName, String password, List<Review> reviews,
                 String phoneNumber, String id, DriverLicense driverLicense, String deviceId, Balance balance) {
        super(id, email, firstName, lastName, password, reviews, phoneNumber, deviceId);
        this.driverLicense = driverLicense;
        this.balance = balance;
    }

    public void creditToBalance(BigDecimal amount) {
        balance.setAmount(balance.getAmount().add(amount));
    }

    public void withdrawlFromBalance(BigDecimal amount) {
        balance.setAmount(balance.getAmount().subtract(amount));
    }

    @Override
    public ValetProfile mapToDtoOrDomain() {
        return FunctionMapperFactory.getValetToDtoMappingFunction().apply(this);
    }

}
