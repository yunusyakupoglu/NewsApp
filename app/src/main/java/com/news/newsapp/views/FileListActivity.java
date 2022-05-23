package com.news.newsapp.views;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.news.newsapp.R;
import com.news.newsapp.adapter.fileAdapter;
import com.news.newsapp.repository.db.NewsDatabase;
import com.news.newsapp.repository.db.dao.FilesDao;
import com.news.newsapp.repository.db.dao.NewsDao;
import com.news.newsapp.repository.db.entities.Files;

import java.util.List;

import co.dift.ui.SwipeToAction;

public class FileListActivity extends AppCompatActivity {
    private RecyclerView rv;
    private SwipeToAction swipeToAction;
    private FilesDao filesDao;
    private NewsDao newsDao;
    private Toolbar toolbar;
    private FloatingActionButton fabAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_list);
        toolbar = findViewById(R.id.toolbar_file_list);
        initComponents();
        setToolbar();
        loadData();
        registerEventHandlers();
    }

    private void setToolbar(){
        toolbar.setTitle("Haberler");
        toolbar.setSubtitle("Dosyalar");
        setSupportActionBar(toolbar);
    }

    private void initComponents(){
        rv = findViewById(R.id.recyclerView_files);
        fabAdd = findViewById(R.id.fabFileAdd);
        NewsDatabase newsDatabase = NewsDatabase.getDatabase(FileListActivity.this);
        filesDao = newsDatabase.filesDao();
        newsDao = newsDatabase.newsDao();
    }

    private void registerEventHandlers(){
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toFilesAdd = new Intent(FileListActivity.this,FileAddActivity.class);
                startActivity(toFilesAdd);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Snackbar.make(rv, "Silmek için kaydırın", Snackbar.LENGTH_LONG).show();
    }

    private void loadData(){
        List<Files> filesList = filesDao.loadAllFiles();
        for(Files files : filesList){
            files.setNews(newsDao.getNewsById(files.newsId));
        }

        fileAdapter adapter = new fileAdapter(filesList);
        rv.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        rv.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        swipeToAction = new SwipeToAction(rv, new SwipeToAction.SwipeListener<Files>() {


            @Override
            public boolean swipeLeft(Files files) {
                deleteFile(files);
                return true;
            }

            @Override
            public boolean swipeRight(Files files) {
                deleteFile(files);
                return true;
            }

            @Override
            public void onClick(Files files) {

            }

            @Override
            public void onLongClick(Files files) {

            }
        });
    }

    private void deleteFile(final Files files){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Emin misiniz?");
        builder.setMessage(files.getNews().Title + " başlıklı habere ait "+files.fileUri +" isimli dosya veritabanından silinecektir.");
        builder.setIcon(R.drawable.ic_baseline_warning_24);

        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == DialogInterface.BUTTON_POSITIVE){
                    filesDao.delete(files);
                    reloadData();
                    Snackbar snackbar = Snackbar.make(rv,files.getNews().getTitle() + " başlıklı habere ait "
                            +files.getFileUri()
                            +" isimli dosya veritabanından silindi.",Snackbar.LENGTH_LONG);
                    snackbar.setAction("Geri Al", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            filesDao.insert(files);
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
        List<Files> filesList = filesDao.loadAllFiles();
        for(Files files : filesList){
            files.setNews(newsDao.getNewsById(files.newsId));
        }
        fileAdapter adapter = new fileAdapter(filesList);
        rv.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.news_main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_search:
                Toast.makeText(getApplicationContext(), "Arama tıklandı", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}