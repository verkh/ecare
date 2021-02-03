package com.ecare.models;

import com.ecare.models.base.AbstractPO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * ContractPO describes the 'contracts' table and contains information of
 * the related tariff plan
 */
@Entity
@Table (name = "contracts")
@Getter
@Setter
@NoArgsConstructor
public class ContractPO extends AbstractPO {
    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "plan_id", referencedColumnName = "id")
    private PlanPO plan;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserPO user;

    @OneToMany(mappedBy="contract", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<ContractOptionPO> options = new HashSet<>();
}
