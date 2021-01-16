package com.ecare.dao;

import com.ecare.base.BaseData;
import com.ecare.models.PlanPO;

public interface IPlanDAO extends BaseData<PlanPO> {

    public PlanPO findByName(String value);
}
