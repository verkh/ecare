package com.ecare.validators;

import com.ecare.models.ContractPO;
import com.ecare.models.UserPO;
import com.ecare.services.ContractService;
import com.ecare.services.UserService;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator of new contracts
 * @see NewContractValidator
 */
@Component
public class NewContractValidator extends ContractValidator implements Validator {

    @Autowired
    protected ContractService contractService;
    @Autowired
    protected UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return ContractPO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        super.validate(target, errors);
        final String[] fields = {"phoneNumber"};

        ValidatorUtils.checkEmptiness(fields, errors);

        ContractPO contract = (ContractPO) target;
        UserPO user = contract.getUser();

        if (userService.findByEmail(user.getEmail()) != null)
            errors.rejectValue("user.email", "user.email.alreadyUsed");
        if (contractService.findByPhoneNumber(contract.getPhoneNumber()) != null)
            errors.rejectValue("phoneNumber", "user.phone.alreadyUsed");
    }
}
