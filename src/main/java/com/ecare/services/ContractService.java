package com.ecare.services;

import com.ecare.dao.IContractDAO;
import com.ecare.models.ContractPO;
import org.springframework.stereotype.Service;

@Service
public class ContractService extends BaseService<IContractDAO, ContractPO> {

    public ContractPO findByPhoneNumber(String phoneNumber) {
        return dao.findByPhone(phoneNumber);
    }

}
