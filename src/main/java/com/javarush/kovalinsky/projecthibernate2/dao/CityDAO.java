package com.javarush.kovalinsky.projecthibernate2.dao;

import com.javarush.kovalinsky.projecthibernate2.entity.City;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class CityDAO extends AbstractDAO<City> {
    public CityDAO(Session session) {
        super(City.class, session);
    }

    public City getByName(String name) {
        Query<City> query = session.createQuery("SELECT c FROM City c WHERE c.name = :name", City.class);
        query.setParameter("name", name);
        query.setMaxResults(1);
        return query.getSingleResult();
    }
}
