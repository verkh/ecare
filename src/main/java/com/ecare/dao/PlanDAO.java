package com.ecare.dao;

import com.ecare.models.PlanPO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class PlanDAO extends Dao<PlanPO> implements IPlanDAO {

    public PlanDAO() {
        super(PlanPO.class);
    }

    @Override
    public PlanPO findByName(String value) { return findBy(value, "name"); }
}
