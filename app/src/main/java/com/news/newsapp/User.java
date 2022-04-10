package com.news.newsapp;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Users")
public class User {
    @PrimaryKey
    public int id;

    public String username;

    public String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
