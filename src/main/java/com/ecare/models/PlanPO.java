package com.ecare.models;

import com.ecare.models.base.AbstractNamedPO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * PlanPO describes a tariff plan represented in database, which also contains
 * information regarding related to the plan options
 */
@Entity
@Table (name = "plans")
@Getter @Setter @NoArgsConstructor
public class PlanPO extends AbstractNamedPO {

    public PlanPO(PlanPO other) {
        this.setId(other.getId());
        this.setName(other.getName());
        this.price = other.getPrice();
        this.options = new ArrayList<>(other.getOptions());
    }

    public PlanPO(Long id) {
        this.setId(id);
    }

    @Column(name = "price")
    private Double price;

    @JoinTable(
            name="plan_options",
            joinColumns = @JoinColumn(name = "plan_id"),
            inverseJoinColumns = @JoinColumn(name = "option_id")
    )
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<OptionPO> options = new ArrayList<>();

}
