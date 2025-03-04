package com.example.bsu.service;

import com.example.bsu.controller.TodoController.TodoRequest;
import com.example.bsu.dao.TodoDao;
import com.example.bsu.model.Todo;
import com.example.bsu.model.User;

import java.util.List;

public class TodoService {
    public static List<Todo> findAllByUserId(int userId) {
        return TodoDao.findAllByUserId(userId);
    }
    public static Todo findById(int id) {
        return TodoDao.findById(id);
    }
    public  static void create(TodoRequest todoRequest, User user) {
        Todo todo = new Todo();
        todo.setTitle(todoRequest.getTitle());
        todo.setDescription(todoRequest.getDescription());
        todo.setUser(user);
        TodoDao.create(todo);
    }
    public static void delete(int id) {
        Todo todo = TodoDao.findById(id);
        TodoDao.delete(todo);
    }
    public static void deleteAll(int userId) {
        TodoDao.deleteAll(userId);
    }
    public static void update(TodoRequest todoRequest,User user) {
        Todo todo = new Todo();
        todo.setId(todoRequest.getId());
        todo.setTitle(todoRequest.getTitle());
        todo.setDescription(todoRequest.getDescription());
        todo.setUser(user);
        TodoDao.update(todo);
    }
}
