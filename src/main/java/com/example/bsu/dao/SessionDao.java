package com.example.bsu.dao;

import com.example.bsu.model.Session;
import com.example.bsu.utils.HibernateSessionFactoryUtil;

public class SessionDao {
    public Session findSession(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().find(Session.class, id);
    }
    public void save(Session session) {
        HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession().save(session);
    }
    public void delete(Session session) {
        HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession().delete(session);
    }
}
