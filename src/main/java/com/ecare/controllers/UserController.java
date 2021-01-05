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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;

    @Autowired
    PasswordEncoder passwordEncoder;

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
    public String getSignUp(ModelMap model,
                            @RequestParam(value = "error", required = false) String error) {
        model.addAttribute("current_action", "register");
        model.addAttribute("current_action_title", "Registration");
        if (error != null) {
            model.addAttribute("error", "Invalid username or password!" + error);
        }
        return "Profile";
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public String submitSignUp(ModelMap model,
                               @RequestParam(value = "firstName", required = true) String name,
                               @RequestParam(value = "lastName", required = true) String lastName,
                               @RequestParam(value = "email", required = true) String email,
                               @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
                               @RequestParam(value = "password1", required = true) String password1,
                               @RequestParam(value = "password2", required = true) String password2,
                               @RequestParam(value = "passport", required = true) String passport,
                               @RequestParam(value = "address", required = true) String address,
                               @RequestParam(value = "birthDate", required = true) String birthDate
    ) {
        model.addAttribute("current_action", "register");
        model.addAttribute("current_action_title", "Registration");

        UserPO newUser = new UserPO();
        try {
            newUser.setName(name);
            newUser.setLastName(lastName);
            newUser.setEmail(email);
            if (!password1.equals(password2))
                throw new Exception("Passwords not match");
            newUser.setPassport(passwordEncoder.encode(password1));
            newUser.setPassport(passport);
            newUser.setAddress(address);
            newUser.setDate(Date.valueOf(birthDate));
            userService.save(newUser);
        } catch (Exception e){
            model.addAttribute("error", e.getMessage());
            model.addAttribute("user", newUser);
            return "Profile";
        }

        return "/";
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
