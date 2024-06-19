package com.javarush.kovalinsky.projecthibernate2.dao;

import com.javarush.kovalinsky.projecthibernate2.entity.Language;
import org.hibernate.Session;

public class LanguageDAO extends AbstractDAO<Language> {
    public LanguageDAO(Session session) {
        super(Language.class, session);
    }
}
