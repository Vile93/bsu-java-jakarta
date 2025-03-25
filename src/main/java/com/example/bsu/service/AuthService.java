package com.example.bsu.service;

import com.example.bsu.controller.AuthController.AuthRequestLogin;
import com.example.bsu.controller.AuthController.AuthRequestRegister;
import com.example.bsu.dao.SessionDao;
import com.example.bsu.dao.UserDao;
import com.example.bsu.model.Session;
import com.example.bsu.model.User;
import com.example.bsu.utils.ValidationFailedExceptionUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

public class AuthService {
    public static void register(HttpServletResponse response,AuthRequestRegister authRequestRegister) throws IOException, ValidationFailedExceptionUtil {
        User newUser = new User();
        newUser.setPassword(authRequestRegister.getPassword());
        newUser.setName(authRequestRegister.getUsername());
        newUser.setEmail(authRequestRegister.getEmail());
        ValidationFailedExceptionUtil ve = new ValidationFailedExceptionUtil();
        ve.validate(newUser);
        String encryptedPassword = BcryptService.encrypt(newUser.getPassword());
        newUser.setPassword(encryptedPassword);
        UserDao.save(newUser);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_CREATED);
    }
    public static void login(HttpServletResponse response,AuthRequestLogin authRequestLogin) throws IOException {
        User dbUser = UserDao.findByUsername(authRequestLogin.getUsername());
        Boolean isCorrectPassword = null;
        UUID sessionId = null;

        if(dbUser == null) {
            String jsonResponse = "{ \"message\": \"username not found\"}";
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write(jsonResponse);
            response.getWriter().flush();
            return;
        }
        isCorrectPassword = BcryptService.verify(authRequestLogin.getPassword(), dbUser.getPassword());
        if(!isCorrectPassword) {
            String jsonResponse = "{ \"message\": \"wrong password\"}";
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(jsonResponse);
            response.getWriter().flush();
            return;
        }
        sessionId = SessionDao.save(dbUser);
        Cookie sessionCookie = new Cookie("session", sessionId.toString());
        sessionCookie.setPath("/");
        sessionCookie.setHttpOnly(true);
        sessionCookie.setMaxAge(7 * 24 * 60 * 60);
        response.addCookie(sessionCookie);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{ \"isVerified\": \"" + dbUser.isVerified() + "\"}" );
        response.setStatus(HttpServletResponse.SC_OK);
    }
    public static void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie session = CookieService.getCookie(request,"session");
        if(session == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().write("{ \"message\": \"session not found\"}");
            return;
        }
        Session s = SessionDao.findById(UUID.fromString(session.getValue()));
        if(s == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().write("{ \"message\": \"session not found\"}");
            return;
        }
        SessionDao.deleteById(s.getId());
        Cookie sessionCookie = new Cookie("session", "");
        sessionCookie.setMaxAge(0);
        sessionCookie.setPath("/");
        sessionCookie.setHttpOnly(true);
        response.addCookie(sessionCookie);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
