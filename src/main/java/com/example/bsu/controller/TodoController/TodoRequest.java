package com.example.bsu.controller.TodoController;

public class TodoRequest {
    private int id;
    private String title;
    private String description;
    private String imagePath;

    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public int getId() {
        return id;
    }
    public String getImagePath() {
        return imagePath;
    }
}
