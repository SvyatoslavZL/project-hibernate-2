package com.javarush.kovalinsky.projecthibernate2.dao;

import com.javarush.kovalinsky.projecthibernate2.entity.Inventory;
import org.hibernate.Session;

public class InventoryDAO extends AbstractDAO<Inventory> {
    public InventoryDAO(Session session) {
        super(Inventory.class, session);
    }
}
