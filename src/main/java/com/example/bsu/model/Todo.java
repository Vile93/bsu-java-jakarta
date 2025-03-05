package com.example.bsu.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "todos")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title",nullable = false)
    @NotEmpty(message = "Title is a required field")
    @Max(value = 50, message = "Maximum title length is 50 characters")
    private String title;

    @Column(name = "description", nullable = true)
    @Max(value = 200, message = "Maximum description length is 200 characters")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private User user;

    @Column(name = "image_path", nullable = true)
    private String imagePath;


    public Todo() {}
    public Todo(String title, String description, User user) {
        this.title = title;
        this.description = description;
        this.user = user;
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
    public String getImagePath() {
        return imagePath;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
