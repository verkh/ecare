package com.ecare.services;

import com.ecare.base.BaseData;
import com.ecare.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class BaseService<DAOType extends Dao, POJOType> implements BaseData<POJOType> {
    @Autowired
    private DAOType dao;

    @Override
    public Optional<POJOType> get(long id) { return dao.get(id); }

    @Override
    public List<POJOType> get(int from, int number) { return dao.get(from, number); }

    @Override
    public List<POJOType> getAll() { return dao.getAll(); }

    @Override
    public void save(POJOType value) {
        dao.save(value);
    }

    @Override
    public void update(POJOType value) {
        dao.update(value);
    }

    @Override
    public void delete(POJOType value) { dao.delete(value); }

    @Override
    public long count() { return dao.count(); }
}
