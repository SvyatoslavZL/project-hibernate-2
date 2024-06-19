package com.javarush.kovalinsky.projecthibernate2.dao;

import com.javarush.kovalinsky.projecthibernate2.entity.Payment;
import org.hibernate.Session;

public class PaymentDAO extends AbstractDAO<Payment> {
    public PaymentDAO(Session session) {
        super(Payment.class, session);
    }
}
