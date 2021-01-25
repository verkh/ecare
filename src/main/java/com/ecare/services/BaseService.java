package com.ecare.services;

import com.ecare.base.BaseData;
import com.ecare.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

/**
 * Describes base service which is used as base for other services
 * @param <DAOType> type of need DAO which handles POJOType
 * @param <POJOType> type of handled data
 */
public class BaseService<DAOType extends BaseData, POJOType> implements BaseData<POJOType> {
    @Autowired
    protected DAOType dao;

    /**
     * Seeks object by id
     * @param id id of object
     * @return object or empty
     */
    @Override
    public Optional<POJOType> get(long id) { return dao.get(id); }

    /**
     * Seeks N object in table. Used for pagination
     * @param from start id in table
     * @param number number of records to load
     * @return list of objects
     */
    @Override
    public List<POJOType> get(int from, int number) { return dao.get(from, number); }

    /**
     * @return all objects from table
     */
    @Override
    public List<POJOType> getAll() { return dao.getAll(); }

    /**
     * Saves objects into database
     * @param value object for saving
     * @return updated object
     */
    @Override
    public POJOType save(POJOType value) {
        return (POJOType) dao.save(value);
    }

    /**
     * Saves objects into database
     * @param value object for saving
     * @return updated object
     */
    @Override
    public POJOType update(POJOType value) {
        return (POJOType) dao.update(value);
    }

    /**
     * Deletes object from database
     * @param value object to delete
     */
    @Override
    public void delete(POJOType value) { dao.delete(value); }

    /**
     * @return number of records in database
     */
    @Override
    public long count() { return dao.count(); }
}
