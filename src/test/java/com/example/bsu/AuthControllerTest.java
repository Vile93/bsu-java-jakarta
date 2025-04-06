package com.example.bsu;

import com.example.bsu.controller.AuthController.AuthController;
import com.example.bsu.dao.UserDao;
import com.example.bsu.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.StringReader;

public class AuthControllerTest extends Mockito {

    @Test
    public void registerUser() throws Exception {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        JSONObject requestJSON = new JSONObject();
        User user = UserDao.findByUsername("user");
        if(user != null) {
            UserDao.delete(user);
        }

        requestJSON.put("username", "user");
        requestJSON.put("password", "password");
        requestJSON.put("email", "user@example.com");

        when(req.getReader()).thenReturn(new BufferedReader(new StringReader(requestJSON.toString())));
        when(req.getPathInfo()).thenReturn("/register");
        new AuthController().doPost(req, resp);

        verify(resp).setStatus(HttpServletResponse.SC_CREATED);

    }


    @Test
    public void registerUserWithDuplicateUsername() throws Exception {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        JSONObject requestJSON = new JSONObject();
        User user = UserDao.findByUsername("user");
        if(user == null) {
            User newUser = new User();
            newUser.setEmail("user@example.com");
            newUser.setPassword("password");
            newUser.setName("user");
            UserDao.save(newUser);
        }
        // TODO
    }

    @After
    public void tearDown() throws Exception {
        User user = UserDao.findByUsername("user");
        if(user != null) {
            UserDao.delete(user);
        }
        user = UserDao.findByEmail("user@example.com");
        if(user != null) {
            UserDao.delete(user);
        }
    }
}
