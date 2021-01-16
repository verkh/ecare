package com.ecare.controllers;

import com.ecare.models.ContractPO;
import com.ecare.models.OptionPO;
import com.ecare.models.UserPO;
import com.ecare.services.PlanService;
import com.ecare.validators.ContractValidator;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@SessionAttributes(names= {"contract", "optionsContract", "availablePlans"}, types = ContractPO.class)
public class UserController extends BaseUserController {

    @Autowired
    ContractValidator contractValidator;

    @Autowired
    PlanService planService;

    /**
     * Show user's profile
     * @param model - current page model
     * @return the name of JSP
     */
    @RequestMapping(value="/profile", method = RequestMethod.GET)
    public String getProfile(ModelMap model,
                             @RequestParam(value = "block", required = false) Boolean block
    ){
        return profilePrepare(model, Type.Profile, authService.getCurrentUser(), block, null);
    }

    @RequestMapping(value="/contract", method = RequestMethod.GET)
    public String getContract(ModelMap model)
    {
        model.addAttribute("current_action", "contract");
        return contractPrepare(model, authService.getCurrentUser().getContract());
    }

    @RequestMapping(value="/administration/contracts/{contract_id}", method = RequestMethod.GET)
    public String getContractByID(ModelMap model, @PathVariable long contract_id)
    {
        model.addAttribute("current_action", contract_id);
        return contractPrepare(model, contractService.get(contract_id).get());
    }
    
    @RequestMapping(value= {"/contract", "/administration/contracts/{contract_id}"}, method = RequestMethod.POST)
    public String saveContract(ModelMap model,
                               @ModelAttribute(value="contract") ContractPO current,
                               @ModelAttribute(value="optionsContract") ContractPO contract,
                               BindingResult result
    ) {
        contractValidator.validate(contract, result);
        current.getOptions().clear();
        for(final OptionPO opt : contract.getOptions()) {
            if(opt.isEnabled())
                current.getOptions().add(opt);
        }
        contractService.update(current);
        setSuccess(model, "Successfully updated!");
        return "Contract";
    }

    @RequestMapping(value= {"/profile","/administration/users/{user_id}"}, method = RequestMethod.POST)
    public String saveProfile(ModelMap model, @ModelAttribute(value="contract") ContractPO contract,
                              BindingResult result
    ){
        contractValidator.validate(contract, result);
        if(!result.hasErrors()) {
            String password = contract.getUser().getRawPassword();
            if(!password.isEmpty())
                contract.getUser().setPasswordHash(passwordEncoder.encode(password));
            contractService.update(contract);
            setSuccess(model, "Successfully updated!");
        }
        checkBlockStatus(model, contract.getUser());
        return "Profile";
    }

    /**
     * Show
     * @param model
     * @param user_id
     * @return
     */
    @RequestMapping(value="/administration/users/{user_id}", method = RequestMethod.GET)
    public String getUser(ModelMap model,
                          @PathVariable long user_id,
                          @RequestParam(value = "block", required = false) Boolean block
    ) {
        return profilePrepare(model, Type.AdminRegistration, userService.get(user_id).get(), block, user_id);
    }

    /**
     * Show users tables with pagination
     * @param model - current page model
     * @param currentPage - page number
     * @return the name of JSP
     */
    @RequestMapping(value="/administration/users")
    public String getUsers(ModelMap model,
        @RequestParam(value = "currentPage", required = false) Integer currentPage,
        @RequestParam(value = "search", required = false) String searchKey
    ) {
        if(searchKey == null) {
            Utils.pagination(userService, model, currentPage, "users");
        }
        else {
            UserPO user = userService.findByEmail(searchKey);
            if(user == null) {
                ContractPO contract = contractService.findByPhoneNumber(searchKey);
                if(contract != null) {
                    user = userService.get(contract.getUser().getId()).orElse(null);
                }
            }
            if(user != null) {
                model.addAttribute("users", Arrays.asList(user));
            }
            model.addAttribute("searchText", searchKey);
        }
        return "administration/Users";
    }

    /**
     * Base method for showing user profile
     * @param model - current page model
     * @param type - which type of profile is used
     * @return the name of JSP
     */
    private String profilePrepare(ModelMap model, Type type, UserPO currentUser, Boolean block, Long id) {
        model.addAttribute("contract", currentUser.getContract());
        if (id == null) {
            prepare(model, type);
        } else {
            prepare(model, type, id);
        }

        if(authService.getCurrentUser().isAdmin())
            model.addAttribute("availablePlans", planService.getAll());

        if(block != null && currentUser.getContract() != null)
            blockProcess(block, currentUser.getContract());

        checkBlockStatus(model, currentUser);

        return "Profile";
    }

    private String contractPrepare(ModelMap model, ContractPO contract) {

        List<OptionPO> options = Utils.prepareOptions(contract.getOptions(), contract.getPlan().getOptions());

        ContractPO optionsContract = new ContractPO();
        optionsContract.setOptions(options);

        model.addAttribute("contract", contract);
        model.addAttribute("optionsContract", optionsContract);
        return "Contract";
    }

    private void blockProcess(boolean block, ContractPO contract){
        if(block || (!block && canUnblock(contract.getUser()))) {
            UserPO user = contract.getUser();
            user.setBlocked(block);
            user.setDisabledBy(block ? authService.getCurrentUser().getId() : null);
        }
    }

    private void checkBlockStatus(ModelMap model, UserPO currentUser) {
        if(currentUser.isBlocked()) {
            model.addAttribute("blocked", true);
            if(canUnblock(currentUser))
                model.addAttribute("couldBeUnblocked", true);
        }
    }

    private boolean canUnblock(UserPO currentUser) {
        return currentUser.getDisabledBy() == currentUser.getId() || authService.getCurrentUser().isAdmin();
    }
}
