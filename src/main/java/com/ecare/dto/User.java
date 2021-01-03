package com.ecare.dto;

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
}