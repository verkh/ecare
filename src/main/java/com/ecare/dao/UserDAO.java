package com.ecare.dao;

import com.ecare.models.UserPO;
import org.springframework.stereotype.Repository;

public class UserDAO extends Dao<UserPO> implements IUserDAO {

    public UserDAO() {
        super(UserPO.class);
    }

    @Override
    public UserPO findByEmail(String email) {
        return findBy(email, "email");
    }
}
