package com.example.bsu.model;

import javax.persistence.*;

@Entity
@Table(name = "sessions")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "session")
    private String session;

    @OneToOne
    @JoinColumn(name = "user_id",nullable = false,referencedColumnName = "id")
    private User user;

    public Session() {}
    public Session(String session, User user) {
        this.session = session;
        this.user = user;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getSession() {
        return session;
    }
    public void setSession(String session) {
        this.session = session;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
