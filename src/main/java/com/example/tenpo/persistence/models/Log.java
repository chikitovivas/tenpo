package com.example.tenpo.persistence.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.OffsetDateTime;

@Entity
public class Log {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String request;
    private String response;
    private OffsetDateTime date;
    private String url;

    public Log(String request, String response, OffsetDateTime date, String url) {
        this.request = request;
        this.response = response;
        this.date = date;
        this.url = url;
    }

    public Log() {
    }

    public String getUrl() {
        return url;
    }

    public Long getId() {
        return id;
    }

    public String getRequest() {
        return request;
    }

    public String getResponse() {
        return response;
    }

    public OffsetDateTime getDate() {
        return date;
    }
}
