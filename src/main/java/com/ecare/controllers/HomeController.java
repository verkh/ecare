package com.ecare.controllers;

import com.ecare.models.PlanPO;
import com.ecare.services.PlanService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Root controller that handles home and about pages
 */
@Controller
public class HomeController {

    private static Logger logger = LogManager.getLogger(HomeController.class);

    /**
     * Describes three basic plans that are viewed on the home page
     */
    @Getter
    public enum BasicPlans {
        DICTATOR("Dictator"),
        PEASANT("Peasant"),
        EXILE("Exile");

        private String value;
        BasicPlans(String v) { this.value = v; }
    }
    @Autowired
    PlanService planService;

    /**
     * Configures home JSP page. Loads needed data.
     * @param model model for JSP page
     * @return name of the file
     */
    @RequestMapping(value = "")
    public String getHome(ModelMap model) {

        logger.trace("Configuring home page...");

        PlanPO dictator = planService.findByName(BasicPlans.DICTATOR.getValue());
        PlanPO peasant = planService.findByName(BasicPlans.PEASANT.getValue());
        PlanPO exile = planService.findByName(BasicPlans.EXILE.getValue());

        model.addAttribute(BasicPlans.DICTATOR.getValue(), dictator);
        model.addAttribute(BasicPlans.PEASANT.getValue(), peasant);
        model.addAttribute(BasicPlans.EXILE.getValue(), exile);

        return "index";
    }

    /**
     * @return name of about page
     */
    @RequestMapping(value = "/about")
    public String getAbout() {
        logger.trace("Configuring about page...");
        return "About";
    }
}
