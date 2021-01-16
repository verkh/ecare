package com.ecare.models;

import com.ecare.models.base.AbstractPO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @JoinTable(
            name="contract_options",
            joinColumns = @JoinColumn(name = "contract_id"),
            inverseJoinColumns = @JoinColumn(name = "option_id")
    )
    @OneToMany()
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<OptionPO> options = new ArrayList<>();
}
