package com.example.bsu.controller.TodoController;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
import java.util.List;

@WebServlet("/api/todos")
public class TodoController extends HttpServlet {
    private void getTodos(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Session session = (Session) request.getAttribute("session");
        JSONArray  jsonArray = new JSONArray();
        List<Todo> todos = TodoService.findAllByUserId(session.getUser().getId());
        for(int i = 0; i < todos.size(); i++) {
            Todo todo = todos.get(i);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", todo.getId());
            jsonObject.put("title", todo.getTitle());
            jsonObject.put("description", todo.getDescription());
            jsonArray.add(jsonObject);
        }
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(jsonArray.toJSONString());
    }
    private void getTodo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Session session = (Session) request.getAttribute("session");
            JSONObject jsonObject = new JSONObject();
            Todo todo = TodoService.findById(Integer.parseInt(request.getParameter("id")), session.getUser());
            jsonObject.put("id", todo.getId());
            jsonObject.put("title", todo.getTitle());
            jsonObject.put("description", todo.getDescription());
            jsonObject.put("imagePath", todo.getImagePath());
            jsonObject.put("isUserTodo", todo.getUser().getId().equals(session.getUser().getId()));
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write(jsonObject.toJSONString());
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
