package com.ecare.dao;

import com.ecare.models.ContractPO;
import org.springframework.stereotype.Repository;

@Repository("ContractDAO")
public class ContractDAO extends Dao<ContractPO> {

    ContractDAO() {
        super(ContractPO.class);
    }

    public ContractPO findByPhone(String phone) {
        return findBy(phone, "phone_number");
    }
}
