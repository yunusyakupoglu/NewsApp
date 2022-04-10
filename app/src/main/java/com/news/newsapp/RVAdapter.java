package com.news.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.CardViewDesignObjects>{
    private Context context;
    private List<String> routeList;

    public RVAdapter(Context context, List<String> routeList) {
        this.context = context;
        this.routeList = routeList;
    }

    public class CardViewDesignObjects extends RecyclerView.ViewHolder {
        public TextView textViewRow;
        public CardView cardViewRow;

        public CardViewDesignObjects(@NonNull View view) {
            super(view);
            textViewRow = view.findViewById(R.id.textViewRow);
            cardViewRow = view.findViewById(R.id.cardViewRow);
        }
    }

    @NonNull
    @Override
    public CardViewDesignObjects onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_design,parent,false);
        return new CardViewDesignObjects(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewDesignObjects holder, int position) {
        String route = routeList.get(position);
        holder.textViewRow.setText(route);
        holder.cardViewRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return routeList.size();
    }

}
