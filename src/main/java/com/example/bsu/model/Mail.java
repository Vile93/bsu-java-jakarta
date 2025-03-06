package com.example.bsu.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "mail")
public class Mail {
    @Id
    private String id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false,referencedColumnName = "id")
    private User user;

    @Column(name = "expiration_date")
    private String expiration;

    public Mail() {
        this.expiration = LocalDateTime.now().toString();
    }
    public Mail(UUID id, User user) {
        this.id = id.toString();
        this.user = user;
        this.expiration = LocalDateTime.now().plusMinutes(15).toString();
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
