package com.ecare.dao;

import com.ecare.base.BaseData;
import com.ecare.models.ContractPO;
import com.ecare.models.UserPO;

/**
 * Interface of DAO handles logic related to contracts table
 * @see BaseData
 */
public interface IContractDAO extends BaseData<ContractPO> {

    /**
     * find contract by phone number
     * @param phone phone number string
     * @return Found contract or null if contract is not found
     */
    public ContractPO findByPhone(String phone);
}
