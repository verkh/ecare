package com.ecare.dao;

import com.ecare.models.UserPO;
import org.springframework.stereotype.Repository;

@Repository("UserDAO")
public class UserDAO extends Dao<UserPO> {

    UserDAO() {
        super(UserPO.class);
    }

    public UserPO findByEmail(String email) {
        return findBy(email, "email");
    }
}
