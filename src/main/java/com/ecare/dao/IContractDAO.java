package com.ecare.dao;

import com.ecare.base.BaseData;
import com.ecare.models.ContractPO;
import com.ecare.models.UserPO;

public interface IContractDAO extends BaseData<ContractPO> {

    public ContractPO findByPhone(String phone);
}
