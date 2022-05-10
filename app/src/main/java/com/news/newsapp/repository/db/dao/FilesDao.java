package com.news.newsapp.repository.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.news.newsapp.repository.db.entities.Files;

import java.util.List;

@Dao
public interface FilesDao {
    @Insert
    void insert(Files files);

    @Update
    void update(Files files);

    @Delete
    void delete(Files files);

    @Query("SELECT * FROM Files")
    List<Files> loadAllFiles();

    @Query("SELECT * FROM Files WHERE id=:id")
    Files loadFilesById(long id);
}
