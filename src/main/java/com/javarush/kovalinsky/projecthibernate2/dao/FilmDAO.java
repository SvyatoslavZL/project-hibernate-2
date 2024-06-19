package com.javarush.kovalinsky.projecthibernate2.dao;

import com.javarush.kovalinsky.projecthibernate2.entity.Film;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class FilmDAO extends AbstractDAO<Film> {
    public FilmDAO(Session session) {
        super(Film.class, session);
    }

    public Film getAnyAvailableFilmForRent() {
        Query<Film> query = session.createQuery(
                "SELECT f FROM Film f WHERE f.id NOT IN (SELECT DISTINCT film.id FROM Inventory)",
                Film.class
        );
        query.setMaxResults(1);
        return query.getSingleResult();
    }
}
