package com.ecare.controllers;

import com.ecare.models.ContractPO;
import com.ecare.models.OptionPO;
import com.ecare.models.PlanPO;
import com.ecare.models.UserPO;
import com.ecare.services.AuthService;
import com.ecare.services.ContractService;
import com.ecare.services.OptionsService;
import com.ecare.services.PlanService;
import com.ecare.validators.PlanValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes(names = "Plan")
public class PlansController {

    @Autowired
    PlanService planService;

    @Autowired
    ContractService contractService;

    @Autowired
    protected AuthService authService;

    @Autowired
    OptionsService optionsService;

    @Autowired
    PlanValidator planValidator;

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
    public String getPlans(ModelMap model,
                           @RequestParam(value = "currentPage", required = false) Integer currentPage,
                           @RequestParam(value = "delete", required = false) Long deleteId
    ) {
        if(deleteId != null)
            planService.delete(new PlanPO(deleteId));
        Utils.pagination(planService, model, currentPage, "tariffs");
        return "administration/Tariffs";
    }

    @RequestMapping(value = "/administration/tariffs/{id}", method = RequestMethod.GET)
    public String getTariff(ModelMap model, @PathVariable long id) {
        PlanPO plan = planService.get(id).get();

        List<OptionPO> options = Utils.prepareOptions(plan.getOptions(), optionsService.getAll());
        plan.setOptions(options);
        model.addAttribute("current_action", id);
        model.addAttribute("Plan", plan);
        return "administration/Tariff";
    }

    @RequestMapping(value = "/administration/tariffs/new", method = RequestMethod.GET)
    public String getTariff(ModelMap model) {
        PlanPO plan = new PlanPO();

        List<OptionPO> options = Utils.prepareOptions(plan.getOptions(), optionsService.getAll());
        plan.setOptions(options);
        model.addAttribute("current_action", "new");
        model.addAttribute("Plan", plan);
        return "administration/Tariff";
    }


    @RequestMapping(value = {"/administration/tariffs/{id}", "/administration/tariffs/new"}, method = RequestMethod.POST)
    public String saveTariff(ModelMap model,
                             @ModelAttribute(value="Plan") PlanPO plan, BindingResult result
    ){
        planValidator.validate(plan, result); // validation should be done before filtering of disabled options
        if(result.hasErrors())
            return "administration/Tariff";

        PlanPO planForSave = plan.getId() != null? planService.get(plan.getId()).get() : new PlanPO(plan);
        planForSave.getOptions().clear();
        for(final OptionPO opt : plan.getOptions()) {
            if(opt.isEnabled())
                planForSave.getOptions().add(opt);
        }

        if(plan.getId() != null)
            planService.update(planForSave);
        else
            planService.save(planForSave);

        model.addAttribute("Plan", plan);
        return "redirect:/administration/tariffs";
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
        contract.setOptions(plan.getOptions());

        contractService.save(contract);

        return "redirect:/contract";
    }
}