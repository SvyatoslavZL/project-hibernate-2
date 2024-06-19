package com.javarush.kovalinsky.projecthibernate2.dao;

import com.javarush.kovalinsky.projecthibernate2.entity.FilmText;
import org.hibernate.Session;

public class FilmTextDAO extends AbstractDAO<FilmText> {
    public FilmTextDAO(Session session) {
        super(FilmText.class, session);
    }
}
