package com.javarush.kovalinsky.projecthibernate2.dao;

import com.javarush.kovalinsky.projecthibernate2.entity.Address;
import org.hibernate.Session;

public class AddressDAO extends AbstractDAO<Address> {
    public AddressDAO(Session session) {
        super(Address.class, session);
    }
}
