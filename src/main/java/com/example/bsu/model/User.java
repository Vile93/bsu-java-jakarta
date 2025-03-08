package com.example.bsu.model;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable=false, unique = false)
    @Size(min = 3, max = 20, message = "The name length must be between 3 and 20 characters")
    private String name;

    @Column(name = "password",nullable=false, unique = false)
    @Size(min = 4, message = "The password length must be at least 4 characters")
    private String password;

    @Column(name = "email",nullable=false, unique = false)
    @Email(message = "Invalid email")
    private String email;

    @Column(name = "is_verified", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean verified;

    public User() {}

    public User(String name, String email, String password, boolean verified) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.verified = verified;
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

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}