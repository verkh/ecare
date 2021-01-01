package com.ecare.configuration;

import com.ecare.models.PlanPO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
public class EcareConfiguration extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

//    @Bean
//    public PlanPO demoBean() {
//        try {
//            EntityManagerFactory factory = Persistence.createEntityManagerFactory("eCareDB");
//            EntityManager entityManager = factory.createEntityManager();
//
//            entityManager.getTransaction().begin();
//
//            PlanPO plan = new PlanPO();
//            plan.setName("Bean");
//            plan.setPrice(0.666);
//
//            entityManager.persist(plan);
//
//            entityManager.getTransaction().commit();
//
//            entityManager.close();
//            factory.close();
//
//            return plan;
//        } catch (Exception e) {
//            System.out.println(e.toString());
//            e.printStackTrace();
//        }
//        return null;
//    }
}
