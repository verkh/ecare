package com.ecare.base;

import java.util.List;
import java.util.Optional;

public interface BaseData<T> {

    /**
     * Seeks object by id
     * @param id id of object
     * @return object or empty
     */
    public Optional<T> get(long id);


    /**
     * Seeks N object in table. Used for pagination
     * @param from start id in table
     * @param number number of records to load
     * @return list of objects
     */
    public List<T> get(int from, int number);

    /**
     * @return all objects from table
     */
    public List<T> getAll();

    /**
     * Saves objects into database
     * @param value object for saving
     * @return updated object
     */
    public T save(T value);

    /**
     * Saves objects into database
     * @param value object for saving
     * @return updated object
     */
    public T update(T value);

    /**
     * Deletes object from database
     * @param value object to delete
     */
    public void delete(T value);

    /**
     * @return number of records in database
     */
    public long count();
}