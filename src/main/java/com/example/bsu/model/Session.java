package com.example.bsu.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "sessions")
public class Session {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false,referencedColumnName = "id")
    private User user;

    @Column(name = "expiration_date")
    private String expiration;

    public Session() {
        this.expiration = LocalDateTime.now().plusDays(7).toString();
    }
    public Session(UUID id,User user) {
        this.id = id.toString();
        this.user = user;
        this.expiration = LocalDateTime.now().plusDays(7).toString();
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
    public String getExpiration() {
        return expiration;
    }
    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }
}
