package com.ecare.validators;

import com.ecare.models.PlanPO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PlanValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return PlanPO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        final String[] fields = {
                "name",
                "price",
        };

        ValidatorUtils.checkEmptiness(fields, errors);

        PlanPO plan = (PlanPO) target;

        ValidatorUtils.priceNotNegative("price", plan.getPrice(), errors);

        ValidatorUtils.checkOptions(plan.getOptions(), errors);
    }
}
