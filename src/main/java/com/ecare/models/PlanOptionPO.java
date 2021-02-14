package com.ecare.models;

import com.ecare.models.base.AttachedOption;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * OptionsPO is a class which describes an option represented in database.
 */
@Entity
@Table (name = "plan_options")
@Getter
@Setter
@NoArgsConstructor
public class PlanOptionPO extends AttachedOption {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="plan_id")
    private PlanPO plan;
}
