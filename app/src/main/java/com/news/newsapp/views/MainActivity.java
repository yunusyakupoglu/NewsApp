package com.news.newsapp.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.news.newsapp.R;
import com.news.newsapp.adapter.displayAdapter;
import com.news.newsapp.repository.db.NewsDatabase;
import com.news.newsapp.repository.db.dao.FilesDao;
import com.news.newsapp.repository.db.dao.NewsDao;
import com.news.newsapp.repository.db.entities.News;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView rv;
    private NewsDao newsDao;
    private FilesDao filesDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar();
    }

    private void setToolbar(){
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Haberler");
        toolbar.setSubtitle("Tüm Haberler");
        setSupportActionBar(toolbar);
        initComponents();
        loadData();
    }

    private void initComponents(){
        rv = findViewById(R.id.recyclerView_main);
        NewsDatabase newsDatabase = NewsDatabase.getDatabase(MainActivity.this);
        newsDao = newsDatabase.newsDao();
        filesDao = newsDatabase.filesDao();
    }

    private void loadData(){
        List<News> newsList = newsDao.getActiveNews();
        for(News news : newsList){
            news.setFilesList(filesDao.loadFilesByNews(news.id));
        }
        displayAdapter adapter = new displayAdapter(newsList,getApplicationContext());
        rv.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);


        rv.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_search){

        } else if (item.getItemId() == R.id.action_login){
            Intent toLogin = new Intent(MainActivity.this,NavigationActivity.class);
            startActivity(toLogin);
        }
        return super.onOptionsItemSelected(item);
    }
}