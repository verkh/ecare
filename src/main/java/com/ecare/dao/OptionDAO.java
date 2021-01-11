package com.ecare.dao;

import com.ecare.models.OptionPO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class OptionDAO extends Dao<OptionPO> implements IOptionDAO {
    public OptionDAO() {
        super(OptionPO.class);
    }
}
