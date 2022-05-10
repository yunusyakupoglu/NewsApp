package com.news.newsapp.views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.news.newsapp.R;
import com.news.newsapp.adapter.newsAdapter;
import com.news.newsapp.repository.db.NewsDatabase;
import com.news.newsapp.repository.db.dao.NewsDao;
import com.news.newsapp.repository.db.entities.News;

import java.util.List;

import co.dift.ui.SwipeToAction;

public class NewsListActivity extends AppCompatActivity {

    private RecyclerView rv;
    private SwipeToAction swipeToAction;
    private NewsDao newsDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        initComponents();
        loadData();
    }

    private void initComponents(){
        rv = findViewById(R.id.recyclerView_news);
        NewsDatabase newsDatabase = NewsDatabase.getDatabase(NewsListActivity.this);
        newsDao = newsDatabase.newsDao();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Snackbar.make(rv, "Silmek için kaydırın", Snackbar.LENGTH_LONG).show();
    }

    private void loadData(){
        List<News> newsList = newsDao.getAllNews();
        newsAdapter adapter = new newsAdapter(newsList);
        rv.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);


        rv.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
       swipeToAction = new SwipeToAction(rv, new SwipeToAction.SwipeListener<News>() {

            public boolean swipeLeft(News news) {
                deleteNews(news);
                return true;
            }

            public boolean swipeRight(News news) {
                deleteNews(news);
                return false;
            }

            public void onClick(News news) {

            }

            public void onLongClick(News news) {

            }
        });

    }

    private void deleteNews(final News news){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Emin misiniz?");
        builder.setMessage(news.Title + "başlıklı haber silinecektir.");
        builder.setIcon(R.drawable.ic_baseline_warning_24);

        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == DialogInterface.BUTTON_POSITIVE){
                    newsDao.delete(news);
                    reloadData();
                    Snackbar snackbar = Snackbar.make(rv, news.getTitle() + " silindi", Snackbar.LENGTH_LONG);
                    snackbar.setAction("Geri Al", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            newsDao.insert(news);
                            reloadData();
                        }
                    });
                    snackbar.show();
                } else {
                    //hiçbir şey yapma
                }
            }
        };
        builder.setPositiveButton("Evet", listener);
        builder.setNegativeButton("Hayır",listener);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void reloadData(){
        List<News> newsList = newsDao.getAllNews();
        newsAdapter adapter = new newsAdapter(newsList);
        rv.setAdapter(adapter);
    }
}