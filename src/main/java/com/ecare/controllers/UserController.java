package com.ecare.controllers;

import com.ecare.models.UserPO;
import com.ecare.services.AuthService;
import com.ecare.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;

    @RequestMapping(value="/auth", method = RequestMethod.GET)
    public String getSignIn(ModelMap model,
        @RequestParam(value = "error", required = false) String error)
    {
        if (error != null) {
            model.addAttribute("error", "Invalid username or password!" + error);
        }

        return "SignIn";
    }

    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String getSignUp(ModelMap model) {
        model.addAttribute("current_action", "register");
        model.addAttribute("current_action_title", "Registration");
        return "Profile";
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public String submitSignUp(ModelMap model) {
        model.addAttribute("current_action", "register");
        model.addAttribute("current_action_title", "Registration");
        return "Profile";
    }

    @RequestMapping(value="/profile")
    public String getProfile(ModelMap model) {
        UserPO currentUser = authService.getCurrentUser();
        model.addAttribute("user", currentUser);
        model.addAttribute("current_action", "profile");
        model.addAttribute("current_action_title", "Profile");
        return "Profile";
    }

    @RequestMapping(value="/administration/users")
    public String getUsers(ModelMap model,
        @RequestParam(value = "currentPage", required = false) Integer currentPage
    ) {
        Utils.pagination(userService, model, currentPage, "users");
        return "administration/Users";
    }

    @RequestMapping(value="/administration/users/{id}")
    public String getPlan(ModelMap model, @PathVariable long id) {
        UserPO user = userService.get(id).get();
        model.addAttribute("user", user);
        model.addAttribute("current_action", "/administration/users/" + id);
        model.addAttribute("current_action_title", "Profile");
        return "Profile";
    }
}
