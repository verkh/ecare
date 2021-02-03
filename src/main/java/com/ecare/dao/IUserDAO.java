package com.ecare.dao;

import com.ecare.base.BaseData;
import com.ecare.models.ContractPO;
import com.ecare.models.UserPO;

import java.util.List;

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

    /**
     * Search contacts that have number alike
     * @param email partial of full email
     * @return list of matched users
     */
    public List<UserPO> findAlikeByEmail(String email);
}
