package com.ecare.dao;

import com.ecare.models.PlanPO;

public class PlanDAO extends Dao<PlanPO> implements IPlanDAO {

    public PlanDAO() {
        super(PlanPO.class);
    }
}
