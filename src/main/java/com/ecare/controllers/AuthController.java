package com.ecare.controllers;

import com.ecare.models.UserPO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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

        prepare(model, Type.Registration);
        return "Profile";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String submitSignUp(ModelMap model,
                               @RequestParam(value = "firstName", required = true) String name,
                               @RequestParam(value = "lastName", required = true) String lastName,
                               @RequestParam(value = "email", required = true) String email,
                               @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
                               @RequestParam(value = "password1", required = true) String password1,
                               @RequestParam(value = "password2", required = true) String password2,
                               @RequestParam(value = "passport", required = true) String passport,
                               @RequestParam(value = "address", required = true) String address,
                               @RequestParam(value = "birthDate", required = true) String birthDate,
                               @RequestParam(value = "authority", required = true) UserPO.Authority authority
    ) {
        prepare(model, Type.Registration);

        UserPO newUser = new UserPO();
        try {
            newUser.setName(name);
            newUser.setLastName(lastName);
            newUser.setEmail(email);
            if (!password1.equals(password2))
                throw new Exception("Passwords not match");
            newUser.setPasswordHash(passwordEncoder.encode(password1));
            newUser.setPassport(passport);
            newUser.setAddress(address);
            newUser.setDate(Date.valueOf(birthDate));
            newUser.setAuthority(authority);
            userService.save(newUser);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("user", newUser);
            return "Profile";
        }

        return "";
    }
}
