package com.example.bsu.dao;

import com.example.bsu.model.Todo;
import com.example.bsu.utils.HibernateSessionFactoryUtil;

import java.util.List;

public class TodoDao {
    public List<Todo> findAll() {
        List<Todo> todos = (List<Todo>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("FROM  Todo").list();
        return  todos;
    }
    public Todo findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().find(Todo.class,id);
    }
    public void update(int id) {
        HibernateSessionFactoryUtil.getSessionFactory().openSession().update(id);
    }
    public void delete(int id) {
        HibernateSessionFactoryUtil.getSessionFactory().openSession().delete(id);
    }
    public void create(Todo todo) {
        HibernateSessionFactoryUtil.getSessionFactory().openSession().save(todo);
    }
}
