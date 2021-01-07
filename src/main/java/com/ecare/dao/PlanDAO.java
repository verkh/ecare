package com.ecare.dao;

import com.ecare.models.PlanPO;
import org.springframework.stereotype.Repository;

@Repository("PlanDAO")
public class PlanDAO extends Dao<PlanPO> {

    PlanDAO() {
        super(PlanPO.class);
    }
}
