package com.news.newsapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.news.newsapp.R;
import com.news.newsapp.repository.db.entities.News;

import java.util.ArrayList;
import java.util.List;

import co.dift.ui.SwipeToAction;

public class newsRecyclerViewAdapter extends RecyclerView.Adapter<newsRecyclerViewAdapter.NewsViewHolder> {
    private List<News> newsList;

    public newsRecyclerViewAdapter(List<News> newsList) {
        this.newsList = newsList;
    }

    class NewsViewHolder extends SwipeToAction.ViewHolder {
        private TextView lblTitle, lblStatus;

        NewsViewHolder(View layout){
            super(layout);
            lblTitle = layout.findViewById(R.id.lblTitle);
            lblStatus = layout.findViewById(R.id.lblStatus);
        }
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_news,parent,false);
        return new NewsViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        News news = newsList.get(position);
        holder.lblTitle.setText(news.getTitle());
        holder.lblStatus.setText(news.getStatusType());
        holder.data = news;
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    @Override
    public long getItemId(int position) {
        News news = newsList.get(position);
        return news.getId();
    }
}
