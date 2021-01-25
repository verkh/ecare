package com.ecare.services;

import com.ecare.dao.IUserDAO;
import com.ecare.models.UserPO;
import org.springframework.stereotype.Service;

/**
 *  Describes handler of user
 */
@Service
public class UserService extends BaseService<IUserDAO, UserPO>  {

    /**
     * find user by email
     * @param email email of user
     * @return Found user or null if user is not found
     */
    public UserPO findByEmail(String email) {
        return dao.findByEmail(email);
    }
}
