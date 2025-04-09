package com.example.bsu.AuthController;

import com.example.bsu.controller.AuthController.AuthController;
import com.example.bsu.dao.UserDao;
import com.example.bsu.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.StringReader;

public class RegisterUserTest extends Mockito {
    @Before
    public void setUp() throws Exception {
        User user = UserDao.findByUsername("user");
        if (user != null) {
            UserDao.delete(user);
        }
    }

    @Test
    public void test() throws Exception {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        JSONObject requestJSON = new JSONObject();

        requestJSON.put("username", "user");
        requestJSON.put("password", "password");
        requestJSON.put("email", "user@example.com");

        when(req.getReader()).thenReturn(new BufferedReader(new StringReader(requestJSON.toString())));
        when(req.getPathInfo()).thenReturn("/register");
        new AuthController().doPost(req, resp);

        verify(resp).setStatus(HttpServletResponse.SC_CREATED);

    }

    @After
    public void tearDown() throws Exception {
        User user = UserDao.findByUsername("user");
        if (user != null) {
            UserDao.delete(user);
        }
    }
}
