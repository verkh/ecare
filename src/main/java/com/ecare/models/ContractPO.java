package com.ecare.models;

import com.ecare.models.base.AbstractPO;
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
public class ContractPO extends AbstractPO {
    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "plan_id", referencedColumnName = "id")
    private PlanPO plan;

    @OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserPO user;
}
