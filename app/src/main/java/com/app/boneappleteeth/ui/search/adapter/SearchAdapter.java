package com.app.boneappleteeth.ui.search.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.boneappleteeth.databinding.ItemResepBinding;
import com.app.boneappleteeth.ui.foodpage.FoodActivity;
import com.app.boneappleteeth.ui.search.model.Search;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    private List<Search> searchList = new ArrayList<>();
    private Context context;

    public SearchAdapter(Context context, List<Search> newsList) {
        this.context = context;
        this.searchList = newsList;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemResepBinding binding = ItemResepBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new SearchViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        Search search = searchList.get(position);
        holder.binding.ivResep.setImageResource(search.getImagePath());

        if((position+1) % 2 != 0) {
            holder.binding.clLikeLeft.setVisibility(View.VISIBLE);
            holder.binding.clLikeRight.setVisibility(View.GONE);
            holder.binding.tvTitleLeft.setText(search.getTitle());
            holder.binding.tvTitleLeft.setText(search.getMenit());
        } else {
            holder.binding.clLikeRight.setVisibility(View.VISIBLE);
            holder.binding.clLikeLeft.setVisibility(View.GONE);
            holder.binding.tvTitle.setText(search.getTitle());
            holder.binding.tvMenit.setText(search.getMenit());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, FoodActivity.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }

    static  class SearchViewHolder extends RecyclerView.ViewHolder {
        ItemResepBinding binding;

        SearchViewHolder(ItemResepBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
