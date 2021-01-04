package com.ecare.dto;

import com.ecare.models.UserPO;
import lombok.Getter;
import lombok.Setter;
import java.sql.Date;

@Getter
@Setter
public class User {
    private Long id;
    private String name;
    private String lastName;
    private Date date;
    private String passport;
    private String address;
    private String email;
    private String passwordHash;
    //private List<Contract>

    public User(UserPO subscriber) {
        this.id = subscriber.getId();
        this.name = subscriber.getName();
        this.lastName = subscriber.getLastName();
        this.date = subscriber.getDate();
        this.passport = subscriber.getPassport();
        this.address = subscriber.getAddress();
        this.email = subscriber.getEmail();
        this.passwordHash = subscriber.getPasswordHash();
    }
}