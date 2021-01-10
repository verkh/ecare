package com.ecare.controllers;

import com.ecare.models.OptionPO;
import com.ecare.models.PlanPO;
import com.ecare.models.UserPO;
import com.ecare.services.AuthService;
import com.ecare.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("user")
public class UserController extends BaseUserController {

    /**
     * Show user's profile
     * @param model - current page model
     * @return the name of JSP
     */
    @RequestMapping(value="/profile", method = RequestMethod.GET)
    public String getProfile(ModelMap model) {
        return profilePrepare(model, Type.Profile, authService.getCurrentUser(), null);
    }

    @RequestMapping(value= {"/profile","/administration/users/{id}"}, method = RequestMethod.POST)
    public String saveProfile(ModelMap model, @ModelAttribute("user") UserPO user, BindingResult result) {
        userService.update(user);
        if(result.hasErrors()) {

        }
        return "Profile";
    }

    /**
     * Show
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value="/administration/users/{id}", method = RequestMethod.GET)
    public String getUser(ModelMap model, @PathVariable long id) {
        return profilePrepare(model, Type.AdminRegistration, userService.get(id).get(), id);
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
        model.addAttribute("user", currentUser);
        if (id == null) {
            prepare(model, type);
        } else {
            prepare(model, type, id);
        }
        return "Profile";
    }
}
