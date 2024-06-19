package com.javarush.kovalinsky.projecthibernate2.dao;

import com.javarush.kovalinsky.projecthibernate2.entity.Staff;
import org.hibernate.Session;

public class StaffDAO extends AbstractDAO<Staff> {
    public StaffDAO(Session session) {
        super(Staff.class, session);
    }
}
