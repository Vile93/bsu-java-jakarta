package com.example.bsu.controller.MailController;

import com.example.bsu.model.Session;
import com.example.bsu.service.MailService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/api/mail/*")
public class MailController extends HttpServlet {
    private void doSend(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session session = (Session) request.getAttribute("session");
        MailService.sendCode(session);
    }
    private void doCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session session = (Session) request.getAttribute("session");
        String mailId = request.getParameter("mailId");
        MailService.check(UUID.fromString(mailId),session);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        switch (path) {
            case "/send":
                doSend(request,response);
                break;
            case "/check":
                doCheck(request,response);
                break;
            default:
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }
}
