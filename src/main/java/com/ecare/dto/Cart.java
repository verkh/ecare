package com.ecare.dto;

import com.ecare.controllers.Utils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class Cart {
    private Contract originalContract;
    private Contract newContract;
    private List<Option> changedOptions;

    public Cart(Contract contract) {
        init(contract);
    }

    public boolean isInited() {
        return originalContract != null && newContract != null;
    }

    public void init(Contract contract) {
        originalContract = contract.toBuilder().build();
        discard();
    }

    public void commitContract(Contract contract) {
        init(contract);
    }

    public void discard() {
        newContract = originalContract.toBuilder().build();
        List<Option> options = Utils.prepareOptions(originalContract.getOptions(), originalContract.getPlan().getOptions());
        newContract.setOptions(options);
        changedOptions = new ArrayList<>();
    }

    public boolean isProfileCommited() {
        return originalContract.getUser().equals(newContract.getUser());
    }

    public boolean isContractCommited() {
        if(!originalContract.getPlan().getId().equals(newContract.getPlan().getId()))
            return false;

        return findChangedOptions().isEmpty();
    }

    /**
     * @return List with differences between old and new set of options
     */
    public List<Option> findChangedOptions() {
        List<Option> cleanedNew = newContract.getOptions().stream()
                .filter(opt -> opt.isEnabled()).collect(Collectors.toList());

        changedOptions = originalContract.getOptions().stream()
                .filter(opt -> !containsAndTheSame(cleanedNew, opt))
                .collect(Collectors.toList());

        changedOptions.addAll(cleanedNew.stream()
                .filter(opt -> !containsAndTheSame(originalContract.getOptions(), opt) && findByID(changedOptions, opt.getId()) == null)
                .collect(Collectors.toList()));

        return changedOptions;
    }

    public String getChangedOptionsIds() throws JSONException {
        JSONObject mainNode = new JSONObject();
        List<Long> ids = changedOptions.stream().map(opt -> opt.getId()).collect(Collectors.toList());
        JSONArray jsonArray = new JSONArray(ids);
        mainNode.put("changedOptionIds", jsonArray);
        return mainNode.toString();
    }

    public boolean isChanged(Long id) {
        return changedOptions.stream().filter(opt -> opt.getId().equals(id)).findAny().orElse(null) != null;
    }

    /**
     * Verifies if options is present in target list and they have the same value of enabled flag
     * @param options target list with options
     * @param opt search option
     * @return true if option is present and has the same value of enabled flag
     */
    private boolean containsAndTheSame(List<Option> options, Option opt) {
        Option found = findByID(options, opt.getId());
        if(found == null) return false;
        return found.isEnabled() == opt.isEnabled();
    }

    private Option findByID(List<Option> options, Long id) {
        return options.stream().filter(opt2 -> id.equals(opt2.getId())).findAny().orElse(null);
    }
}
