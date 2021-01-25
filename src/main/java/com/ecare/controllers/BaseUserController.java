package com.ecare.controllers;

import com.ecare.dao.Dao;
import com.ecare.models.UserPO;
import com.ecare.services.AuthService;
import com.ecare.services.ContractService;
import com.ecare.services.UserService;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.ModelMap;

@Getter
public class BaseUserController {

    private static Logger logger = LogManager.getLogger(BaseUserController.class);

    @Autowired
    protected UserService userService;

    @Autowired
    protected ContractService contractService;

    @Autowired
    protected AuthService authService;

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Getter
    public enum Type {
        Profile("Profile", "profile"),
        Registration("Registration", "register"),
        AdminRegistration("Registration", "");

        String title;
        String action;

        Type(String title, String action) {
            this.title = title;
            this.action = action;
        }
    }

    public void prepare(ModelMap model, Type type, Long idSuffixAction) {
        logger.debug(String.format("Preparing basic user mode: action_type=%s idSuffix=%d", type.toString(), idSuffixAction));
        model.addAttribute("current_action", type.getAction() + (idSuffixAction!=null ? idSuffixAction : "") );
        model.addAttribute("current_action_title", type.getTitle());
        model.addAttribute("defaultAuthority", UserPO.Authority.defaultAuthority);
        model.addAttribute("availableAuthorities", UserPO.Authority.stringMap);
    }

    public void prepare(ModelMap model, Type type) {
        prepare(model, type, null);
    }

    public void setSuccess(ModelMap model, String successText) {
        logger.debug("Setting success status: " + successText);
        model.addAttribute("success", successText);
    }
}
