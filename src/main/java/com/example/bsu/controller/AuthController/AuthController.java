package com.example.bsu.controller.AuthController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/api/auth/*")
public class AuthController {
    private void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    private void doLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    private void doRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        switch (path) {
            case "/login":
                doLogin(request, response);
            case "/logout":
                doLogout(request, response);
            case "/register":
                doRegister(request, response);
        }
    }
}
