package com.example.bsu.service;

import com.example.bsu.controller.TodoController.TodoRequest;
import com.example.bsu.dao.TodoDao;
import com.example.bsu.model.Todo;
import com.example.bsu.model.User;
import com.example.bsu.utils.ForbiddenExceptionUtil;
import com.example.bsu.utils.ValidationFailedExceptionUtil;

import java.util.List;

public class TodoService {
    public static List<Todo> findAllByUserId(int userId) {
        return TodoDao.findAllByUserId(userId);
    }
    public static Todo findById(int todoId, int userId) throws ForbiddenExceptionUtil {
        Todo todo = TodoDao.findById(todoId);
        if(todo.getUser().getId() != userId) {
            throw new ForbiddenExceptionUtil();
        }
        return todo;
    }
    public  static void create(TodoRequest todoRequest, User user) throws ValidationFailedExceptionUtil {
        Todo todo = new Todo();
        todo.setTitle(todoRequest.getTitle());
        todo.setDescription(todoRequest.getDescription());
        todo.setUser(user);
        todo.setImagePath(todoRequest.getImagePath());
        ValidationFailedExceptionUtil ve = new ValidationFailedExceptionUtil();
        ve.validate(todo);
        TodoDao.save(todo);
    }
    public static void delete(int todoId, int userId) throws ForbiddenExceptionUtil {
        Todo todo = TodoDao.findById(todoId);
        if(todo.getUser().getId() != userId) {
            throw new ForbiddenExceptionUtil();
        }
        TodoDao.delete(todo);
    }
    public static void update(TodoRequest todoRequest,User user) throws ValidationFailedExceptionUtil,ForbiddenExceptionUtil {
        if(user.getId() != todoRequest.getId()) {
            throw new ForbiddenExceptionUtil();
        }
        Todo todo = new Todo();
        todo.setId(todoRequest.getId());
        todo.setTitle(todoRequest.getTitle());
        todo.setDescription(todoRequest.getDescription());
        todo.setImagePath(todoRequest.getImagePath());
        todo.setUser(user);
        ValidationFailedExceptionUtil ve = new ValidationFailedExceptionUtil();
        ve.validate(todo);
        TodoDao.update(todo);
    }
}
