package com.ecare.dto;

import com.ecare.models.OptionRestrictionPO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder=true)
public class OptionRestriction {
    private Long id;
    private Long optionId1;
    private Long optionId2;
    private OptionRestrictionPO.Type type;

    public OptionRestriction(OptionRestrictionPO rule) {
        this.id = rule.getId();
        this.optionId1 = rule.getOptionId1();
        this.optionId2 = rule.getOptionId2();
        this.type = rule.getRule();
    }

    /**
     * Creates restriction rule related to two options
     * @param id1 source option id
     * @param id2 related option id
     */
    public OptionRestriction(Long id1, Long id2) {
        this.optionId1 = id1;
        this.optionId2 = id2;
        this.type = OptionRestrictionPO.Type.REQUIRES;
    }

    public OptionRestrictionPO toEntity() {
        OptionRestrictionPO rule = new OptionRestrictionPO();
        rule.setId(this.id);
        rule.setOptionId1(this.optionId1);
        rule.setOptionId2(this.optionId2);
        rule.setRule(this.type);
        return rule;
    }
}
