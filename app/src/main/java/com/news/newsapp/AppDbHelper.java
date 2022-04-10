package com.news.newsapp;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class},version = 1)
public abstract class AppDbHelper extends RoomDatabase
{
    public abstract UserDao userDao();
}
