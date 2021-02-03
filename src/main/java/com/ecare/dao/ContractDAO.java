package com.ecare.dao;

import com.ecare.models.ContractPO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    @Override
    public ContractPO findByPhone(String phone) {
        return findBy(phone, "phoneNumber");
    }

    /**
     * Search contacts that have number alike
     * @param phone partial of full number
     * @return list of matched contracts
     */
    @Override
    public List<ContractPO> findAlikeByPhone(String phone) { return findAlikeBy(phone, "phoneNumber"); }
}
