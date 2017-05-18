package com.amsystem.bifaces.util;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * Title: AbstractDao.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 08/09/2016.
 */
public abstract class AbstractDao<PK extends Serializable, T> {

    private final Class<T> persistentClass;

    private boolean flag;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private DataSource dataSource;

    @SuppressWarnings("unchecked")
    public AbstractDao(){
        this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    protected DataSource getDataSource() {
        return dataSource;
    }

    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    public T getByKey(PK key) {
        return (T) getSession().get(persistentClass, key);
    }

    public boolean persist(T entity) {
        flag = true;
        try {
            getSession().persist(entity);
        } catch (Exception ex) {
            flag = false;
            ex.printStackTrace();
        }
        return flag;
    }

    public void update(T entity) {
        getSession().update(entity);
    }

    public boolean delete(T entity) {
        flag = true;
        try {
            getSession().delete(entity);
        } catch (Exception ex) {
            flag = false;
            ex.printStackTrace();
        }
        return flag;
    }

    protected Criteria createEntityCriteria(){
        return getSession().createCriteria(persistentClass);
    }


}
