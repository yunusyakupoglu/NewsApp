package com.news.newsapp.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.news.newsapp.R;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
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

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_search:
                Toast.makeText(getApplicationContext(), "Arama tıklandı", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_news_add:
                Intent intent_news_add = new Intent(MainActivity.this,NewsAddActivity.class);
                startActivity(intent_news_add);
            case R.id.action_news_list:
                Intent intent = new Intent(MainActivity.this,NewsListActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_logout:
                Toast.makeText(getApplicationContext(), "Çıkış yap tıklandı", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}