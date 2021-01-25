package com.ecare.dto;

import com.ecare.models.OptionPO;
import com.ecare.models.OptionRestrictionPO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class describes temporary object which allows to configure options rules
 */
@Getter
@Setter
public class Option {

    /**
     * Creates temporary object
     * @param option original option
     * @param allOptions all available options
     */
    public Option(OptionPO option, List<OptionPO> allOptions) {
        this.value = option;
        this.restrictions = new ArrayList<>(option.getRestrictions());
        for(final OptionPO opt : allOptions){
            this.allOptions.put(opt.getId(), opt);
            this.allOptionNames.put(opt.getId(), opt.getName());
        }
    }

    /**
     * Adds new restriction to option
     * @param type type of restriction
     * @param id an id of related option via restriction
     */
    public void addRestriction(OptionRestrictionPO.Type type, Long id) {
        if(!allOptions.containsKey(id)) {
            throw new IllegalArgumentException(String.format("Option with id %d is not found", id));
        }
    }

    /**
     * Seeks for an option name by id
     * @param id id of seeked option
     * @return The name of option
     */
    public String getOptionName(Long id) {
        if(!allOptionNames.containsKey(id)) {
            throw new IllegalArgumentException(String.format("Option with id %d is not found", id));
        }
        return allOptionNames.get(id);
    }

    /**
     * Adds new empty restriction
     */
    public void addNewRule() {
        Long id2 = allOptions.isEmpty() ? null : allOptions.values().iterator().next().getId();
        OptionRestrictionPO rule = new OptionRestrictionPO(value.getId(), id2);
        restrictions.add(rule);
    }

    /**
     * Removes restriction from restriction list
     * @param index index of restriction in the list
     */
    public void removeRule(Long index) {
        restrictions.remove(index.intValue());
    }

    /**
     * Clears all data that won't be saved
     */
    public void clear() {
        allOptions.clear();
        allOptionNames.clear();
    }

    /**
     * Set id of an target option to related restrictions
     * @param id
     */
    public void setId(Long id) {
        for(OptionRestrictionPO rule : restrictions)
            rule.setOptionId1(id);
    }

    private OptionPO value;
    private List<OptionRestrictionPO> restrictions;
    private Map<Long, OptionPO> allOptions = new HashMap<>();
    private Map<Long, String> allOptionNames = new HashMap<>();
}