package com.example.bsu.controller.MailController;

import com.example.bsu.model.Session;
import com.example.bsu.service.MailService;
import com.example.bsu.utils.ValidationFailedExceptionUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/api/mail/*")
public class MailController extends HttpServlet {
    private void doSend(HttpServletRequest request, HttpServletResponse response) throws IOException, ValidationFailedExceptionUtil {
        Session session = (Session) request.getAttribute("session");
        MailService.sendCode(session);
    }
    private void doCheck(HttpServletRequest request, HttpServletResponse response) throws IOException, ValidationFailedExceptionUtil {
        Session session = (Session) request.getAttribute("session");
        String mailId = request.getParameter("mailId");
        MailService.check(UUID.fromString(mailId), session);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String path = request.getPathInfo();
            switch (path) {
                case "/send":
                    doSend(request, response);
                    break;
                case "/check":
                    doCheck(request, response);
                    break;
                default:
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    break;
            }
        } catch (ValidationFailedExceptionUtil ve) {
            response.setStatus(HttpServletResponse.SC_UNPROCESSABLE_CONTENT);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().write(ve.getJSONMessage());
        }
    }
}
