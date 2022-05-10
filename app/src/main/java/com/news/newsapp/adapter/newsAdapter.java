package com.news.newsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.news.newsapp.R;
import com.news.newsapp.repository.db.entities.News;

import java.util.List;

import co.dift.ui.SwipeToAction;

public class newsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private List<News> newsList;

    public newsAdapter(Context context) {
        this.context = context;
    }

    public newsAdapter(List<News> newsList) {
        this.newsList = newsList;
    }

    public class CardViewDesignHolder extends SwipeToAction.ViewHolder<News>{
        private final TextView textViewTitle, textViewStatus;
        //private final CardView rowCardView;

        public CardViewDesignHolder(@NonNull View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewStatus = itemView.findViewById(R.id.textViewStatus);
            //rowCardView = itemView.findViewById(R.id.rowCardView);
        }
    }

    @NonNull
    @Override
    public CardViewDesignHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_design,parent,false);
        return new CardViewDesignHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        News news = newsList.get(position);
        CardViewDesignHolder vh = (CardViewDesignHolder) holder;
        vh.textViewTitle.setText(newsList.get(position).getTitle());
        vh.textViewStatus.setText(newsList.get(position).getStatusType());
        vh.data = news;

      /*  vh.rowCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Tıklandı : " + news.Title, Toast.LENGTH_SHORT).show();
            }
        });*/
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
