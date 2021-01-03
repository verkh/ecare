package com.ecare.dao;

import com.ecare.base.BaseData;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * DAO is an interface that represents common manipulation methods for data objects;
 * @param <T> Template parameter describes handled data
 */
public class Dao<T> implements BaseData<T> {

    private final Class<T> type;
    private final String pojoClassName;

    private EntityManager entityManager;

    public Dao(Class<T> type, String pojoClassName, EntityManager entityManager) {
        this.type = type;
        this.pojoClassName = pojoClassName;
        this.entityManager = entityManager;
    }

    @Override
    public Optional<T> get(long id) {
        return Optional.ofNullable(entityManager.find(type, id));
    }

    @Override
    public List<T> getAll() {
        Query q = entityManager.createQuery(String.format("SELECT e FROM %s e", pojoClassName));
        return q.getResultList();
    }

    @Override
    public void save(T value) {
        execute(entityManager -> entityManager.persist(value));
    }

    @Override
    public void update(T value) {
        execute(entityManager -> entityManager.merge(value));
    }

    @Override
    public void delete(T value) {
        execute(entityManager -> entityManager.remove(value));
    }

    private void execute(Consumer<EntityManager> action) {

        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            action.accept(entityManager);
            tx.commit();
        }
        catch (RuntimeException e){
            tx.rollback();
        }
    }
}