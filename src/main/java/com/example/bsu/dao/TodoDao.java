package com.example.bsu.dao;

import com.example.bsu.model.Todo;
import com.example.bsu.utils.HibernateSessionFactoryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TodoDao {
    public static List<Todo> findAllByUserId(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Todo> todos =  session.createQuery("FROM Todo WHERE user_id = :id",Todo.class).setParameter("id",id).list();
        session.close();
        return todos;
    }
    public static Todo findById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Todo todo = session.find(Todo.class,id);
        session.close();
        return todo;
    }
    public static void update(Todo todo) {
        Session session =  HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(todo);
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null) tx.rollback();
        } finally {
            session.close();
        }
    }
    public static void save(Todo todo) {
        Session session =  HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(todo);
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null) tx.rollback();
        } finally {
            session.close();
        }
    }
    public static void delete(Todo todo) {
        Session session =  HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(todo);
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null) tx.rollback();
        } finally {
            session.close();
        }
    }
    public  static  void deleteAll(int userId) {
        Session session =  HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.createQuery("DELETE FROM Todo WHERE user_id = :userId").setParameter("userId", userId).executeUpdate();
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null) tx.rollback();
        } finally {
            session.close();
        }
    }
}
