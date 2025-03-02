package com.example.bsu.dao;

import com.example.bsu.model.Todo;
import com.example.bsu.model.User;
import com.example.bsu.utils.HibernateSessionFactoryUtil;

import java.util.List;


public class UserDao {

    public User findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().find(User.class, id);
    }
    public void save(User user) {
        HibernateSessionFactoryUtil.getSessionFactory().openSession().save(user);
    }
    public void update(User user) {
        HibernateSessionFactoryUtil.getSessionFactory().openSession().update(user);
    }
    public void delete(User user) {
        HibernateSessionFactoryUtil.getSessionFactory().openSession().delete(user);
    }
}
