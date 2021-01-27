package com.ecare.controllers;

import com.ecare.dto.Option;
import com.ecare.models.ContractPO;
import com.ecare.models.OptionPO;
import com.ecare.models.UserPO;
import com.ecare.services.PlanService;
import com.ecare.validators.ContractValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * Handles buisness logic of user management
 */
@Controller
@SessionAttributes(names= {"contract", "optionsContract", "availablePlans"}, types = ContractPO.class)
public class UserController extends BaseUserController {

    private static Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    ContractValidator contractValidator;

    @Autowired
    PlanService planService;

    /**
     * Show user's profile
     * @param model model for JSP page
     * @return the name of JSP file
     */
    @RequestMapping(value="/profile", method = RequestMethod.GET)
    public String getProfile(ModelMap model,
                             @RequestParam(value = "block", required = false) Boolean block
    ){
        logger.trace("Configuring profile page for user with id=" + authService.getCurrentUser().getId());
        return profilePrepare(model, Type.Profile, authService.getCurrentUser(), block, null);
    }

    /**
     * Configures contract page of currenct authenticated user
     * @param model model for JSP page
     * @return the name of JSP file
     */
    @RequestMapping(value="/contract", method = RequestMethod.GET)
    public String getContract(ModelMap model)
    {
        logger.trace("Configuring contract page of user with id=" + authService.getCurrentUser().getId());
        model.addAttribute("current_action", "contract");
        return contractPrepare(model, authService.getCurrentUser().getContract());
    }

    /**
     * Configures contract page that is seen by administrator
     * @param model model for JSP page
     * @param contract_id id of contract
     * @return the name of JSP file
     */
    @RequestMapping(value="/administration/contracts/{contract_id}", method = RequestMethod.GET)
    public String getContractByID(ModelMap model, @PathVariable long contract_id)
    {
        logger.trace("Configuring contracts page of user with id=" + contract_id);
        model.addAttribute("current_action", contract_id);
        return contractPrepare(model, contractService.get(contract_id).get());
    }

    /**
     * Attempts to save contract changes
     * @param model model for JSP page
     * @param current current contract settings
     * @param contract new contract settings
     * @param result validation errors container
     * @return the name of JSP file
     */
    @RequestMapping(value= {"/contract", "/administration/contracts/{contract_id}"}, method = RequestMethod.POST)
    public String saveContract(ModelMap model,
                               @ModelAttribute(value="contract") ContractPO current,
                               @ModelAttribute(value="optionsContract") ContractPO contract,
                               BindingResult result
    ) {
        logger.trace(String.format("Saving contract of user with id=%d and contract id=%d",
                current.getUser().getId(), current.getId()));

        current.getOptions().clear();
        for(final OptionPO opt : contract.getOptions()) {
            if(opt.isEnabled())
                current.getOptions().add(opt);
        }
        contractService.update(current);
        logger.trace(String.format("Saved contract of user with id=%d and contract id=%d",
                current.getUser().getId(), current.getId()));
        setSuccess(model, "Successfully updated!");
        return "Contract";
    }

    /**
     * Attempts to save profile of user
     * @param model model for JSP page
     * @param contract current contract of user
     * @param result validation errors container
     * @return the name of JSP file
     */
    @RequestMapping(value= {"/profile","/administration/users/{user_id}"}, method = RequestMethod.POST)
    public String saveProfile(ModelMap model, @ModelAttribute(value="contract") ContractPO contract,
                              BindingResult result
    ){
        logger.trace(String.format("Saving profile settings of user with id=%d", contract.getUser().getId()));
        contractValidator.validate(contract, result);
        if(!result.hasErrors()) {
            String password = contract.getUser().getRawPassword();
            if(!password.isEmpty())
                contract.getUser().setPasswordHash(passwordEncoder.encode(password));
            contractService.update(contract);
            setSuccess(model, "Successfully updated!");
        }
        logger.trace(String.format("Saving profile settings of user with id=%d", contract.getUser().getId()));
        checkBlockStatus(model, contract.getUser());
        return "Profile";
    }

    /**
     * Configures profile page of selected user (administration)
     * @param model model for JSP page
     * @param user_id id of user
     * @return the name of JSP file
     */
    @RequestMapping(value="/administration/users/{user_id}", method = RequestMethod.GET)
    public String getUser(ModelMap model,
                          @PathVariable long user_id,
                          @RequestParam(value = "block", required = false) Boolean block
    ) {
        return profilePrepare(model, Type.AdminProfileView, userService.get(user_id).get(), block, user_id);
    }

