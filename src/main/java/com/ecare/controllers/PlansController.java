package com.ecare.controllers;

import com.ecare.models.PlanPO;
import com.ecare.services.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class PlansController {

    @Autowired
    PlanService planService;

    @RequestMapping(value="/Plans")
    public String getPlans(ModelMap model) {
        try {
            List<PlanPO> plans = planService.getAll();
            System.out.println("PLANS: " + plans.size() + " options:" + plans.get(0).getOptions().size());
            model.addAttribute("Plans", plans);
        }
        catch (Exception e)
        {
            model.addAttribute("error", e.toString());
            model.addAttribute("errorStack", e.getStackTrace().toString());
        }
        return "Plans";
    }

    @RequestMapping(value="/Plans/{id}")
    public String getPlan(ModelMap model, @PathVariable long id) {
        try {
            PlanPO plan = planService.get(id).get();
            System.out.println(plan.getId() + " " + plan.getName() + " " + plan.getOptions().size());
            model.addAttribute("Plan", plan);
        }
        catch (Exception e)
        {
            model.addAttribute("error", e.toString());
            model.addAttribute("errorStack", e.getStackTrace().toString());
        }
        return "Plan";
    }

//    @RequestMapping(value="/Plans/{id}")
//    @PostAuthorize("hasPermission(returnObject, 'WRITE')")
//    public String setupPlan(ModelMap model, @PathVariable long id) {
//        System.out.println("Im here!");
//        return "SignUpFinish";
//    }
}