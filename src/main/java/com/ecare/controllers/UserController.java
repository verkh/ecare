package com.ecare.controllers;

import com.ecare.models.SubscriberPO;
import com.ecare.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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

    @RequestMapping(value="/Users")
    public String getUsers(ModelMap model,
        @RequestParam(value = "currentPage", required = false) Integer currentPage
    ) {
        final int recordsPerPage = 15;

        long rows = userService.count();
        int nOfPages = (int)(rows / recordsPerPage);

        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }

        if(currentPage == null)
            currentPage = 1;

        List<SubscriberPO> users = userService.get((currentPage-1)*recordsPerPage, recordsPerPage);

        model.addAttribute("users", users);

        model.addAttribute("noOfPages", nOfPages);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("recordsPerPage", recordsPerPage);

        return "Users";
    }
}
