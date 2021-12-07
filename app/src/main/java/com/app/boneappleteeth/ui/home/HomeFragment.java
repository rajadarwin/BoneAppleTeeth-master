package com.app.boneappleteeth.ui.home;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
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

import com.app.boneappleteeth.AccountModel;
import com.app.boneappleteeth.FullAccountModel;
import com.app.boneappleteeth.NewsModel;
import com.app.boneappleteeth.NewsRepository;
import com.app.boneappleteeth.NewsServices;
import com.app.boneappleteeth.R;
import com.app.boneappleteeth.databinding.FragmentHomeBinding;
import com.app.boneappleteeth.ui.home.adapter.HomeAdapter;
import com.app.boneappleteeth.ui.home.model.News;
import com.app.boneappleteeth.ui.register.RegisterActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private List<News> newsList = new ArrayList<>();
    private HomeAdapter adapter;
    private AccountModel account;

    private SharedPreferences sharedPreferences;
    private String sharedPrefFile;
    private Context context;
    final String PREF_NAME = "ACCOUNT";

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        sharedPreferences = this.getActivity().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        Boolean loggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
        if (!loggedIn) {
            startActivity(new Intent(getContext(), RegisterActivity.class));
            getActivity().finish();
        }
        Gson gson = new Gson();
        String json = sharedPreferences.getString("account", "");
        FullAccountModel account = gson.fromJson(json, FullAccountModel.class);
        Log.d("NAME", account.getUsername());
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        binding.tvName.setText(account.getUsername());
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
        newsList.clear();
        NewsServices service = NewsRepository.create();
        service.get().enqueue(new Callback<List<NewsModel>>() {
            @Override
            public void onResponse(Call<List<NewsModel>> call, Response<List<NewsModel>> response) {
                for(NewsModel n : response.body()){
                    News news = new News();
                    news.setId(0);
                    news.setNama(n.getTanggal());
                    news.setText(n.getJudul());
                    news.setIsi(n.getIsi());
                    news.setImagePath(R.drawable.image_sehat);
                    newsList.add(news);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<NewsModel>> call, Throwable t) {

            }
        });

        /*News news = new News();
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
        newsList.add(news);*/

        adapter.notifyDataSetChanged();
    }
}