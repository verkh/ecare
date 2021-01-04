package com.ecare.models;

import com.ecare.models.base.AbstractNamedPO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * ContractPO describes the 'contracts' table and contains information of
 * the related tariff plan
 */
@Entity
@Table (name = "contracts")
@Getter
@Setter
@NoArgsConstructor
public class ContractPO extends AbstractNamedPO {
    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "plan_id", referencedColumnName = "id")
    private PlanPO plan;

    @JoinTable(
            name="user_contracts",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "contract_id")
    )
    @ManyToOne(fetch=FetchType.LAZY)
    private UserPO user;
}
