package com.ecare.controllers;

import com.ecare.dao.Dao;
import com.ecare.models.PlanPO;
import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@Controller
public class PlansController {
    @RequestMapping(value="/Plans")
    public String getPlans(ModelMap model) {
        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("eCareDB");
            EntityManager entityManager = factory.createEntityManager();

            Dao<PlanPO> plansDao = new Dao<PlanPO>(PlanPO.class, "PlanPO");
            plansDao.setEntityManager(entityManager);
            List<PlanPO> plans = plansDao.getAll();
            System.out.println("PLANS: " + plans.size() + " options:" + plans.get(0).getOptions().size());
            model.addAttribute("Plans", plans);
        }
        catch (Exception e)
        {
            model.addAttribute("error", e.toString());
            model.addAttribute("errorStack", e.getStackTrace().toString());
        }
        return "Plans";
    }

    @RequestMapping(value="/Plans/{id}")
    public String getPlan(ModelMap model, @PathVariable long id) {
        return "Plan";
    }
}