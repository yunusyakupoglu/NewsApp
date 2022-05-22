package com.news.newsapp.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.news.newsapp.R;

public class NavigationActivity extends AppCompatActivity {
    private Button btnUser, btnNews, btnFiles, btnSettings, btnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        initComponents();
        listeners();
    }

    private void initComponents(){
        btnUser = findViewById(R.id.btnUsers);
        btnNews = findViewById(R.id.btnNews);
        btnFiles = findViewById(R.id.btnFiles);
        btnSettings = findViewById(R.id.btnSettings);
        btnLogout = findViewById(R.id.btnLogout);
    }

    private void listeners(){
        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent toUserList = new Intent(NavigationActivity.this,);
                Toast.makeText(NavigationActivity.this, "Kullanıcılar tıklandı.", Toast.LENGTH_SHORT).show();
            }
        });

        btnNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toNewsList = new Intent(NavigationActivity.this,NewsListActivity.class);
                startActivity(toNewsList);
            }
        });

        btnFiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toFileList = new Intent(NavigationActivity.this,FileListActivity.class);
                startActivity(toFileList);
            }
        });

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent toSettings = new Intent();
                Toast.makeText(NavigationActivity.this, "Ayarlar tıklandı.", Toast.LENGTH_SHORT).show();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NavigationActivity.this, "Çıkış yap tıklandı.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}