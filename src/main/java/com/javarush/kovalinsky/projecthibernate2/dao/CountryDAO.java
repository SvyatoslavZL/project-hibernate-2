package com.javarush.kovalinsky.projecthibernate2.dao;

import com.javarush.kovalinsky.projecthibernate2.entity.Country;
import org.hibernate.Session;

public class CountryDAO extends AbstractDAO<Country> {
    public CountryDAO(Session session) {
        super(Country.class, session);
    }
}
