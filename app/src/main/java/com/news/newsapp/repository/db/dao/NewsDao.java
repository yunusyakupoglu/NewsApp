package com.news.newsapp.repository.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.news.newsapp.repository.db.entities.News;

import java.util.List;

@Dao
public interface NewsDao {
    @Insert
    public void insert(News news);
    @Update
    public void update(News news);
    @Delete
    void delete(News news);
    @Query("SELECT * FROM News")
    public List<News> getAllNews();
    @Query("SELECT * FROM News WHERE StatusType==1") //StatusType enumdan gelir.
    public List<News> getActiveNews();
    @Query("SELECT * FROM News WHERE StatusType==2")
    public List<News> getPassiveNews();
    @Query("SELECT * FROM News WHERE id=:id")
    public News getNewsById(long id);
}
