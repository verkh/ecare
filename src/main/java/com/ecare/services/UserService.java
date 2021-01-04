package com.ecare.services;

import com.ecare.dao.UserDAO;
import com.ecare.models.UserPO;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService extends BaseService<UserDAO, UserPO>  {

}
