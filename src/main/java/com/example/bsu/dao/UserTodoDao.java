package com.example.bsu.dao;

import com.example.bsu.model.User;
import com.example.bsu.model.UserTodo;
import com.example.bsu.utils.HibernateSessionFactoryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserTodoDao {
    public List<UserTodo> getByUser(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<UserTodo> userTodos = session.createQuery("FROM UserTodo WHERE userId = :userId").setParameter("userId", user.getId()).list();
        session.close();
        return  userTodos;
    }
    public List<UserTodo> getByTodoId(int todoId) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<UserTodo> userTodos = session.createQuery("FROM UserTodo WHERE todoId = :todoId").setParameter("todoId", todoId).list();
        session.close();
        return userTodos;
    }
    public void save(UserTodo userTodo) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(userTodo);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
        } finally {
            session.close();
        }
    }
    public void delete(int userId, int todoId) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.createQuery("DELETE FROM UserTodo WHERE userId = :userId AND todoId = :todoId", UserTodo.class).setParameter("userId", userId).setParameter("todoId", todoId).executeUpdate();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
        } finally {
            session.close();
        }
    }
    public void deleteAll(User author) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.createQuery("DELETE FROM UserTodo WHERE authorId = :authorId", UserTodo.class).setParameter("authorId", author.getId()).executeUpdate();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
        } finally {
            session.close();
        }
    }
}
