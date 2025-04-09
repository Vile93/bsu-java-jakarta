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
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

public class RegisterUserWithDuplicateUsernameTest extends Mockito {

    @Before
    public void setUp() {
        User user = UserDao.findByUsername("user");
        if(user == null) {
            User newUser = new User();
            newUser.setEmail("user@example.com");
            newUser.setPassword("password");
            newUser.setName("user");
            UserDao.save(newUser);
        }
    }

    @Test
    public void test() throws Exception {
            HttpServletRequest req = mock(HttpServletRequest.class);
            HttpServletResponse resp = mock(HttpServletResponse.class);
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            JSONObject requestJSON = new JSONObject();
            JSONObject expectedJSON = new JSONObject();

            requestJSON.put("username", "user");
            requestJSON.put("email", "unique@example.com");
            requestJSON.put("password", "password");
            expectedJSON.put("message", "Username already in use");

            when(resp.getWriter()).thenReturn(pw);
            when(req.getReader()).thenReturn(new BufferedReader(new StringReader(requestJSON.toString())));
            when(req.getPathInfo()).thenReturn("/register");
            new AuthController().doPost(req, resp);

            assertEquals(expectedJSON.toString(), sw.toString());
            verify(resp).setStatus(HttpServletResponse.SC_UNPROCESSABLE_CONTENT);
        }

    @After
    public  void  tearDown() {
        User user = UserDao.findByUsername("user");
        if (user != null) {
            UserDao.delete(user);
        }
    }
}
