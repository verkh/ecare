package com.ecare.controllers;

import com.ecare.models.UserPO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;

@Controller
public class AuthController extends BaseUserController {

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
        model.addAttribute("user", new UserPO());
        prepare(model, Type.Registration);
        return "Profile";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String submitSignUp(ModelMap model,
                               @ModelAttribute(value="user") UserPO newUser
    ) {
        prepare(model, Type.Registration);

        try {
            userService.save(newUser);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("user", newUser);
            return "Profile";
        }

        return "";
    }
}
