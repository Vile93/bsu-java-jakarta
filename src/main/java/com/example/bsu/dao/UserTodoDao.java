package com.example.bsu.dao;

import com.example.bsu.model.User;
import com.example.bsu.model.UserTodo;
import com.example.bsu.utils.HibernateSessionFactoryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserTodoDao {
    public static UserTodo findByUserTodo(UserTodo userTodo) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        UserTodo resultUserTodo = (UserTodo) session.createQuery("FROM UserTodo ut WHERE ut.user.id = :userId AND ut.todo.id = :todoId AND ut.author.id = :authorId").setParameter("userId",userTodo.getUser().getId()).setParameter("authorId", userTodo.getAuthor().getId()).setParameter("todoId", userTodo.getTodo().getId()).uniqueResult();
        session.close();
        return resultUserTodo;
    }
    public static List<UserTodo> findByUser(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<UserTodo> userTodos = session.createQuery("FROM UserTodo ut WHERE ut.user.id = :userId").setParameter("userId", user.getId()).list();
        session.close();
        return  userTodos;
    }
    public static List<UserTodo> findByTodoId(int todoId) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<UserTodo> userTodos = session.createQuery("FROM UserTodo ut WHERE ut.todo.id = :todoId").setParameter("todoId", todoId).list();
        session.close();
        return userTodos;
    }
    public static void save(UserTodo userTodo) {
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
    public static void delete(int userId, int todoId) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.createQuery("DELETE FROM UserTodo ut WHERE ut.user.id = :userId AND ut.todo.id = :todoId").setParameter("userId", userId).setParameter("todoId", todoId).executeUpdate();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
        } finally {
            session.close();
        }
    }
    public static void deleteAll(User author) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.createQuery("DELETE FROM UserTodo ut WHERE ut.author.id = :authorId").setParameter("authorId", author.getId()).executeUpdate();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
        } finally {
            session.close();
        }
    }
}
