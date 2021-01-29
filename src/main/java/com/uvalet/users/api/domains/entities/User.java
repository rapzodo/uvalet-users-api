package com.uvalet.users.api.domains.entities;

import com.uvalet.users.api.domains.valueobjects.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.lang.NonNull;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private String id;
    @Indexed(unique = true, dropDups = true)
    private String email;
    private String firstName;
    private String lastName;
    @NonNull
    private String password;
    private List<Review> reviews;
    private String phoneNumber;
    @NonNull
    private String deviceId;

    public Double getAverageRating() {
        if (reviews != null) {
            return reviews.stream().mapToDouble(Review::getRating).average().orElse(0);
        }
        return 0.0;
    }

}
