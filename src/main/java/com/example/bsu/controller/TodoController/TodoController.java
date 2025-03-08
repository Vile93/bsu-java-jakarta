package com.example.bsu.controller.TodoController;


import com.example.bsu.model.Session;
import com.example.bsu.model.Todo;
import com.example.bsu.service.TodoService;
import com.example.bsu.utils.ForbiddenExceptionUtil;
import com.example.bsu.utils.ValidationFailedExceptionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/api/todos")
public class TodoController extends HttpServlet {
    private void getTodos(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Session session = (Session) request.getAttribute("session");
        List<Todo> todos = TodoService.findAllByUserId(session.getUser().getId());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter out = response.getWriter();
        out.print("[");
        for(int i = 0; i < todos.size(); i++) {
            Todo todo = todos.get(i);
            out.print("{ \"id\":" + todo.getId());
            out.print(", \"title\":\"" + todo.getTitle() + "\"");
            out.print(", \"description\":\"" + todo.getDescription() + "\"");
            if(i == todos.size() - 1) {
                out.print("}");
            } else {
                out.print("},");
            }
        }
        out.print("]");
        out.flush();

    }
    private void getTodo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Session session = (Session) request.getAttribute("session");
            Todo todo = TodoService.findById(Integer.parseInt(request.getParameter("id")), session.getUser().getId());
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_OK);
            PrintWriter out = response.getWriter();
            out.print("{ \"id\":" + todo.getId());
            out.print(", \"title\":\"" + todo.getTitle() + "\"");
            out.print(", \"description\":\"" + todo.getDescription() + "\"}");
            out.flush();
        } catch (ForbiddenExceptionUtil fe) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            if(request.getParameter("id") != null) {
                getTodo(request,response);
            } else {
                getTodos(request, response);
            }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
        Session session = (Session) request.getAttribute("session");
        ObjectMapper mapper = new ObjectMapper();
        TodoRequest todoRequest = mapper.readValue(request.getReader(), TodoRequest.class);
        TodoService.create(todoRequest, session.getUser());
        response.setStatus(HttpServletResponse.SC_CREATED);
    } catch (ValidationFailedExceptionUtil ve) {
        response.setStatus(HttpServletResponse.SC_UNPROCESSABLE_CONTENT);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().write(ve.getJSONMessage());
    }
    }
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       try {
           Session session = (Session) request.getAttribute("session");
           ObjectMapper mapper = new ObjectMapper();
           TodoRequest todoRequest = mapper.readValue(request.getReader(), TodoRequest.class);
           TodoService.update(todoRequest, session.getUser());
           response.setStatus(HttpServletResponse.SC_OK);
       } catch (ValidationFailedExceptionUtil ve) {
           response.setStatus(HttpServletResponse.SC_UNPROCESSABLE_CONTENT);
           response.setCharacterEncoding("UTF-8");
           response.setContentType("application/json");
           response.getWriter().write(ve.getJSONMessage());
       } catch (ForbiddenExceptionUtil fe) {
           response.setStatus(HttpServletResponse.SC_FORBIDDEN);
       }
    }
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Session session = (Session) request.getAttribute("session");
            String todoId = request.getParameter("id");
            TodoService.delete(Integer.parseInt(todoId), session.getUser().getId());
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (ForbiddenExceptionUtil fe) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
