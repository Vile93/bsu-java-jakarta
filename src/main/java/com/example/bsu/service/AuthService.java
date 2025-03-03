package com.example.bsu.service;

import com.example.bsu.controller.AuthController.AuthRequestLogin;
import com.example.bsu.controller.AuthController.AuthRequestRegister;
import com.example.bsu.dao.UserDao;
import com.example.bsu.model.Session;
import com.example.bsu.model.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AuthService {
    public static void register(HttpServletRequest request,HttpServletResponse response,AuthRequestRegister authRequestRegister) throws IOException {
        User dbUser = UserDao.findByUsername(authRequestRegister.getUsername());
        if (dbUser != null) {
            String jsonResponse = "{: \"error\": \"username already in use\"}";
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            response.getWriter().write(jsonResponse);
            response.getWriter().flush();
            return;
        }
        String password = BcryptService.encrypt(authRequestRegister.getPassword());
        User newUser = new User();
        newUser.setPassword(password);
        newUser.setName(authRequestRegister.getUsername());
        newUser.setEmail(authRequestRegister.getEmail());
        UserDao.save(newUser);
        String jsonResponse = "{: \"success\": \"" + authRequestRegister.getUsername() + "\"}";
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_CREATED);
        response.getWriter().write(jsonResponse);
        response.getWriter().flush();
    }
    public static void login(HttpServletRequest request, HttpServletResponse response,AuthRequestLogin authRequestLogin) throws IOException {
        User dbUser = UserDao.findByUsername(authRequestLogin.getUsername());
        if(dbUser == null) {
            String jsonResponse = "{: \"error\": \"username not found\"}";
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write(jsonResponse);
            response.getWriter().flush();
            return;
        }
        Boolean isCorrectPassword = BcryptService.verify(authRequestLogin.getPassword(), dbUser.getPassword());
        if(!isCorrectPassword) {
            String jsonResponse = "{: \"error\": \"wrong password\"}";
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(jsonResponse);
            response.getWriter().flush();
            return;
        }
        // TODO session
        String jsonResponse = "{: \"success\": \"" + authRequestLogin.getUsername() + "\"}";
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(jsonResponse);
        response.getWriter().flush();
    }
    public static void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        Cookie session = CookieService.getCookie(httpServletRequest,"session");
        if(session != null) {
                Cookie sessionCookie = new Cookie("session", "");
                sessionCookie.setMaxAge(0);
                sessionCookie.setPath("/");
                httpServletResponse.addCookie(sessionCookie);
                httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
