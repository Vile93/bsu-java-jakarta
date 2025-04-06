package com.example.bsu.service;

import com.example.bsu.dao.TodoDao;
import com.example.bsu.dao.UserDao;
import com.example.bsu.dao.UserTodoDao;
import com.example.bsu.model.Todo;
import com.example.bsu.model.User;
import com.example.bsu.model.UserTodo;
import com.example.bsu.utils.BadRequestExceptionUtil;

import java.util.List;

public class WatcherService {
    public static List<UserTodo> findByTodoId(User author,  int todoId) throws BadRequestExceptionUtil {
        Todo todo = TodoDao.findById(todoId);
        if(!todo.getUser().getId().equals(author.getId())) {
            throw new BadRequestExceptionUtil();
        }
        return UserTodoDao.findByTodoId(todoId);
    }
    public static void create(User author,String username, int todoId) throws BadRequestExceptionUtil {
        UserTodo userTodo = new UserTodo();
        Todo todo = TodoDao.findById(todoId);
        if(!todo.getUser().getId().equals(author.getId())) {
            throw new BadRequestExceptionUtil();
        }
        User user = UserDao.findByUsername(username);
        if(user == null || user.getId().equals(author.getId())) {
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
        User user = UserDao.findByUsername(username);
        Todo todo = TodoDao.findById(todoId);
        if(!todo.getUser().getId().equals(author.getId())) {
            throw new BadRequestExceptionUtil();
        }
        if(user.getId().equals(author.getId())) {
            throw new BadRequestExceptionUtil();
        }
        UserTodoDao.delete(user.getId(), todoId);
    }

}
