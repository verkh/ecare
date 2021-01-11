package com.ecare.controllers;

import com.ecare.models.ContractPO;
import com.ecare.models.UserPO;
import com.ecare.validators.ContractValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes(names="contract", types = ContractPO.class)
public class UserController extends BaseUserController {

    @Autowired
    ContractValidator contractValidator;

    /**
     * Show user's profile
     * @param model - current page model
     * @return the name of JSP
     */
    @RequestMapping(value="/profile", method = RequestMethod.GET)
    public String getProfile(ModelMap model) {
        return profilePrepare(model, Type.Profile, authService.getCurrentUser(), null);
    }

    @RequestMapping(value= {"/profile","/administration/users/{user_id}"}, method = RequestMethod.POST)
    public String saveProfile(ModelMap model, @ModelAttribute(value="contract") ContractPO contract,
                              BindingResult result)
    {
        contractValidator.validate(contract, result);
        if(!result.hasErrors()) {
            String password = contract.getUser().getRawPassword();
            if(!password.isEmpty())
                contract.getUser().setPasswordHash(passwordEncoder.encode(password));
            contractService.update(contract);
            setSuccess(model, "Successfully updated!");
        }
        return "Profile";
    }

    /**
     * Show
     * @param model
     * @param user_id
     * @return
     */
    @RequestMapping(value="/administration/users/{user_id}", method = RequestMethod.GET)
    public String getUser(ModelMap model, @PathVariable long user_id) {
        return profilePrepare(model, Type.AdminRegistration, userService.get(user_id).get(), user_id);
    }

    /**
     * Show users tables with pagination
     * @param model - current page model
     * @param currentPage - page number
     * @return the name of JSP
     */
    @RequestMapping(value="/administration/users")
    public String getUsers(ModelMap model,
        @RequestParam(value = "currentPage", required = false) Integer currentPage
    ) {
        Utils.pagination(userService, model, currentPage, "users");
        return "administration/Users";
    }

    /**
     * Base method for showing user profile
     * @param model - current page model
     * @param type - which type of profile is used
     * @return the name of JSP
     */
    private String profilePrepare(ModelMap model, Type type, UserPO currentUser, Long id) {
        model.addAttribute("contract", currentUser.getContract());
        if (id == null) {
            prepare(model, type);
        } else {
            prepare(model, type, id);
        }
        return "Profile";
    }
}
