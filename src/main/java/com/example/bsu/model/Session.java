package com.example.bsu.model;

import javax.persistence.*;

@Entity
@Table(name = "sessions")
public class Session {

    @Id
    private String id;

    @OneToOne
    @JoinColumn(name = "user_id",nullable = false,referencedColumnName = "id")
    private User user;

    public Session() {}
    public Session(String id,User user) {
        this.id = id;
        this.user = user;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
