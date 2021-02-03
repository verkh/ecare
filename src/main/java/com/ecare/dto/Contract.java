package com.ecare.dto;

import com.ecare.controllers.Utils;
import com.ecare.models.ContractOptionPO;
import com.ecare.models.ContractPO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class Contract {
    private Long id;
    private String phoneNumber;
    private Plan plan;
    private User user;
    private List<Option> options = new ArrayList<>();

    public Contract(ContractPO contract) {
        this.id = contract.getId();
        this.phoneNumber = contract.getPhoneNumber();
        this.plan = new Plan(contract.getPlan());
        this.user = new User(contract.getUser());
        this.options = contract.getOptions().stream().map(value -> Option.of(value)).collect(Collectors.toList());
        Utils.sortOptions(options);
    }

    public ContractPO toEntity() {
        ContractPO contract = new ContractPO();
        contract.setId(this.id);
        contract.setPhoneNumber(phoneNumber);
        contract.setPlan(plan.toEntity());
        contract.setUser(user.toEntity());
        for(final Option option : options) {
            ContractOptionPO opt = option.toEntity(new ContractOptionPO());
            opt.setContractId(this.id);
            contract.getOptions().add(opt);
        }
        return contract;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
        options.forEach(v -> v.setAttachedId(null));
    }

    public static Contract of(ContractPO contractPO) {
        return new Contract(contractPO);
    }
}
