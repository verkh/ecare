package com.ecare.controllers;

import com.ecare.models.ContractPO;
import com.ecare.models.UserPO;
import com.ecare.services.PlanService;
import com.ecare.validators.NewContractValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("availablePlans")
public class AuthController extends BaseUserController {

    @Autowired
    private NewContractValidator newContractValidator;

    @Autowired
    private PlanService planService;

    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public String getSignIn(ModelMap model,
                            @RequestParam(value = "error", required = false) String error) {
        if (error != null) {
            model.addAttribute("error", "Invalid username or password!" + error);
        }

        return "SignIn";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getSignUp(ModelMap model,
                            @RequestParam(value = "error", required = false) String error) {
        model.addAttribute("contract", new ContractPO());
        model.addAttribute("availablePlans", planService.getAll());
        prepare(model, Type.Registration);
        return "Profile";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String submitSignUp(ModelMap model,
                               @ModelAttribute(value="contract") ContractPO newContract,
                               BindingResult result
    ) {
        prepare(model, Type.Registration);
        UserPO newUser = newContract.getUser();
        newUser.setPasswordHash(passwordEncoder.encode(newUser.getRawPassword()));
        newContractValidator.validate(newContract, result);

        if(result.hasErrors())
            return "Profile";

        contractService.save(newContract);
        setSuccess(model, "You've been successfully registered!");

        return "Profile";
    }
}
