package com.ecare.controllers;

import com.ecare.models.ContractPO;
import com.ecare.models.PlanPO;
import com.ecare.models.UserPO;
import com.ecare.services.AuthService;
import com.ecare.services.ContractService;
import com.ecare.services.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PlansController {

    @Autowired
    PlanService planService;

    @Autowired
    ContractService contractService;

    @Autowired
    protected AuthService authService;

    @RequestMapping(value = "/plans")
    public String getPlans(ModelMap model) {
        List<PlanPO> plans = planService.getAll();
        model.addAttribute("Plans", plans);
        return "Plans";
    }

    @RequestMapping(value = "/plans/{id}")
    public String getPlan(ModelMap model, @PathVariable long id) {
        try {
            PlanPO plan = planService.get(id).get();
            System.out.println(plan.getId() + " " + plan.getName() + " " + plan.getOptions().size());
            model.addAttribute("Plan", plan);
        } catch (Exception e) {
            model.addAttribute("error", e.toString());
            model.addAttribute("errorStack", e.getStackTrace().toString());
        }
        return "Plan";
    }

    @RequestMapping(value = "/administration/tariffs")
    public String getUsers(ModelMap model,
                           @RequestParam(value = "currentPage", required = false) Integer currentPage
    ) {
        Utils.pagination(planService, model, currentPage, "tariffs");
        return "administration/Tariffs";
    }

    @RequestMapping(value = "/plans/plan")
    public String applyPlan(ModelMap model,
                           @RequestParam(value = "apply", required = false) Long id
    ) {
        UserPO user = authService.getCurrentUser();
        if(user == null)
            return "redirect:/auth";

        PlanPO plan = planService.get(id).get();
        ContractPO contract = user.getContract();
        contract.setPlan(plan);

        contractService.save(contract);

        return "redirect:/profile";
    }
}