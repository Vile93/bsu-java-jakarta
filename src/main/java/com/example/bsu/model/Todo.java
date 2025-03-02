package com.example.bsu.model;

import javax.persistence.*;

@Entity
@Table(name = "todos")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "description",nullable = true)
    private String description;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    public Todo() {}
    public Todo(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
