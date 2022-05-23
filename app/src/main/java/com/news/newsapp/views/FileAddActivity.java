package com.news.newsapp.views;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.splashscreen.SplashScreen;

import com.news.newsapp.R;
import com.news.newsapp.adapter.spinnerAdapter;
import com.news.newsapp.repository.db.NewsDatabase;
import com.news.newsapp.repository.db.dao.FilesDao;
import com.news.newsapp.repository.db.dao.NewsDao;
import com.news.newsapp.repository.db.entities.Files;
import com.news.newsapp.repository.db.entities.News;

import java.io.File;
import java.util.List;

public class FileAddActivity extends AppCompatActivity {
    private ImageView imageViewFile;
    private TextView txtFileName;
    private Button btnSelect, btnFileSave;
    private Spinner spinnerNews;
    private RadioGroup radioGroupFileStatus;
    private Toolbar toolbar;
    private NewsDatabase newsDatabase;
    private NewsDao newsDao;
    private FilesDao filesDao;
    private static final int IMAGE_PICK_MODE = 1000;
    private static final int PERMISSION_CODE = 1001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_add);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        initComponents();
        setToolbar();
        registerEventHandlers();
        loadData();
    }
    private void setToolbar(){
        toolbar.setTitle("Haberler");
        toolbar.setSubtitle("Dosya Ekle");
        setSupportActionBar(toolbar);
    }
    private void initComponents(){
        imageViewFile = findViewById(R.id.imageViewFile);
        btnSelect = findViewById(R.id.btnSelect);
        btnFileSave = findViewById(R.id.btnFileSave);
        txtFileName = findViewById(R.id.textViewFileName);
        radioGroupFileStatus = findViewById(R.id.radioGroupStatus);
        spinnerNews = (Spinner) findViewById(R.id.spinnerNews);
        toolbar = findViewById(R.id.toolbar_file_add);
        newsDatabase = NewsDatabase.getDatabase(FileAddActivity.this);
        newsDao = newsDatabase.newsDao();
        filesDao = newsDatabase.filesDao();
    }
    private void loadData(){
        List<News> newsList = newsDao.getAllNews();
        SpinnerAdapter spinnerAdapter = new spinnerAdapter(this,R.layout.custom_spinner_adapter,newsList);
        spinnerNews.setAdapter(spinnerAdapter);
    }
    private void  registerEventHandlers(){
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // check permission
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                        //permission not granted, request it.
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        //show popup for runtime permission
                        requestPermissions(permissions,PERMISSION_CODE);
                    }
                    else{
                        //permission already granted
                        pickImageFromGallery();
                    }
                }
                else{
                    // system os is less then marsmallow
                    pickImageFromGallery();
                }
            }
        });
        btnFileSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filePath = txtFileName.getText().toString();
                News selectedStatus = (News) spinnerNews.getSelectedItem();
                long newsId = selectedStatus.getId();
                Files files = new Files();
                files.setFileUri(filePath);
                files.setNewsId(newsId);
                int status = 1; //varsayılan Active
                if (radioGroupFileStatus.getCheckedRadioButtonId() == R.id.rbPassive)
                    status= 2;
                files.setStatusType(status);
                filesDao.insert(files);
                Toast.makeText(FileAddActivity.this, "Resim başarıyla kaydedildi.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void pickImageFromGallery() {
        //intent to pick image
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_MODE);
    }
    //handle result of runtime permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    //permission was granted
                    pickImageFromGallery();
                } else {
                    //permission was denied
                    Toast.makeText(this, "Permission denied...!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    //handle result of picked image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_MODE) {
            //imageViewFile.setImageURI(data.getData());
            Uri selectedImageUri = data.getData();
            // Get the path from the Uri
            final String path = getPathFromURI(selectedImageUri);
            if (path != null) {
                File f = new File(path);
                selectedImageUri = Uri.fromFile(f);
            }
            imageViewFile.setImageURI(selectedImageUri);
            txtFileName.setText(selectedImageUri.getPath());
        }
    }
    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }
}