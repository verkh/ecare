package com.ecare.dto;

import com.ecare.controllers.Utils;
import com.ecare.models.ContractOptionPO;
import com.ecare.models.PlanOptionPO;
import com.ecare.models.PlanPO;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder=true)
public class Plan {
    private Long id;
    private String name;
    private Double price;
    @Singular private List<Option> options;

    public Plan(Long id) {
        this.setId(id);
    }

    public Plan(PlanPO plan){
        this.id = plan.getId();
        this.name = plan.getName();
        this.price = plan.getPrice();
        options = plan.getOptions().stream().map(opt -> new Option(opt))
                .collect(Collectors.toList());
        Utils.sortOptions(options);
    }

    public PlanPO toEntity() {
        PlanPO plan = new PlanPO();
        plan.setId(this.id);
        plan.setName(this.name);
        plan.setPrice(this.price);
        for(final Option option : options) {
            PlanOptionPO opt = option.toEntity(new PlanOptionPO());
            opt.setPlanId(this.id);
            plan.getOptions().add(opt);
        }
        return plan;
    }

    public static Plan of(PlanPO plan) {
        return new Plan(plan);
    }
}
