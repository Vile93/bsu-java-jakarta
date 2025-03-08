package com.example.bsu.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "mail")
public class Mail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "verification_code",nullable = false)
    private String verificationCode;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false,referencedColumnName = "id")
    private User user;

    @Column(name = "expiration_date")
    private String expiration;

    public Mail() {
        this.expiration = LocalDateTime.now().plusMinutes(15).toString();
    }
    public Mail(UUID verificationCode, User user) {
        this.verificationCode = verificationCode.toString();
        this.user = user;
        this.expiration = LocalDateTime.now().plusMinutes(15).toString();
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public UUID getVerificationCode() {
        return UUID.fromString(this.verificationCode);
    }
    public void setVerificationCode(UUID verificationCode) {
        this.verificationCode = verificationCode.toString();
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
