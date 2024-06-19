package com.javarush.kovalinsky.projecthibernate2.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

@SuppressWarnings("unused")
public abstract class AbstractDAO<T> {

    private final Class<T> aClass;
    protected final Session session;

    public AbstractDAO(Class<T> aClass, Session session) {
        this.aClass = aClass;
        this.session = session;
    }

    public T getById(Short id) {
        return (T) session.get(aClass, id);
    }

    public List<T> getItems(int offset, int count) {
        Query<T> query = session.createQuery("FROM " + aClass.getName(), aClass);
        query.setFirstResult(offset);
        query.setMaxResults(count);
        return query.list();
    }

    public List<T> findAll() {
        return session.createQuery("FROM " + aClass.getName(), aClass).list();
    }

    public T save(T entity) {
        Transaction tx = session.beginTransaction();
        try {
            session.persist(entity);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new RuntimeException(e);
        }
        return entity;
    }

    public T update(T entity) {
        Transaction tx = session.beginTransaction();
        try {
            session.merge(entity);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new RuntimeException(e);
        }
        return entity;
    }

    public void delete(T entity) {
        Transaction tx = session.beginTransaction();
        try {
            session.remove(entity);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new RuntimeException(e);
        }
    }

    public void deleteById(Integer entityId) {
        Transaction tx = session.beginTransaction();
        try {
            T entity = getById(entityId.shortValue());
            session.remove(entity);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new RuntimeException(e);
        }
    }
}
