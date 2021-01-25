package com.ecare.dao;

import com.ecare.models.PlanPO;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO handles logic related to plans table
 * @see Dao
 */
@Transactional
public class PlanDAO extends Dao<PlanPO> implements IPlanDAO {

    public PlanDAO() {
        super(PlanPO.class);
    }

    /**
     * find plan by name
     * @param value name of plan
     * @return Found plan or null if plan is not found
     */
    @Override
    public PlanPO findByName(String value) { return findBy(value, "name"); }
}
