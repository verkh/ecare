package com.ecare.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

public class ValidatorUtils {

    public static void checkEmptiness(String[] fieldList, Errors errors) {
        for(final String field : fieldList)
            ValidationUtils.rejectIfEmpty(errors, field, "property.empty");
    }

    public static void priceNotNegative(String field, Double d, Errors errors) {
        if(d < 0)
            errors.rejectValue(field, "price.negative");
    }
}
