package com.ecare.models;

import com.ecare.models.base.AbstractNamedPO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        this.options = new HashSet<>(other.getOptions());
    }

    @Column(name = "price")
    private Double price;

    @OneToMany(mappedBy="plan", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<PlanOptionPO> options = new HashSet<>();
}
