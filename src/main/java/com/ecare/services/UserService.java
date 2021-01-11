package com.ecare.services;

import com.ecare.dao.IUserDAO;
import com.ecare.models.UserPO;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<IUserDAO, UserPO>  {

    public UserPO findByEmail(String email) {
        return dao.findByEmail(email);
    }
}
