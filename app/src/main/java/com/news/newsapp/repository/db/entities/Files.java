package com.news.newsapp.repository.db.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = News.class,parentColumns = "id",childColumns = "fk_newsId", onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)
})
public class Files {
    @PrimaryKey(autoGenerate = true)
    public long id;

    public long fk_newsId;
    public String fileUri;
    public int StatusType;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFk_newsId() {
        return fk_newsId;
    }

    public void setFk_newsId(long fk_newsId) {
        this.fk_newsId = fk_newsId;
    }

    public String getFileUri() {
        return fileUri;
    }

    public void setFileUri(String fileUri) {
        this.fileUri = fileUri;
    }

    public int getStatusType() {
        return StatusType;
    }

    public void setStatusType(int statusType) {
        StatusType = statusType;
    }
}
