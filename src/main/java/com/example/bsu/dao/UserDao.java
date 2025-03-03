package com.example.bsu.dao;

import com.example.bsu.model.Todo;
import com.example.bsu.model.User;
import com.example.bsu.utils.HibernateSessionFactoryUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


public class UserDao {
    private static final Logger logger = LogManager.getLogger(UserDao.class);
    public static  User findById(int id) {
        Session session =  HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            User user = (User) session.get(User.class, id);
            tx.commit();
            return user;
        } catch (Exception e) {
            tx.rollback();
        } finally {
            session.close();
        }
        return null;
    }
    public static void save(User user) {
        Session session =  HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.save(user);
            tx.commit();
            logger.info("User found with id: ");
        } catch (Exception e) {
            logger.error("Error in findById", e);
            tx.rollback();
        } finally {
            session.close();
        }
    }
    public static void update(User user) {
        HibernateSessionFactoryUtil.getSessionFactory().openSession().update(user);
    }
    public static  void delete(User user) {
        HibernateSessionFactoryUtil.getSessionFactory().openSession().delete(user);
    }
}
