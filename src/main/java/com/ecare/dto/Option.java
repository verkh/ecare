package com.ecare.dto;

import com.ecare.models.OptionPO;
import com.ecare.models.OptionRestrictionPO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Option {

    public Option(OptionPO option, List<OptionPO> allOptions) {
        this.value = option;
        this.restrictions = new ArrayList<>(option.getRestrictions());
        for(final OptionPO opt : allOptions){
            this.allOptions.put(opt.getId(), opt);
            this.allOptionNames.put(opt.getId(), opt.getName());
        }
    }

    public void addRestriction(OptionRestrictionPO.Type type, Long id) {
        if(!allOptions.containsKey(id)) {
            throw new IllegalArgumentException(String.format("Option with id %d is not found", id));
        }
    }

    public String getOptionName(Long id) {
        if(!allOptionNames.containsKey(id)) {
            throw new IllegalArgumentException(String.format("Option with id %d is not found", id));
        }
        return allOptionNames.get(id);
    }

    public void addNewRule() {
        Long id2 = allOptions.isEmpty() ? null : allOptions.values().iterator().next().getId();
        OptionRestrictionPO rule = new OptionRestrictionPO(value.getId(), id2);
        restrictions.add(rule);
    }

    public void removeRule(Long index) {
        restrictions.remove(index.intValue());
    }

    public void clear() {
        allOptions.clear();
        allOptionNames.clear();
    }

    public void setId(Long id) {
        for(OptionRestrictionPO rule : restrictions)
            rule.setOptionId1(id);
    }

    private OptionPO value;
    private List<OptionRestrictionPO> restrictions;
    private Map<Long, OptionPO> allOptions = new HashMap<>();
    private Map<Long, String> allOptionNames = new HashMap<>();
}