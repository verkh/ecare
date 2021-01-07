package com.ecare.dao;

import com.ecare.base.BaseData;
import com.ecare.models.UserPO;

public interface IUserDAO extends BaseData<UserPO> {

    public UserPO findByEmail(String email);
}
