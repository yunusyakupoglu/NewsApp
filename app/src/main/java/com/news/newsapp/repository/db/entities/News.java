package com.news.newsapp.repository.db.entities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.news.newsapp.enums.StatusEnum;

import java.io.Serializable;
import java.util.List;

@Entity
public class News implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public long id;

    public String Title;
    public String Content;
    public int StatusType;
    @Ignore

    public static transient List<Files> filesList;

    public List<Files> getFilesList() {
        return filesList;
    }


    public void setFilesList(List<Files> filesList) {
        this.filesList = filesList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getStatusType() {
        if(this.StatusType == StatusEnum.ACTIVE.getValue())
        {
            return "ACTIVE";
        }
        else
        {
            return "PASSIVE";
        }
    }

    public void setStatusType(int statusType) {
        StatusType = statusType;
    }
}
