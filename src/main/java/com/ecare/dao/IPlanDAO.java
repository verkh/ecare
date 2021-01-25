package com.ecare.dao;

import com.ecare.base.BaseData;
import com.ecare.models.PlanPO;

/**
 * Interface of DAO handles logic related to plans table
 * @see BaseData
 */
public interface IPlanDAO extends BaseData<PlanPO> {

    /**
     * find plan by name
     * @param value name of plan
     * @return Found plan or null if plan is not found
     */
    public PlanPO findByName(String value);
}
