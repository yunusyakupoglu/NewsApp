package com.news.newsapp;


import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface UserDao {
    @Insert
    public void userAdd(User user);
}
