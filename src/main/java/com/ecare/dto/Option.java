package com.ecare.dto;

import com.ecare.models.OptionPO;
import com.ecare.models.OptionRestrictionPO;
import com.ecare.models.PlanOptionPO;
import com.ecare.models.base.AttachedOption;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class Option {
    private Long attachedId;
    private Long id;
    private String name;
    private Double price;
    private String description;
    private Double turnOnPrice;
    private boolean undisablable;
    private boolean deprecated;
    private boolean enabled;
    private List<OptionRestriction> restrictions = new ArrayList<>();

    public Option(Long id) {
        this.setId(id);
    }

    public Option(AttachedOption option){
        this(option.getOption());
        this.attachedId = option.getId();
        this.undisablable = option.isUndisablable();
    }

    public Option(OptionPO option){
        this.id = option.getId();
        this.name = option.getName();
        this.price = option.getPrice();
        this.description = option.getDescription();
        this.turnOnPrice = option.getTurnOnPrice();
        this.deprecated = option.isDeprecated();
        for(final OptionRestrictionPO rule : option.getRestrictions()){
            this.restrictions.add(new OptionRestriction(rule));
        }
    }

    public OptionPO toEntity() {
        OptionPO option = new OptionPO();
        option.setId(this.id);
        option.setDescription(this.description);
        option.setPrice(this.price);
        option.setTurnOnPrice(this.turnOnPrice);
        option.setName(this.name);
        option.setDeprecated(this.isDeprecated());
        option.setRestrictions(restrictions.stream().map(rule -> rule.toEntity())
                .collect(Collectors.toList()));
        return option;
    }

    public <OptionType extends AttachedOption> OptionType toEntity(OptionType optionWrapper) {
        optionWrapper.setId(this.attachedId);
        optionWrapper.setUndisablable(this.undisablable);
        optionWrapper.setOption(toEntity());
        return optionWrapper;
    }

    public static Option of(AttachedOption value) { return new Option(value); }
    public static Option of(OptionPO value) { return new Option(value); }
}
