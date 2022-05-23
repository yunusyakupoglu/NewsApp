package com.news.newsapp.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.news.newsapp.R;
import com.news.newsapp.repository.db.entities.Files;

import java.util.List;

import co.dift.ui.SwipeToAction;

public class fileAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Files> filesList;
    public fileAdapter(Context context) {
        this.context = context;
    }
    public fileAdapter(List<Files> filesList) {
        this.filesList = filesList;
    }
    public class CardViewDesignHolder extends SwipeToAction.ViewHolder<Files>{
        private TextView textViewTitle, textViewStatus;
        private ImageView imageViewCardFile;
        public CardViewDesignHolder(View v) {
            super(v);
            imageViewCardFile = v.findViewById(R.id.imageViewCardFile);
            textViewTitle = v.findViewById(R.id.textViewFileCardTitle);
            textViewStatus = v.findViewById(R.id.textViewFileCardStatus);
        }
    }
    @NonNull
    @Override
    public CardViewDesignHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.file_card_design,parent,false);
        return new fileAdapter.CardViewDesignHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Files files = filesList.get(position);
        CardViewDesignHolder vh = (CardViewDesignHolder) holder;
        if(files !=null)
        {
            vh.imageViewCardFile.setImageURI(Uri.parse(filesList.get(position).getFileUri()));
            vh.textViewTitle.setText(filesList.get(position).getNews().getTitle());
            vh.textViewStatus.setText(filesList.get(position).getNews().getStatusType());
            vh.data=files;
        }
    }
    @Override
    public int getItemCount() {
        return filesList.size();
    }
    @Override
    public long getItemId(int position) {
        Files files = filesList.get(position);
        return files.getId();
    }
}
