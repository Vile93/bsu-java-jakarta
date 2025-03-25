package com.example.bsu.utils;


import com.example.bsu.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.DriverManager;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;
    private static final Logger logger = LogManager.getLogger(HibernateSessionFactoryUtil.class);

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                DriverManager.registerDriver(new org.mariadb.jdbc.Driver());
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Todo.class);
                configuration.addAnnotatedClass(Session.class);
                configuration.addAnnotatedClass(Mail.class);
                configuration.addAnnotatedClass(UserTodo.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                logger.error(e);
            }
        }
        return sessionFactory;
    }
}