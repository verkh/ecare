package com.ecare.controllers;

import com.ecare.models.OptionPO;
import com.ecare.models.PlanPO;
import com.ecare.services.OptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class OptionController {

    @Autowired
    private OptionsService optionsService;

    @RequestMapping(value="/administration/options")
    public String getOptions(ModelMap model,
                             @RequestParam(value = "currentPage", required = false) Integer currentPage
    ){
        Utils.pagination(optionsService, model, currentPage, "options");
        return "administration/Options";
    }

    @RequestMapping(value="/administration/options/{id}")
    public String getOption(ModelMap model, @PathVariable long id) {
        try {
            OptionPO option = optionsService.get(id).get();
            model.addAttribute("option", option);
        } catch (Exception e) {
            model.addAttribute("error", e.toString());
            model.addAttribute("errorStack", e.getStackTrace().toString());
        }
        return "administration/Option";
    }

    @RequestMapping(value="/administration/options/{id}", method = RequestMethod.POST)
    public String getOption(ModelMap model, @PathVariable long id, @ModelAttribute("option") OptionPO option) {
        try {
            optionsService.update(option);
        } catch (Exception e) {
            model.addAttribute("error", e.toString());
            model.addAttribute("errorStack", e.getStackTrace().toString());
        }
        return "administration/Option";
    }
}
