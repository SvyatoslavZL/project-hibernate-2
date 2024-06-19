package com.javarush.kovalinsky.projecthibernate2.dao;

import com.javarush.kovalinsky.projecthibernate2.entity.Category;
import org.hibernate.Session;

public class CategoryDAO extends AbstractDAO<Category> {
    public CategoryDAO(Session session) {
        super(Category.class, session);
    }
}
