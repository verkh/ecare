package com.ecare.dao;

import com.ecare.models.OptionPO;

public class OptionDAO extends Dao<OptionPO> implements IOptionDAO {
    public OptionDAO() {
        super(OptionPO.class);
    }
}
