package com.ecare.validators;

import com.ecare.dto.Option;
import com.ecare.models.OptionRestrictionPO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.HashMap;
import java.util.Map;

@Component
public class OptionValidator  implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Option.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        final String[] fields = {
                "value.name",
                "value.price",
                "value.turnOnPrice",
                "value.description",
        };

        ValidatorUtils.checkEmptiness(fields, errors);

        Option option = (Option) target;

        if(option.getAllOptionNames().containsKey(option.getValue().getName()))
            errors.rejectValue("option.name.notUnique", "NotUniqueOptionName");

        ValidatorUtils.priceNotNegative("value.price", option.getValue().getPrice(), errors);
        ValidatorUtils.priceNotNegative("value.price", option.getValue().getTurnOnPrice(), errors);

        Map<Long, Integer> usedIds = new HashMap();
        for(int i = 0; i < option.getRestrictions().size(); i++) {
            final OptionRestrictionPO rule = option.getRestrictions().get(i);
            if(usedIds.containsKey(rule.getOptionId2())) {
                errors.rejectValue(String.format("value.restrictions[%d]", i), "option.rule.duplicated");
                errors.rejectValue(String.format("value.restrictions[%d]", usedIds.get(rule.getOptionId2())), "option.rule.duplicated");
                break;
            }
            usedIds.put(rule.getOptionId2(), i);
        }
    }
}
