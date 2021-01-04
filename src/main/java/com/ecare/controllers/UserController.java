package com.ecare.controllers;

import com.ecare.models.UserPO;
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

    @RequestMapping(value="/SignIn", method = RequestMethod.GET)
    public String getSignIn(ModelMap model,
        @RequestParam(value = "error", required = false) String error)
    {
        if (error != null) {
            model.addAttribute("error", "Invalid username or password!" + error);
        }

        return "SignIn";
    }

//    @RequestMapping(value="/login", method = RequestMethod.POST)
//    public String signIn(ModelMap model)
//    {
//        System.out.println(model);
//        return "Plans";
//    }

    @RequestMapping(value="/SignUp", method = RequestMethod.GET)
    public String getSignUp() {
        System.out.println("Im here one!");
        return "SignUp";
    }

    @RequestMapping(value="/SignUp", method = RequestMethod.POST)
    public String submitSignUp() {
        System.out.println("Im here!");
        return "SignUpFinish";
    }

    @RequestMapping(value="/SignUpFinish")
    public String getSignUpFinish() {
        System.out.println("At last here");
        return "SignUpFinish";
    }

    @RequestMapping(value="/Profile")
    public String getProfile(ModelMap model) {
        return "Profile";
    }

    @RequestMapping(value="/administration/Users")
    public String getUsers(ModelMap model,
        @RequestParam(value = "currentPage", required = false) Integer currentPage
    ) {
        Utils.pagination(userService, model, currentPage, "users");
        return "administration/Users";
    }

    @RequestMapping(value="/administration/Users/{id}")
    public String getPlan(ModelMap model, @PathVariable long id) {
        UserPO user = userService.get(id).get();
        model.addAttribute("user", user);
        return "Profile";
    }
}
