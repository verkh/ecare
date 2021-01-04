package com.ecare.dao;

import com.ecare.models.ContractPO;
import com.ecare.models.UserPO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

@Repository("ContractDAO")
public class ContractDAO extends Dao<ContractPO> {

    ContractDAO(EntityManagerFactory factory) {
        super(ContractPO.class);
    }

    public ContractPO findByPhone(String phone) {
        return findBy(phone, "phone_number");
    }
}
