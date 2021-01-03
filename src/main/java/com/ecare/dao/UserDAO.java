package com.ecare.dao;

import com.ecare.models.SubscriberPO;
import javax.persistence.EntityManagerFactory;
import org.springframework.stereotype.Repository;

@Repository("UserDAO")
public class UserDAO extends Dao<SubscriberPO> {

    UserDAO(EntityManagerFactory factory) {
        super(SubscriberPO.class,"SubscriberPO", factory.createEntityManager());
    }
}
