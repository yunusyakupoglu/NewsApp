package com.news.newsapp.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;
import com.news.newsapp.R;
import com.news.newsapp.repository.db.NewsDatabase;
import com.news.newsapp.repository.db.dao.NewsDao;
import com.news.newsapp.repository.db.entities.News;

public class NewsAddActivity extends AppCompatActivity {
    private TextInputEditText txtTitle, txtContent;
    private RadioGroup radioGroupStatus;
    private Button btnSave;
    private Toolbar toolbar;
    private NewsDatabase newsDatabase;
    private NewsDao newsDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_add);
        toolbar = findViewById(R.id.toolbar_news_add);
        initComponents();
        setToolbar();
        registerEventHandlers();
    }
    private void setToolbar(){
        toolbar.setTitle("Haberler");
        toolbar.setSubtitle("Haber Ekle");
        setSupportActionBar(toolbar);
    }
    private void initComponents(){
        txtTitle = findViewById(R.id.txtTitle);
        txtContent = findViewById(R.id.txtContent);
        radioGroupStatus = findViewById(R.id.radioGroupStatus);
        btnSave = findViewById(R.id.btnSave);

        newsDatabase = NewsDatabase.getDatabase(NewsAddActivity.this);
        newsDao = newsDatabase.newsDao();
    }
    private void registerEventHandlers(){
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = txtTitle.getText().toString();
                String content = txtContent.getText().toString();
                News news = new News();
                news.setTitle(title);
                news.setContent(content);
                int status = 1; //varsayılan Active
                if (radioGroupStatus.getCheckedRadioButtonId() == R.id.rbPassive)
                    status= 2;
                news.setStatusType(status);
                newsDao.insert(news);
                Toast.makeText(NewsAddActivity.this, "Haber başarıyla kaydedildi.", Toast.LENGTH_SHORT).show();
                NewsAddActivity.this.finish();
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = this.getMenuInflater();
        menuInflater.inflate(R.menu.news_main_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.recyclerView_news:
                Intent intent = new Intent(this,MainActivity.class);
                this.startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}