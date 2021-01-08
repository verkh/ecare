package com.ecare.services;

import com.ecare.dao.IUserDAO;
import com.ecare.models.UserPO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class UserService extends BaseService<IUserDAO, UserPO>  {

}
