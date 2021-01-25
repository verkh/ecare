package com.ecare.dao;

import com.ecare.models.UserPO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO handles logic related to users table
 * @see Dao
 */
@Transactional
public class UserDAO extends Dao<UserPO> implements IUserDAO {

    public UserDAO() {
        super(UserPO.class);
    }

    /**
     * find user by email
     * @param email email of user
     * @return Found user or null if user is not found
     */
    @Override
    public UserPO findByEmail(String email) {
        return findBy(email, "email");
    }
}
