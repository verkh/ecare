package com.ecare.dto;

import com.ecare.models.UserPO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
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
        user.setAuthority(this.getAuthority());
        user.setBlocked(this.isBlocked());
        user.setDisabledBy(this.getDisabledBy());
        return user;
    }

    public void setPassword(String password) {
        // encode
    }

    public boolean isAdmin() { return authority == UserPO.Authority.ROLE_ADMIN; }
    public boolean isDictator() { return authority == UserPO.Authority.ROLE_DICTATOR; }
}