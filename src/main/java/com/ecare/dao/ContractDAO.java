package com.ecare.dao;

import com.ecare.models.ContractPO;

public class ContractDAO extends Dao<ContractPO> implements IContractDAO {

    public ContractDAO() {
        super(ContractPO.class);
    }

    public ContractPO findByPhone(String phone) {
        return findBy(phone, "phone_number");
    }
}
