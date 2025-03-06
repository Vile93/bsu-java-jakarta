package com.example.bsu.dao;

import com.example.bsu.model.User;
import com.example.bsu.utils.HibernateSessionFactoryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class UserDao {
    public static User findByUsername(String username) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        User user = (User) session.createQuery("FROM User WHERE name = :username", User.class).setParameter("username", username).uniqueResult();
        session.close();
        return user;
    }
    public static User findById(int id) {
        Session session =  HibernateSessionFactoryUtil.getSessionFactory().openSession();
        User user = null;
        user = (User) session.get(User.class, id);
        session.close();
        return user;
    }
    public static void save(User user) {
        Session session =  HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(user);
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null) tx.rollback();
        } finally {
            session.close();
        }
    }
    public static void update(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(user);
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null) tx.rollback();
        } finally {
            session.close();
        }
    }
    public static  void delete(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(user);
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null) tx.rollback();
        } finally {
            session.close();
        }
    }
}
