package com.javarush.kovalinsky.projecthibernate2.dao;

import com.javarush.kovalinsky.projecthibernate2.entity.Rental;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class RentalDAO extends AbstractDAO<Rental> {
    public RentalDAO(Session session) {
        super(Rental.class, session);
    }

    public Rental getAnyUnreturnedRental() {
        Query<Rental> query = session.createQuery("SELECT r FROM Rental r WHERE r.returnDate is NULL", Rental.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }
}
