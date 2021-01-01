package com.ecare.dao;

import org.springframework.stereotype.Repository;

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
//@Repository
public class Dao<T> {

    private final Class<T> type;
    private final String tableName;
    private EntityManager entityManager;

    public Dao(Class<T> type, String tableName) {
        this.type = type;
        this.tableName = tableName;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<T> get(long id) {
        return Optional.ofNullable(entityManager.find(type, id));
    }

    public List<T> getAll() {
        Query q = entityManager.createQuery(String.format("SELECT e FROM %s e", tableName));
        return q.getResultList();
    }

    public void save(T value) {
        execute(entityManager -> entityManager.persist(value));
    }

    public void update(T value) {
        execute(entityManager -> entityManager.merge(value));
    }

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