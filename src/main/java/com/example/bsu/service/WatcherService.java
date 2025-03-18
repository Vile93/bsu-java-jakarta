package com.example.bsu.service;

import com.example.bsu.dao.UserTodoDao;
import com.example.bsu.model.Todo;
import com.example.bsu.model.User;
import com.example.bsu.model.UserTodo;
import com.example.bsu.utils.BadRequestExceptionUtil;

import java.util.List;

public class WatcherService {
    public static List<UserTodo> findByUser(User user) {
        return UserTodoDao.findByUser(user);
    }
    public static List<UserTodo> findByTodoId(User author,int todoId) throws BadRequestExceptionUtil {
        Todo todo = TodoService.findById(todoId);
        if(todo.getUser().getId() != author.getId()) {
            throw new BadRequestExceptionUtil();
        }
        return UserTodoDao.findByTodoId(todoId);
    }
    public static void create(User author,String username, int todoId) throws BadRequestExceptionUtil {
        UserTodo userTodo = new UserTodo();
        Todo todo = TodoService.findById(todoId);
        if(todo.getUser().getId() != author.getId()) {
            throw new BadRequestExceptionUtil();
        }
        User user = UserService.findByUsername(username);
        if(user == null || user.getId() == author.getId()) {
            throw new BadRequestExceptionUtil();
        }

        userTodo.setTodo(todo);
        userTodo.setUser(user);
        userTodo.setAuthor(author);
        UserTodo dbUserTodo = UserTodoDao.findByUserTodo(userTodo);
        if(dbUserTodo != null) {
            throw new BadRequestExceptionUtil();
        }
        UserTodoDao.save(userTodo);
    }
    public static void deleteByUsername(User author, String username,int todoId) throws BadRequestExceptionUtil {
        User user = UserService.findByUsername(username);
        Todo todo = TodoService.findById(todoId);
        if(todo.getUser().getId() != author.getId()) {
            throw new BadRequestExceptionUtil();
        }
        if(user.getId() == author.getId()) {
            throw new BadRequestExceptionUtil();
        }
        UserTodoDao.delete(user.getId(), todoId);
    }
    public static void deleteAll(User author) {
        UserTodoDao.deleteAll(author);
    }

}
