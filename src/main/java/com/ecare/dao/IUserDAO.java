package com.ecare.dao;

import com.ecare.base.BaseData;
import com.ecare.models.UserPO;

/**
 * Interface of DAO handles logic related to users table
 * @see BaseData
 */
public interface IUserDAO extends BaseData<UserPO> {

    /**
     * find user by email
     * @param email email of user
     * @return Found user or null if user is not found
     */
    public UserPO findByEmail(String email);
}
