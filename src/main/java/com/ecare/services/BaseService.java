package com.ecare.services;

import com.ecare.base.BaseData;
import com.ecare.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class BaseService<DAOType extends Dao, POJOType> implements BaseData<POJOType> {
    @Autowired
    private DAOType dao;

    public Optional<POJOType> get(long id) { return dao.get(id); }

    public List<POJOType> getAll() { return dao.getAll(); }

    public void save(POJOType value) {
        dao.save(value);
    }

    public void update(POJOType value) {
        dao.update(value);
    }

    public void delete(POJOType value) { dao.delete(value); }
}
