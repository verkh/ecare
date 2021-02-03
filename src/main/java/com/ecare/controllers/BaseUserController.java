package com.ecare.controllers;

import com.ecare.dto.User;
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

/**
 * Basic class for user controlles which contains common methods
 */
@Getter
public class BaseUserController {

    private static Logger logger = LogManager.getLogger(BaseUserController.class);

    @Autowired
    protected ContractService contractService;

    @Autowired
    protected AuthService authService;

    @Autowired
    protected PasswordEncoder passwordEncoder;

    /**
     * The Type enum describes the actions that could be done with user controller.
     * AdminProfileView action is empty since it uses just id to extend the original path
     */
    @Getter
    public enum Type {
        Profile("Profile", "profile"),
        Registration("Registration", "register"),
        AdminProfileView("Registration", "");

        String title;
        String action;

        Type(String title, String action) {
            this.title = title;
            this.action = action;
        }
    }

    /**
     * Configures common information in model for profile JSP view
     * @param model model for JSP page
     * @param type current action type
     * @param idSuffixAction id of user if applicable
     */
    public void prepare(ModelMap model, Type type, Long idSuffixAction) {
        logger.debug(String.format("Preparing basic user mode: action_type=%s idSuffix=%d", type.toString(), idSuffixAction));
        model.addAttribute("current_action", type.getAction() + (idSuffixAction!=null ? idSuffixAction : "") );
        model.addAttribute("current_action_title", type.getTitle());
        model.addAttribute("defaultAuthority", UserPO.Authority.defaultAuthority);
        model.addAttribute("availableAuthorities", UserPO.Authority.stringMap);
    }

    /**
     * Configures common information in model for profile JSP view if user id is not applicable
     * @param model model for JSP page
     * @param type current action type
     */
    public void prepare(ModelMap model, Type type) {
        prepare(model, type, null);
    }

    /**
     * @param model model for JSP page
     * @param successText text of status
     */
    public void setSuccess(ModelMap model, String successText) {
        logger.debug("Setting success status: " + successText);
        model.addAttribute("success", successText);
    }
}
