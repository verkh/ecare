package com.ecare.services;

import com.ecare.dao.IContractDAO;
import com.ecare.models.ContractPO;
import org.springframework.stereotype.Service;

/**
 *  Describes handler of contracts
 */
@Service
public class ContractService extends BaseService<IContractDAO, ContractPO> {

    /**
     * find contract by phone number
     * @param phoneNumber phone number string
     * @return Found contract or null if contract is not found
     */
    public ContractPO findByPhoneNumber(String phoneNumber) {
        return dao.findByPhone(phoneNumber);
    }

}
