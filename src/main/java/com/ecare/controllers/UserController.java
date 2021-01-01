package com.ecare.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    @RequestMapping(value="/SignIn")
    public String getSignIn() {
        return "SignIn";
    }

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
}
