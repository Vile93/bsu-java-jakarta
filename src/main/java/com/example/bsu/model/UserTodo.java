package com.example.bsu.model;

import javax.persistence.*;

@Entity
@Table(name = "user_todo")
public class UserTodo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false,referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false,referencedColumnName = "id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "todo_id", nullable = false,referencedColumnName = "id")
    private Todo todo;

    public UserTodo() {}
    public UserTodo(User user, User author, Todo todo) {
            this.user = user;
            this.author = author;
            this.todo = todo;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public User getAuthor() {
        return author;
    }
    public void setAuthor(User author) {
        this.author = author;
    }
    public Todo getTodo() {
        return this.todo;
    }
    public void setTodo(Todo todo) {
        this.todo = todo;
    }
}
