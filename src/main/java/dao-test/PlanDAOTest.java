package dao;

import models.OptionPO;
import models.PlanPO;
import models.SubscriberPO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Date;

public class PlanDAOTest {
    public static void main(String[] args) {
	try {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("eCareDB");
        EntityManager entityManager = factory.createEntityManager();
             
        entityManager.getTransaction().begin();
             
        PlanPO plan = new PlanPO();
        plan.setName("All inc");
        plan.setPrice(0.23);

        OptionPO option = new OptionPO();
        option.setName("GoodOK");
        option.setPrice(0.1);
        option.setOn(true);

        plan.options.add(option);

        SubscriberPO sub = new SubscriberPO();
        sub.setAddress("Not the building, not the street");
        sub.setDate(Date.valueOf("1984-01-01"));
        sub.setEmail("wwwleningrad.spb.ru");
        sub.setPassport("No passport 1021");
        sub.setPasswordHash("asdasdasd");
        sub.setName("Oleg");
        sub.setLastName("NeOleg");

        entityManager.persist(sub);
        entityManager.persist(plan);
        entityManager.persist(option);

        entityManager.getTransaction().commit();
             
        entityManager.close();
        factory.close();
	} 
	catch (Exception e)
	{
        System.out.println(e.toString());
        e.printStackTrace();
	}

    }

}
