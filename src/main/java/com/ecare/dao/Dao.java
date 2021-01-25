package com.ecare.dao;

import com.ecare.base.BaseData;
import com.ecare.models.base.AbstractPO;
import com.ecare.services.AuthServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
public class Dao<T extends AbstractPO> implements BaseData<T> {

    private static Logger logger = LogManager.getLogger(Dao.class);

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
        logger.debug(String.format("Looking for '%s' with id=%d", type.getSimpleName(), id));
        return Optional.ofNullable((T) getCurrentSession().get(type, id));
    }

    public List<T> get(int from, int number) {
        logger.debug(String.format("Looking for '%s' in range %d->%d", type.getSimpleName(), from, number));
        Query q = sessionFactory.getCurrentSession().createQuery(String.format("SELECT e FROM %s e", type.getSimpleName()));
        q.setFirstResult(from);
        q.setMaxResults(number);
        return q.list();
    }

    @Override
    public List<T> getAll() {
        logger.debug(String.format("Looking for all '%s'"));
        Query q = getCurrentSession().createQuery(String.format("SELECT e FROM %s e", type.getSimpleName()));
        return q.list();
    }

    @Override
    public T save(T value) {
        getCurrentSession().saveOrUpdate(value);
        logger.debug(String.format("Saved '%s' with id=%d", type.getSimpleName(), value.getId()));
        return value;
    }

    @Override
    public T update(T value) {
        getCurrentSession().merge(value);
        logger.debug(String.format("Updated '%s' with id=%d", type.getSimpleName(), value.getId()));
        return value;
    }

    @Override
    public void delete(T value) {
        logger.debug(String.format("Deleting '%s' with id=%d", type.getSimpleName(), value.getId()));
        getCurrentSession().delete(value);
    }

    @Override
    public long count() {
        Query q = getCurrentSession().createQuery(String.format("SELECT count(e) FROM %s e", type.getSimpleName()));
        Object res = q.uniqueResult();
        long count = res == null ? 0 : (Long)res;
        logger.debug(String.format("Total '%s' found: %d", type.getSimpleName(), count));
        return count;
    }

    protected T findBy(String value, String column) {
        logger.debug(String.format("Looking for '%s' by column=%s with value=%s", type.getSimpleName(), column, value));
        String sql = String.format("SELECT e FROM %s e WHERE e.%s = '%s'", type.getSimpleName(), column, value);
        Query q = getCurrentSession().createQuery(sql);
        return (T) q.uniqueResult();
    }
}