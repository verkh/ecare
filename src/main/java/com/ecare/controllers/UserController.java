package com.ecare.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
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
}
