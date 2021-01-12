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
                             @RequestParam(value = "currentPage", required = false) Integer currentPage,
                             @RequestParam(value = "delete", required = false) Long deleteId
    ){
        if(deleteId != null) {
            optionsService.delete(new OptionPO(deleteId));
        }
        Utils.pagination(optionsService, model, currentPage, "options");
        return "administration/Options";
    }

    @RequestMapping(value="/administration/options/{id}", method = RequestMethod.GET)
    public String getOption(ModelMap model, @PathVariable long id) {
        OptionPO option = optionsService.get(id).get();
        model.addAttribute("option", option);
        return "administration/Option";
    }

    @RequestMapping(value="/administration/options/new", method = RequestMethod.GET)
    public String getNewOption(ModelMap model) {
        model.addAttribute("option", new OptionPO());
        return "administration/Option";
    }

    @RequestMapping(value="/administration/options/new", method = RequestMethod.POST)
    public String saveNewOption(ModelMap model, @ModelAttribute("option") OptionPO option) {
        optionsService.save(option);
        return "redirect:/administration/options/"+option.getId();
    }

    @RequestMapping(value="/administration/options/{id}", method = RequestMethod.POST)
    public String saveOption(ModelMap model, @PathVariable long id, @ModelAttribute("option") OptionPO option) {
        optionsService.update(option);
        return "administration/Option";
    }
}
