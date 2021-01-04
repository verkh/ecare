package com.ecare.dao;

import com.ecare.models.UserPO;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("UserDAO")
public class UserDAO extends Dao<UserPO> {

    UserDAO(EntityManagerFactory factory) {
        super(UserPO.class, factory.createEntityManager());
    }

    public UserPO findByEmail(String email) {
        return findBy(email, "email");
    }
}
