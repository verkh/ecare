package com.ecare.dao;

import com.ecare.base.BaseData;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * DAO is an interface that represents common manipulation methods for data objects;
 * @param <T> Template parameter describes handled data
 */
@Transactional
public class Dao<T> implements BaseData<T> {

    protected final Class<T> type;

    @Autowired
    protected SessionFactory sessionFactory;

    public Dao(Class<T> type) {
        this.type = type;
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Optional<T> get(long id) {
        return Optional.ofNullable((T) getCurrentSession().get(type, id));
    }

    public List<T> get(int from, int number) {
        Query q = sessionFactory.getCurrentSession().createQuery(String.format("SELECT e FROM %s e", type.getSimpleName()));
        q.setFirstResult(from);
        q.setMaxResults(number);
        return q.list();
    }

    @Override
    public List<T> getAll() {
        Query q = getCurrentSession().createQuery(String.format("SELECT e FROM %s e", type.getSimpleName()));
        return q.list();
    }

    @Override
    public T save(T value) {
        getCurrentSession().saveOrUpdate(value);
        return value;
    }

    @Override
    public T update(T value) {
        getCurrentSession().merge(value);
        return value;
    }

    @Override
    public void delete(T value) {
        getCurrentSession().delete(value);
    }

    @Override
    public long count() {
        Query q = getCurrentSession().createQuery(String.format("SELECT count(e) FROM %s e", type.getSimpleName()));
        Object res = q.uniqueResult();
        return res == null ? null : (Long)res;
    }

    protected T findBy(String value, String column) {
        String sql = String.format("SELECT e FROM %s e WHERE e.%s = '%s'", type.getSimpleName(), column, value);
        Query q = getCurrentSession().createQuery(sql);
        return (T) q.uniqueResult();
    }
}