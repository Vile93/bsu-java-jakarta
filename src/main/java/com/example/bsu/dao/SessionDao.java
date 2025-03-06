package com.example.bsu.dao;

import com.example.bsu.model.Session;
import com.example.bsu.utils.HibernateSessionFactoryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import java.util.UUID;

public class SessionDao {
    public static Session findById(UUID id) {
        org.hibernate.Session session =  HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Session s = null;
        s = (Session) session.get(Session.class, id.toString());
        session.close();
        return s;
    }
    public static void save(Session s) {
        org.hibernate.Session session =  HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(s);
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null) tx.rollback();
        } finally {
            session.close();
        }
    }
    public static void delete(Session s) {
        org.hibernate.Session session =  HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(s);
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null) tx.rollback();
        } finally {
            session.close();
        }
    }
    public  static void deleteAll(int userId) {
        org.hibernate.Session session =  HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.createQuery("DELETE FROM Session WHERE user_id = :userId").setParameter("userId", userId).executeUpdate();
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null) tx.rollback();
        } finally {
            session.close();
        }
    }
}
