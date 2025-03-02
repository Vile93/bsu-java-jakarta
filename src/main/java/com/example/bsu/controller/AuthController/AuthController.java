package com.example.bsu.controller.AuthController;

import com.example.bsu.model.User;
import com.example.bsu.utils.HibernateSessionFactoryUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;

@WebServlet("/api/auth/*")
public class AuthController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();
    private void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        AuthRequestLogin authRequestLogin = mapper.readValue(request.getReader(), AuthRequestLogin.class);

    }
    private void doLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    private void doRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        User user = new User("admin", "admin", "admin");
        session.save(user);
        tx1.commit();
        session.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        logger.info("doRegister " + path);
        System.out.println(path + "Test");
        switch (path) {
            case "/login":
                doLogin(request, response);
                break;
            case "/logout":
                doLogout(request, response);
                break;
            case "/register":
                 doRegister(request, response);
                 break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;

        }
    }
}
