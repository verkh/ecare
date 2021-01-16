package com.ecare.validators;

import com.ecare.models.OptionPO;
import com.ecare.models.OptionRestrictionPO;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import java.util.List;
import java.util.stream.IntStream;

public class ValidatorUtils {

    public static void checkEmptiness(String[] fieldList, Errors errors) {
        for(final String field : fieldList)
            ValidationUtils.rejectIfEmpty(errors, field, "property.empty");
    }

    public static void priceNotNegative(String field, Double d, Errors errors) {
        if(d < 0)
            errors.rejectValue(field, "price.negative");
    }

    public static void checkOptions(List<OptionPO> options, Errors errors) {

        if(options.isEmpty()) {
            errors.rejectValue("options", "plan.noOptions");
            return;
        }

        final String optionsField = "options[%d]";

        for(int i = 0; i < options.size(); i++) {
            final OptionPO option = options.get(i);

            for (final OptionRestrictionPO rule : option.getRestrictions()) {

                if(!option.isEnabled()) continue;

                for(int j = 0; j < options.size(); j++){
                    OptionPO secondOpt = options.get(j);
                    if(secondOpt.getId() == rule.getOptionId2()) {
                        if(secondOpt != null && secondOpt.isEnabled() && rule.getRule() == OptionRestrictionPO.Type.INCOMPATIBLE) {
                            errors.rejectValue(String.format(optionsField, i), "options.incompatible");
                            errors.rejectValue(String.format(optionsField, j), "options.incompatible");
                            return;
                        }
                        else if(secondOpt == null && rule.getRule() == OptionRestrictionPO.Type.REQUIRES) {
                            errors.rejectValue(String.format(optionsField, i), "options.requires");
                            errors.rejectValue(String.format(optionsField, j), "options.requires");
                            return;
                        }
                    }
                }
            }
        }
    }
}
