package com.ecare.controllers;

import com.ecare.models.PlanPO;
import com.ecare.models.UserPO;
import com.ecare.services.AuthService;
import com.ecare.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController extends BaseUserController {

    /**
     * Show user's profile
     * @param model - current page model
     * @return the name of JSP
     */
    @RequestMapping(value="/profile")
    public String getProfile(ModelMap model) { return profilePrepare(model, Type.Registration); }

    /**
     * Show
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value="/administration/users/{id}")
    public String getUser(ModelMap model, @PathVariable long id) { return profilePrepare(model, Type.AdminRegistration); }

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
    private String profilePrepare(ModelMap model, Type type) {
        UserPO currentUser = authService.getCurrentUser();
        model.addAttribute("user", currentUser);
        prepare(model, type);
        return "Profile";
    }
}
