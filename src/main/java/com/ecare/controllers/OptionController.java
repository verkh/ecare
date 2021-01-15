package com.ecare.controllers;

import com.ecare.dto.Option;
import com.ecare.models.OptionPO;
import com.ecare.services.OptionsService;
import com.ecare.validators.OptionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@SessionAttributes("option")
public class OptionController {

    @Autowired
    OptionValidator optionValidator;

    @Autowired
    private OptionsService optionsService;

    @RequestMapping(value = "/administration/options")
    public String getOptions(ModelMap model,
                             @RequestParam(value = "currentPage", required = false) Integer currentPage,
                             @RequestParam(value = "delete", required = false) Long deleteId
    ) {
        if (deleteId != null) {
            optionsService.delete(new OptionPO(deleteId));
            return "redirect:/administration/options";
        }
        Utils.pagination(optionsService, model, currentPage, "options");
        return "administration/Options";
    }

    @RequestMapping(value = "/administration/options/{id}", method = RequestMethod.GET)
    @Transactional
    public String getOption(ModelMap model, @PathVariable long id) {
        OptionPO currentOption = optionsService.get(id).get();
        Option option = new Option(currentOption, optionsService.getAll());
        model.addAttribute("option", option);
        return "administration/Option";
    }

    @RequestMapping(value = "/administration/options/new", method = RequestMethod.GET)
    @Transactional
    public String getNewOption(ModelMap model) {
        model.addAttribute("option", new Option(new OptionPO(), optionsService.getAll()));
        return "administration/Option";
    }

    @RequestMapping(params = "remove_rule", value = {"/administration/options/{id}", "/administration/options/new"}, method = RequestMethod.POST)
    public String deleteRule(ModelMap model, @ModelAttribute("option") Option option,
                             @RequestParam(value = "remove_rule", required = false) Long index
    ) {
        if (index != null) option.removeRule(index);
        return "administration/Option";
    }

    @RequestMapping(params = "add_rule", value = {"/administration/options/{id}", "/administration/options/new"}, method = RequestMethod.POST)
    public String addRule(ModelMap model, @ModelAttribute("option") Option option) {
        option.addNewRule();
        return "administration/Option";
    }

    @RequestMapping(value = {"/administration/options/{id}", "/administration/options/new"}, method = RequestMethod.POST)
    public String saveNewOption(HttpServletRequest request,
                                ModelMap model,
                                @ModelAttribute("option") Option option,
                                BindingResult result
    ) {

        optionValidator.validate(option, result);
        if(result.hasErrors())
            return "administration/Option";

        OptionPO optionForSave = option.getValue();
        if(optionForSave.getId() == null) {
            optionsService.save(optionForSave);
            option.setId(optionForSave.getId());
        }
        optionForSave.setRestrictions(option.getRestrictions());
        optionsService.update(optionForSave);
        return "redirect:/administration/options";
    }
}
