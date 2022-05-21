package com.news.newsapp.repository.repos;

import android.app.Application;
import android.os.AsyncTask;

import com.news.newsapp.repository.db.NewsDatabase;
import com.news.newsapp.repository.db.dao.NewsDao;
import com.news.newsapp.repository.db.entities.News;

import java.util.List;

public class NewsRepo {
    private NewsDao newsDao;
    private List<News> allNews;

    public NewsRepo(Application application) {
        NewsDatabase newsDatabase = NewsDatabase.getDatabase(application);
        newsDao = newsDatabase.newsDao();
        allNews = newsDao.getAllNews();
    }

    public void insert(News news){
        new InsertNewsAsyncTask(newsDao).execute(news);
    }

    public void update(News news){
        new UpdateNewsAsyncTask(newsDao).execute(news);
    }

    public void delete(News news){
        new DeleteNewsAsyncTask(newsDao).execute(news);
    }

    public List<News> getAllNews(){
        return allNews;
    }

    private static class InsertNewsAsyncTask extends AsyncTask<News, Void, Void>{
        private NewsDao newsDaoAsync;

        public InsertNewsAsyncTask(NewsDao newsDaoAsync) {
            this.newsDaoAsync = newsDaoAsync;
        }

        @Override
        protected Void doInBackground(News... news) {
            newsDaoAsync.update(news[0]);
            return null;
        }
    }

    private static class UpdateNewsAsyncTask extends AsyncTask<News, Void, Void>{
        private NewsDao newsDaoAsync;

        public UpdateNewsAsyncTask(NewsDao newsDaoAsync) {
            this.newsDaoAsync = newsDaoAsync;
        }

        @Override
        protected Void doInBackground(News... news) {
            newsDaoAsync.update(news[0]);
            return null;
        }
    }

    private static class DeleteNewsAsyncTask extends AsyncTask<News, Void, Void>{
        private NewsDao newsDaoAsync;

        public DeleteNewsAsyncTask(NewsDao newsDaoAsync) {
            this.newsDaoAsync = newsDaoAsync;
        }

        @Override
        protected Void doInBackground(News... news) {
            newsDaoAsync.delete(news[0]);
            return null;
        }
    }
}