    /**
     * Show users tables with pagination
     * @param model - current page model
     * @param currentPage - page number
     * @return the name of JSP file
     */
    @RequestMapping(value="/administration/users")
    public String getUsers(ModelMap model,
                           @RequestParam(value = "currentPage", required = false) Integer currentPage,
                           @RequestParam(value = "search", required = false) String searchKey,
                           @RequestParam(value = "block", required = false) Boolean block,
                           @RequestParam(value = "user_id", required = false) Long userId
    ) {
        //processing of block algo
        if(block != null && userId != null) {
            UserPO user = userService.get(userId).orElse(null);
            blockProcess(block, user);
            userService.update(user);
        }

        //process of search
        if(searchKey == null) {
            Utils.pagination(userService, model, currentPage, "users");
        }
        else { // simple pagination processing
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
     * @param model
     * @param currentPage
     * @param searchKey
     * @param block
     * @param userId
     * @return
     */
    @RequestMapping(params = "unblock_all", value = "/administration/users", method = RequestMethod.POST)
    public String unblockAllUsers(ModelMap model,
                                  @RequestParam(value = "currentPage", required = false) Integer currentPage,
                                  @RequestParam(value = "search", required = false) String searchKey,
                                  @RequestParam(value = "block", required = false) Boolean block,
                                  @RequestParam(value = "user_id", required = false) Long userId)
    {
        List<UserPO> allUsers = userService.getAll();
        for(UserPO user : allUsers) {
            blockProcess(false, user);
            userService.save(user);
        }
        return getUsers(model, currentPage, searchKey, block, userId);
    }

    /**
     * @param model
     * @param currentPage
     * @param searchKey
     * @param block
     * @param userId
     * @return
     */
    @RequestMapping(params = "block_all", value = "/administration/users", method = RequestMethod.POST)
    public String blockAllUsers(ModelMap model,
                             @RequestParam(value = "currentPage", required = false) Integer currentPage,
                             @RequestParam(value = "search", required = false) String searchKey,
                             @RequestParam(value = "block", required = false) Boolean block,
                             @RequestParam(value = "user_id", required = false) Long userId)
    {
        List<UserPO> allUsers = userService.getAll();
        for(UserPO user : allUsers) {
            if(!user.isAdmin() && !user.isDictator()) {
                blockProcess(true, user);
                userService.save(user);
            }
        }
        return getUsers(model, currentPage, searchKey, block, userId);
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
            blockProcess(block, currentUser);

        checkBlockStatus(model, currentUser);

        return "Profile";
    }

    /**
     * Common method for configuring page of contract
     * @param model model for JSP page
     * @param contract contract of selected user
     * @return the name of JSP file
     */
    private String contractPrepare(ModelMap model, ContractPO contract) {

        List<OptionPO> options = Utils.prepareOptions(contract.getOptions(), contract.getPlan().getOptions());

        ContractPO optionsContract = new ContractPO();
        optionsContract.setOptions(options);

        model.addAttribute("contract", contract);
        model.addAttribute("optionsContract", optionsContract);
        return "Contract";
    }

    /**
     * Handles block business logic.
     * User can block and unblock themself only if user was not blocked in the first place by administrator
     * @param user current managed user
     */
    private void blockProcess(boolean block, UserPO user){
        if(block || (!block && canUnblock(user))) {
            user.setBlocked(block);
            user.setDisabledBy(block ? authService.getCurrentUser().getId() : null);
        }
    }

    /**
     * Checks whether user is blocked or not
     * @param model model for JSP page
     * @param currentUser current selected user
     */
    private void checkBlockStatus(ModelMap model, UserPO currentUser) {
        if(currentUser.isBlocked()) {
            model.addAttribute("blocked", true);
            if(canUnblock(currentUser))
                model.addAttribute("couldBeUnblocked", true);
        }
    }

    /**
     * Checks whether user can be unblocked (by themself or other user)
     * @param currentUser current selected user
     * @return true if user can be unblock
     */
    private boolean canUnblock(UserPO currentUser) {
        UserPO curLoggedUser = authService.getCurrentUser();
        return currentUser.getDisabledBy() == currentUser.getId() ||
                curLoggedUser.isAdmin() ||
                curLoggedUser.isDictator();
    }
}
