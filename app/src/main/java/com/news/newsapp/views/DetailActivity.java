package com.news.newsapp.views;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.news.newsapp.R;
import com.news.newsapp.repository.db.NewsDatabase;
import com.news.newsapp.repository.db.dao.FilesDao;
import com.news.newsapp.repository.db.entities.News;

public class DetailActivity extends AppCompatActivity {
    private TextView lblDetailTitle, lblDetailContent;
    private ImageView imageViewThumbnail;
    private News news;
    private FilesDao filesDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initComponents();
        loadData();
    }

    private void initComponents(){
        lblDetailTitle = findViewById(R.id.lblDetailTitle);
        lblDetailContent = findViewById(R.id.lblDetailContent);
        imageViewThumbnail = findViewById(R.id.imageViewThumbnail);
        NewsDatabase newsDatabase = NewsDatabase.getDatabase(DetailActivity.this);
        filesDao = newsDatabase.filesDao();
    }

    private void loadData(){
        news = (News) getIntent().getSerializableExtra("nesne");
       imageViewThumbnail.setImageURI(Uri.parse(filesDao.loadFilesByNews(news.id).get(0).fileUri));
        lblDetailTitle.setText(news.getTitle());
        lblDetailContent.setText(news.getContent());
    }
}