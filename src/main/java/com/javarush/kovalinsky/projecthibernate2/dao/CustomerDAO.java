package com.javarush.kovalinsky.projecthibernate2.dao;

import com.javarush.kovalinsky.projecthibernate2.entity.Customer;
import org.hibernate.Session;

public class CustomerDAO extends AbstractDAO<Customer> {
    public CustomerDAO(Session session) {
        super(Customer.class, session);
    }
}
