package com.ecare.controllers;

import com.ecare.models.PlanPO;
import com.ecare.services.PlanService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

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

    @RequestMapping(value = "")
    public String getHome(ModelMap model) {

        PlanPO dictator = planService.findByName(BasicPlans.DICTATOR.getValue());
        PlanPO peasant = planService.findByName(BasicPlans.PEASANT.getValue());
        PlanPO exile = planService.findByName(BasicPlans.EXILE.getValue());

        model.addAttribute(BasicPlans.DICTATOR.getValue(), dictator);
        model.addAttribute(BasicPlans.PEASANT.getValue(), peasant);
        model.addAttribute(BasicPlans.EXILE.getValue(), exile);

        return "index";
    }

    @RequestMapping(value = "/about")
    public String getAbout() {
        return "About";
    }
}
