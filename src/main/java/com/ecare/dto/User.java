package com.ecare.dto;

import com.ecare.models.LocationPO;
import com.ecare.models.PlanOptionPO;
import com.ecare.models.UserPO;
import lombok.*;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder=true)
public class User {
    private Long id;
    private String name;
    private String lastName;
    private Date date;
    private String passport;
    private String address;
    private String email;
    private String passwordHash;
    private String rawPassword;
    private UserPO.Authority authority;
    private boolean blocked;
    private Long disabledBy;
    @Singular private List<Location> locations;

    public User(UserPO subscriber) {
        this.id = subscriber.getId();
        this.name = subscriber.getName();
        this.lastName = subscriber.getLastName();
        this.date = subscriber.getDate();
        this.passport = subscriber.getPassport();
        this.address = subscriber.getAddress();
        this.email = subscriber.getEmail();
        this.passwordHash = subscriber.getPasswordHash();
        this.authority = subscriber.getAuthority();
        this.blocked = subscriber.isBlocked();
        this.disabledBy = subscriber.getDisabledBy();
        locations = subscriber.getLocations().stream().map(location -> new Location(location))
                .collect(Collectors.toList());
    }

    public static User of(UserPO user) {
        return new User(user);
    }

    public UserPO toEntity() {
        UserPO user = new UserPO();
        user.setId(this.getId());
        user.setName(this.getName());
        user.setLastName(this.getLastName());
        user.setDate(this.getDate());
        user.setPassport(this.getPassport());
        user.setAddress(this.getAddress());
        user.setEmail(this.getEmail());
        user.setPasswordHash(this.getPasswordHash());
        user.setAuthority(this.getAuthority() == null ? UserPO.Authority.ROLE_USER : this.getAuthority());
        user.setBlocked(this.isBlocked());
        user.setDisabledBy(this.getDisabledBy());
        for(final Location location : this.locations) {
            LocationPO locationPO = location.toEntity();
            locationPO.setUser(user);
            user.getLocations().add(locationPO);
        }
        return user;
    }

    public void setPassword(String password) { // FIXME
        // encode
    }

    public boolean isAdmin() { return authority == UserPO.Authority.ROLE_ADMIN; }
    public boolean isDictator() { return authority == UserPO.Authority.ROLE_DICTATOR; }

    public boolean equals(User other) {
        return id.equals(other.id) &&
        name.equals(other.name) &&
        lastName.equals(other.lastName) &&
        date.equals(other.date) &&
        passport.equals(other.passport) &&
        address.equals(other.address) &&
        email.equals(other.email) &&
        authority.equals(other.authority) &&
        blocked == blocked &&
        disabledBy.equals(other.disabledBy);
    }

    public Double getCurrentLatitude() {
        if(locations.isEmpty()) return null;
        return locations.get(locations.size() - 1).getLatitude();
    }

    public Double getCurrentLongitude() {
        if(locations.isEmpty()) return null;
        return locations.get(locations.size() - 1).getLongitude();
    }
}