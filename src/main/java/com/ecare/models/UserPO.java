package com.ecare.models;

import com.ecare.models.base.AbstractNamedPO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

/**
 * SubscriberPO describes a client of eCare system and contains all information
 * related to them. Also class contains information regarding list of contracts
 * signed with company.
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class UserPO extends AbstractNamedPO {

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birth_date")
    private Date date;

    @Column(name = "passport")
    private String passport;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String passwordHash;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "authority")
    private String authority;

    @JoinTable(
            name="user_contracts",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "contract_id")
    )
    @OneToMany
    private List<ContractPO> contracts = new ArrayList<>();
}