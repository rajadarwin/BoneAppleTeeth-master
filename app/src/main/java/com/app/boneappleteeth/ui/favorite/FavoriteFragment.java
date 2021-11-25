package com.app.boneappleteeth.ui.favorite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.app.boneappleteeth.R;
import com.app.boneappleteeth.databinding.FragmentFavoriteBinding;
import com.app.boneappleteeth.ui.favorite.adapter.FavoriteAdapter;
import com.app.boneappleteeth.ui.favorite.model.Favorite;
import com.app.boneappleteeth.ui.search.adapter.SearchAdapter;
import com.app.boneappleteeth.ui.search.model.Search;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment {

    private FavoriteViewModel notificationsViewModel;
    private FragmentFavoriteBinding binding;
    private FavoriteAdapter adapter;
    private List<Favorite> favoriteList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(FavoriteViewModel.class);

        binding = FragmentFavoriteBinding.inflate(inflater, container, false);

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
        binding.rvPencarian.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new FavoriteAdapter(getContext(), favoriteList);
        binding.rvPencarian.setAdapter(adapter);
    }

    private void loadData() {
        Favorite favorite = new Favorite();
        favorite.setId(0);
        favorite.setTitle("Tes test");
        favorite.setMenit("10 Menit");
        favorite.setImagePath(R.drawable.image_buah);
        favoriteList.add(favorite);

        favorite = new Favorite();
        favorite.setId(0);
        favorite.setTitle("Tes test");
        favorite.setMenit("10 Menit");
        favorite.setImagePath(R.drawable.image_ngantuk);
        favoriteList.add(favorite);

        favorite = new Favorite();
        favorite.setId(0);
        favorite.setTitle("Tes test");
        favorite.setMenit("10 Menit");
        favorite.setImagePath(R.drawable.image_sayur);
        favoriteList.add(favorite);

        favorite = new Favorite();
        favorite.setId(0);
        favorite.setTitle("Tes test");
        favorite.setMenit("10 Menit");
        favorite.setImagePath(R.drawable.image_sehat);
        favoriteList.add(favorite);

        adapter.notifyDataSetChanged();
    }
}