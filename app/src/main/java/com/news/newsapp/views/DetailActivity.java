package com.news.newsapp.views;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.news.newsapp.R;
import com.news.newsapp.repository.db.entities.News;

public class DetailActivity extends AppCompatActivity {
    private TextView lblDetailTitle, lblDetailContent;
    private News news;

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
    }

    private void loadData(){
        news = (News) getIntent().getSerializableExtra("nesne");
        lblDetailTitle.setText(news.getTitle());
        lblDetailContent.setText(news.getContent());
    }
}