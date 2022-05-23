package com.news.newsapp.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.news.newsapp.R;

public class NavigationActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button btnNews, btnFiles, btnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        mAuth = FirebaseAuth.getInstance();
        initComponents();
        listeners();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser==null){
            startActivity(new Intent(NavigationActivity.this,LoginActivity.class));
        }
    }

    private void initComponents(){
        btnNews = findViewById(R.id.btnNews);
        btnFiles = findViewById(R.id.btnFiles);
        btnLogout = findViewById(R.id.btnLogout);
    }

    private void listeners(){
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


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(NavigationActivity.this, LoginActivity.class));
            }
        });
    }
}