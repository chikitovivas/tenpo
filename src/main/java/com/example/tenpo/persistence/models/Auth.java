package com.example.tenpo.persistence.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.OffsetDateTime;

@Entity
public class Auth {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String token;
    private OffsetDateTime expirationDate;
    private Boolean active;

    public Auth(Long userId, String token, OffsetDateTime expirationDate) {
        this.userId = userId;
        this.token = token;
        this.expirationDate = expirationDate;
        this.setActive(true);
    }

    public Auth() {
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getToken() {
        return token;
    }

    public OffsetDateTime getExpirationDate() {
        return expirationDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setExpirationDate(OffsetDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
