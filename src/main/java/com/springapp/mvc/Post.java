package com.springapp.mvc;

import java.sql.Timestamp;

/**
 * Created by Никита on 21.11.2015.
 */
public class Post {
    private int id;
    private User author;
    private String content;
    private Timestamp date;

    public Post() {
    }

    public Post(int id, User author, String content, Timestamp date) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
