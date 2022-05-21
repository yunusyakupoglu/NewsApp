package com.news.newsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.news.newsapp.R;
import com.news.newsapp.repository.db.entities.News;

import java.util.List;

public class spinnerAdapter extends ArrayAdapter<News> {
    LayoutInflater layoutInflater;

    public spinnerAdapter(@NonNull Context context, int resource, @NonNull List<News> news) {
        super(context, resource, news);
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View rowView = layoutInflater.inflate(R.layout.custom_spinner_adapter,null,true);
        News news = getItem(position);
        TextView textView = (TextView) rowView.findViewById(R.id.textViewNewsTitle);
        textView.setText(news.getTitle());
        return rowView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null)
            convertView = layoutInflater.inflate(R.layout.custom_spinner_adapter,parent,false);
        News news = getItem(position);
        TextView textView = (TextView) convertView.findViewById(R.id.textViewNewsTitle);
        textView.setText(news.getTitle());
        return convertView;
    }
}
