package com.ecare.validators;

import com.ecare.models.ContractPO;
import com.ecare.models.UserPO;
import com.ecare.services.AuthService;
import com.ecare.services.ContractService;
import com.ecare.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Calendar;
import java.util.GregorianCalendar;

@Component
public class ContractValidator implements Validator {

    /**
     * General Email Regex (RFC 5322 Official Standard)
     */
    private static final String emailRegex =
            "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    @Override
    public boolean supports(Class<?> clazz) {
        return ContractPO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        final String[] fields = {
                "user.name",
                "user.lastName",
                "user.email",
                "user.passwordHash",
                "user.passport",
                "user.address",
                "user.date"
        };

        ValidatorUtils.checkEmptiness(fields, errors);

        ContractPO contract = (ContractPO) target;
        UserPO user = contract.getUser();

        if (user.getName().length() < 3)
            errors.rejectValue("user.name", "user.name.length");
        if (user.getLastName().length() < 3)
            errors.rejectValue("user.lastName", "user.lastName.length");
        if (!user.getEmail().matches(emailRegex))
            errors.rejectValue("user.email", "user.email.wrong");

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(user.getDate());

        if (Calendar.getInstance().get(Calendar.YEAR) - calendar.get(Calendar.YEAR) < 18)
            errors.rejectValue("user.date", "user.date.tooYoung");

        ValidatorUtils.checkOptions(contract.getOptions(), errors);
    }
}
