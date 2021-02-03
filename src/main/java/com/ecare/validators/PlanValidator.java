package com.ecare.validators;

import com.ecare.dto.Plan;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator of plans
 */
@Component
public class PlanValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Plan.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        final String[] fields = {
                "name",
                "price",
        };

        ValidatorUtils.checkEmptiness(fields, errors);

        Plan plan = (Plan) target;

        ValidatorUtils.priceNotNegative("price", plan.getPrice(), errors);

        ValidatorUtils.checkOptions(plan.getOptions(), errors);
    }
}
