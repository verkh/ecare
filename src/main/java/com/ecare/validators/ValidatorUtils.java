package com.ecare.validators;

import com.ecare.dto.Option;
import com.ecare.dto.OptionRestriction;
import com.ecare.models.OptionPO;
import com.ecare.models.OptionRestrictionPO;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import java.util.List;
import java.util.stream.IntStream;

/**
 * Validator utils that are used in other validators
 */
public class ValidatorUtils {

    /**
     * Checks that passed fields are not empty
     * @param fieldList list of fields to check
     * @param errors container of errors
     */
    public static void checkEmptiness(String[] fieldList, Errors errors) {
        for(final String field : fieldList)
            ValidationUtils.rejectIfEmpty(errors, field, "property.empty");
    }

    /**
     * Checks that price is not negative
     * @param field field name
     * @param d price
     * @param errors container of errors
     */
    public static void priceNotNegative(String field, Double d, Errors errors) {
        if(d < 0)
            errors.rejectValue(field, "price.negative");
    }

    /**
     * Checks that options are compatible or required ones is also selected
     * @param options selected options
     * @param errors container of errors
     */
    public static void checkOptions(List<Option> options, Errors errors) {

        if(options.isEmpty()) {
            errors.rejectValue("options", "plan.noOptions");
            return;
        }

        final String optionsField = "options[%d]";

        for(int i = 0; i < options.size(); i++) {
            final Option option = options.get(i);

            for (final OptionRestriction rule : option.getRestrictions()) {

                if(!option.isEnabled()) continue;

                for(int j = 0; j < options.size(); j++){
                    Option secondOpt = options.get(j);
                    if(secondOpt.getId() == rule.getOptionId2()) {
                        if(secondOpt != null && secondOpt.isEnabled() && rule.getType() == OptionRestrictionPO.Type.INCOMPATIBLE) {
                            errors.rejectValue(String.format(optionsField, i), "options.incompatible");
                            errors.rejectValue(String.format(optionsField, j), "options.incompatible");
                            return;
                        }
                        else if(secondOpt == null && rule.getType() == OptionRestrictionPO.Type.REQUIRES) {
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
