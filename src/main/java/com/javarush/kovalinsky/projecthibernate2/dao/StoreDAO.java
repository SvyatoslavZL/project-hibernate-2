package com.javarush.kovalinsky.projecthibernate2.dao;

import com.javarush.kovalinsky.projecthibernate2.entity.Store;
import org.hibernate.Session;

public class StoreDAO extends AbstractDAO<Store> {
    public StoreDAO(Session session) {
        super(Store.class, session);
    }
}
