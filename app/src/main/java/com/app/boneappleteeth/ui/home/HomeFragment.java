package com.app.boneappleteeth.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.app.boneappleteeth.R;
import com.app.boneappleteeth.databinding.FragmentHomeBinding;
import com.app.boneappleteeth.ui.home.adapter.HomeAdapter;
import com.app.boneappleteeth.ui.home.model.News;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private List<News> newsList = new ArrayList<>();
    private HomeAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        initAdapter();
        loadData();
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void initAdapter() {
        binding.rvNews.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new HomeAdapter(getContext(), newsList);
        binding.rvNews.setAdapter(adapter);
    }

    private void loadData() {
        News news = new News();
        news.setId(0);
        news.setText("Pertajam kemampuan otak dengan konsumsi 5 makanan ini setiap hari");
        news.setImagePath(R.drawable.image_buah);
        newsList.add(news);

        news = new News();
        news.setId(0);
        news.setText("Mengantuk sangat berbahaya ketika tidur");
        news.setImagePath(R.drawable.image_ngantuk);
        newsList.add(news);

        news = new News();
        news.setId(0);
        news.setText("Makan sayur sayuran bergizi");
        news.setImagePath(R.drawable.image_sayur);
        newsList.add(news);

        news = new News();
        news.setId(0);
        news.setText("Ayo olahraga agar tubuh sehat kuat");
        news.setImagePath(R.drawable.image_sehat);
        newsList.add(news);

        adapter.notifyDataSetChanged();
    }
}