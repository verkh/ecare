package com.ecare.services;

import com.ecare.dao.PlanDAO;
import com.ecare.models.PlanPO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class PlanService extends BaseService<PlanDAO, PlanPO> {

}
