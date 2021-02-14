package com.ecare.controllers;

import com.ecare.dto.Option;
import com.ecare.dto.OptionRulesConfigurer;
import com.ecare.services.OptionsService;
import com.ecare.validators.OptionValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Controller handles buisness logic of plan options management
 */
@Controller
@SessionAttributes("option")
public class OptionController {

    private static Logger logger = LogManager.getLogger(OptionController.class);

    @Autowired
    private OptionValidator optionValidator;

    @Autowired
    private OptionsService optionsService;

    /**
     * Configuring options JSP page
     * @param model model for JSP page
     * @param currentPage current page number (pagination)
     * @param deprecated set flag
     * @param id if action is "delete" then it's an id of option to be deleterd
     * @return the name of JSP file
     */
    @RequestMapping(value = "/administration/options")
    public String getOptions(ModelMap model,
                             @RequestParam(value = "currentPage", required = false) Integer currentPage,
                             @RequestParam(value = "deprecated", required = false) Boolean deprecated,
                             @RequestParam(value = "id", required = false) Long id
    ) {
        logger.trace("Configuring options page...");
        if (deprecated != null && id != null) {
            optionsService.setDeprecated(id, deprecated);
            return "redirect:/administration/options";
        }
        Utils.pagination(optionsService, model, currentPage, "options");
        return "administration/Options";
    }

    /**
     * Configuring JSP page for specific option
     * @param model model for JSP page
     * @param id an id of needed option
     * @return the name of JSP file
     */
    @RequestMapping(value = "/administration/options/{id}", method = RequestMethod.GET)
    @Transactional
    public String getOption(ModelMap model, @PathVariable long id) {
        logger.trace("Configuring options page with option id=" + id);
        Option currentOption = optionsService.get(id).get();
        OptionRulesConfigurer option = new OptionRulesConfigurer(currentOption, optionsService.getAll());
        model.addAttribute("option", option);
        return "administration/Option";
    }

    /**
     * Configuring JSP page for creation of new option
     * @param model model for JSP page
     * @return the name of JSP file
     */
    @RequestMapping(value = "/administration/options/new", method = RequestMethod.GET)
    @Transactional
    public String getNewOption(ModelMap model) {
        logger.trace("Configuring new option page...");
        model.addAttribute("option", new OptionRulesConfigurer(Option.builder().build(), optionsService.getAll()));
        return "administration/Option";
    }

    /**
     * Deletes rule from option restrictions list
     * @param model model for JSP page
     * @param option current processing option
     * @param index index of rule in the list
     * @return the name of JSP file
     */
    @RequestMapping(params = "remove_rule", value = {"/administration/options/{id}", "/administration/options/new"}, method = RequestMethod.POST)
    public String deleteRule(ModelMap model, @ModelAttribute("option") OptionRulesConfigurer option,
                             @RequestParam(value = "remove_rule", required = false) Long index
    ) {
        if (index != null) {
            logger.debug(String.format("Removing rule with for option %s with id=%d index=%1",
                    option.getValue(), option.getValue().getId(), index));
            option.removeRule(index);
        }
        return "administration/Option";
    }

    /**
     * Adding rule into option restrictions list
     * @param model model for JSP page
     * @param option current option
     * @return the name of JSP file
     */
    @RequestMapping(params = "add_rule", value = {"/administration/options/{id}", "/administration/options/new"}, method = RequestMethod.POST)
    public String addRule(ModelMap model, @ModelAttribute("option") OptionRulesConfigurer option) {
        logger.debug(String.format("Adding new rule for option %s with id=%d", option.getValue(), option.getValue().getId()));
        option.addNewRule();
        return "administration/Option";
    }

    /**
     * Saves new option into database
     * @param option current option
     * @param result validation error container
     * @return the name of JSP file
     */
    @RequestMapping(value = {"/administration/options/{id}", "/administration/options/new"}, method = RequestMethod.POST)
    public String saveNewOption(@ModelAttribute("option") OptionRulesConfigurer option,
                                BindingResult result
    ) {
        logger.trace(String.format("Saving new option %s with id=%d", option.getValue(), option.getValue().getId()));
        optionValidator.validate(option, result);
        if(result.hasErrors())
            return "administration/Option";

        Option optionForSave = option.getValue().toBuilder().build();
        if(optionForSave.getId() == null) {
            optionForSave.setRestrictions(new ArrayList<>());
            optionForSave = optionsService.save(optionForSave);
            option.setId(optionForSave.getId());
        }
        optionForSave.setRestrictions(option.getRestrictions());
        optionsService.update(optionForSave);
        logger.trace(String.format("Option saved %s with id=%d", option.getValue(), option.getValue().getId()));
        return "redirect:/administration/options";
    }

    /**
     * Checks options in database that marked with "deprecated flag" every 30 seconds.
     * If options is also not related to any contract it would be deleted from database.
     * @return number of deleted options
     */
    @Scheduled(fixedRate = 1000*30) // every 30 seconds
    public void deleteUnusedDeprecatedOptions() {
        logger.trace("Running check of deprecated options...");
        int deletedN = optionsService.deleteUnusedDeprecatedOptions();
        logger.trace(String.format("Removed %d unused deprecated options", deletedN));
    }
}
