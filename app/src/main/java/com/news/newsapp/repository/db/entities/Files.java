package com.news.newsapp.repository.db.entities;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.news.newsapp.enums.StatusEnum;

@Entity(foreignKeys = {
        @ForeignKey(entity = News.class,
                parentColumns = "id",
                childColumns = "news_id",
                onDelete = CASCADE,
                onUpdate = CASCADE)
})
public class Files {
    @PrimaryKey(autoGenerate = true)
    public long id;
    @ColumnInfo(name = "news_id")
    public long newsId;
    public String fileUri;
    public int StatusType;
    @Ignore
    private News news;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getNewsId() {
        return newsId;
    }
    public void setNewsId(long newsId) {
        this.newsId = newsId;
    }
    public String getFileUri() {
        return fileUri;
    }
    public void setFileUri(String fileUri) {
        this.fileUri = fileUri;
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
    public News getNews() {
        return news;
    }
    public void setNews(News news) {
        this.news = news;
    }
}
