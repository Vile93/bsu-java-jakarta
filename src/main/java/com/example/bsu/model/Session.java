package com.example.bsu.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "sessions")
public class Session {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false,referencedColumnName = "id")
    private User user;

    public Session() {}
    public Session(UUID id,User user) {
        this.id = id.toString();
        this.user = user;
    }

    public UUID getId() {
        return UUID.fromString(id);
    }
    public void setId(UUID id) {
        this.id = id.toString();
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
