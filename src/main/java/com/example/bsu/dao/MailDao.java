package com.example.bsu.dao;

import com.example.bsu.model.Mail;
import com.example.bsu.utils.HibernateSessionFactoryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MailDao {
    public static Mail findByUserId(int userId) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Mail mail = (Mail) session.createQuery("FROM Mail WHERE user_id = :userId").setParameter("userId", userId).uniqueResult();
        session.close();
        return mail;
    }
    public static void save(Mail mail) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(mail);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
        } finally {
            session.close();
        }
    }
    public static void update(Mail mail) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(mail);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
        } finally {
            session.close();
        }
    }
}
