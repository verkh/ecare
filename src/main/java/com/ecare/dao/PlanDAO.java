package com.ecare.dao;

import com.ecare.models.PlanPO;
import javax.persistence.EntityManagerFactory;
import org.springframework.stereotype.Repository;

@Repository("PlanDAO")
public class PlanDAO extends Dao<PlanPO> {

    PlanDAO(EntityManagerFactory factory) {
        super(PlanPO.class);
    }
}
