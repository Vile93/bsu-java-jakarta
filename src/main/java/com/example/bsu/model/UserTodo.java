package com.example.bsu.model;

import javax.persistence.*;

@Entity
@Table(name = "user_todo")
public class UserTodo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id", nullable = false)
    @OneToMany
    @JoinColumn(referencedColumnName = "id")
    private User user;

    @Column(name = "author_id", nullable = false)
    @OneToMany
    @JoinColumn(referencedColumnName = "id")
    private User author;

    @Column(name = "todo_id", nullable = false)
    @OneToMany
    @JoinColumn(referencedColumnName = "id")
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
