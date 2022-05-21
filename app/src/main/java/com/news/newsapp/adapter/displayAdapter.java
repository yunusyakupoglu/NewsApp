package com.news.newsapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.news.newsapp.R;
import com.news.newsapp.repository.db.entities.News;
import com.news.newsapp.views.DetailActivity;
import com.news.newsapp.views.MainActivity;

import java.io.Serializable;
import java.util.List;

public class displayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<News> newsList;
    private Context context;
    private MainActivity main;


    public displayAdapter(List<News> newsList, Context context) {
        this.newsList = newsList;
        this.context = context;
    }


    public class CardViewDesignHolder extends RecyclerView.ViewHolder{
        private TextView lblTitle;
        private CardView rowCardViewMain;
        public CardViewDesignHolder(@NonNull View itemView) {
            super(itemView);
            lblTitle = itemView.findViewById(R.id.lblTitle);
            rowCardViewMain = itemView.findViewById(R.id.rowCardViewMain);
        }

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_card_design,parent,false);
        return new CardViewDesignHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final News newsItem = newsList.get(position);
        displayAdapter.CardViewDesignHolder vh = (displayAdapter.CardViewDesignHolder) holder;
        vh.lblTitle.setText(newsItem.getTitle());
        vh.rowCardViewMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("nesne", (Serializable) newsItem);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
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
