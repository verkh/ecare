package com.ecare.dao;

import com.ecare.models.ContractPO;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO handles logic related to contracts table
 * @see Dao
 */
@Transactional
public class ContractDAO extends Dao<ContractPO> implements IContractDAO {

    public ContractDAO() {
        super(ContractPO.class);
    }

    /**
     * find contract by phone number
     * @param phone phone number string
     * @return Found contract or null if contract is not found
     */
    public ContractPO findByPhone(String phone) {
        return findBy(phone, "phoneNumber");
    }
}
