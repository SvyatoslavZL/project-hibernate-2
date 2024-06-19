package com.javarush.kovalinsky.projecthibernate2.dao;

import com.javarush.kovalinsky.projecthibernate2.entity.Actor;
import org.hibernate.Session;

public class ActorDAO extends AbstractDAO<Actor> {
    public ActorDAO(Session session) {
        super(Actor.class, session);
    }
}
