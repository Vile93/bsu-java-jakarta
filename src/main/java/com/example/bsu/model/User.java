package com.example.bsu.model;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable=false, unique = false)
    @Min(value = 3, message = "The minimum length of a username is 3 characters")
    @Max(value = 20, message = "The maximum length of a username is 20 characters")
    private String name;

    @Column(name = "password",nullable=false, unique = false)
    @Min(value = 4,message = "Minimum password length is 4 characters")
    @Max(value = 8,message = "Maximum password length is 8 characters")
    private String password;

    @Column(name = "email",nullable=false, unique = false)
    @Email(message = "Invalid email")
    private String email;

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

}