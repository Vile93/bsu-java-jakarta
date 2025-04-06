package com.example.bsu.service;

import com.example.bsu.controller.TodoController.TodoRequest;
import com.example.bsu.dao.TodoDao;
import com.example.bsu.dao.UserTodoDao;
import com.example.bsu.model.Todo;
import com.example.bsu.model.User;
import com.example.bsu.model.UserTodo;
import com.example.bsu.utils.ForbiddenExceptionUtil;
import com.example.bsu.utils.ValidationFailedExceptionUtil;

import java.util.List;

public class TodoService {
    public static List<Todo> findAllByUserId(User user) {
        List<Todo> todos = TodoDao.findAllByUserId(user.getId());
        List<Todo> relatedTodos = UserTodoDao.findByUser(user).stream().map(userTodo -> userTodo.getTodo()).toList();
        todos.addAll(relatedTodos);
        return todos;
    }
    public static Todo findById(int todoId, User user) throws ForbiddenExceptionUtil {
        Todo todo = TodoDao.findById(todoId);
        if(!todo.getUser().getId().equals(user.getId())) {
            UserTodo userTodo = UserTodoDao.findByUserAndTodoId(user, todo);
            if(userTodo == null) {
                throw new ForbiddenExceptionUtil();
            }
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
        if(!todo.getUser().getId().equals(userId)) {
            throw new ForbiddenExceptionUtil();
        }
        UserTodoDao.deleteAllByTodo(todo);
        TodoDao.delete(todo);
    }
    public static void update(TodoRequest todoRequest,User user) throws ValidationFailedExceptionUtil,ForbiddenExceptionUtil {
       Todo dbTodo = TodoDao.findById(todoRequest.getId());
       if(dbTodo == null) {
               throw  new ForbiddenExceptionUtil();
       }
        if(!dbTodo.getUser().getId().equals(user.getId())) {
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
