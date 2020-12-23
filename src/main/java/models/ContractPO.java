package models;

import models.base.AbstractNamedPO;

import javax.persistence.*;

/**
 * ContractPO describes the 'contracts' table and contains information of
 * the related tariff plan
 */
@Entity
@Table (name = "contracts")
public class ContractPO extends AbstractNamedPO {
    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "plan_id", referencedColumnName = "id")
    private PlanPO plan;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public PlanPO getPlan() {
        return plan;
    }

    public void setPlan(PlanPO plan) {
        this.plan = plan;
    }
}
