package com.app.boneappleteeth.ui.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.boneappleteeth.databinding.ItemNewsBinding;
import com.app.boneappleteeth.ui.home.model.News;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {
    private List<News> newsList = new ArrayList<>();
    private Context context;

    public HomeAdapter(Context context, List<News> newsList) {
        this.context = context;
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemNewsBinding binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new HomeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        News news = newsList.get(position);
        holder.binding.tvNews.setText(news.getText());
        holder.binding.ivNews.setImageResource(news.getImagePath());
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    static  class HomeViewHolder extends RecyclerView.ViewHolder {
        ItemNewsBinding binding;

        HomeViewHolder(ItemNewsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
