package com.news.newsapp.repository.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.news.newsapp.repository.converters.DateConverter;
import com.news.newsapp.repository.db.dao.FilesDao;
import com.news.newsapp.repository.db.dao.NewsDao;
import com.news.newsapp.repository.db.entities.Files;
import com.news.newsapp.repository.db.entities.News;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {News.class, Files.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class NewsDatabase extends RoomDatabase {
    private static NewsDatabase newsDatabase;
    public abstract NewsDao newsDao();
    public abstract FilesDao filesDao();

    private static final String databaseName = "newsDatabase.db";

    private static final Migration MIGRATION = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Veri islemleri
        }
    };


    public static NewsDatabase getDatabase(Context context){
        if (newsDatabase == null){
            newsDatabase =
                    Room.databaseBuilder(context.getApplicationContext(),
                            NewsDatabase.class,databaseName)
                            .addMigrations(MIGRATION)
                            .allowMainThreadQueries()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
        }
        return newsDatabase;
    }

    public static void destroyInstance(){
        newsDatabase = null;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            db.disableWriteAheadLogging();
        }
    };
}
