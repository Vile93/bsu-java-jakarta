package com.example.bsu.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name",unique=true, nullable=false)
    private String name;

    @Column(name = "password",nullable=false)
    private String password;

    @Column(name = "email",nullable=false)
    private String email;

//    @OneToMany(mappedBy = "user_id", cascade = CascadeType.ALL)
//    private List<Todo> todos;
//
//    @OneToOne(mappedBy = "user_id", cascade = CascadeType.ALL)
//    private Session session;

    public User() {}

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public Session getSession() {
//        return session;
//    }
//
//    public void setSession(Session session) {
//        this.session = session;
//    }

}