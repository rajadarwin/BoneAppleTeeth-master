package com.app.boneappleteeth.ui.favorite;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.app.boneappleteeth.AccountModel;
import com.app.boneappleteeth.R;
import com.app.boneappleteeth.ResepModel;
import com.app.boneappleteeth.ResepRepository;
import com.app.boneappleteeth.ResepServices;
import com.app.boneappleteeth.databinding.FragmentFavoriteBinding;
import com.app.boneappleteeth.ui.favorite.adapter.FavoriteAdapter;
import com.app.boneappleteeth.ui.favorite.model.Favorite;
import com.app.boneappleteeth.ui.search.adapter.SearchAdapter;
import com.app.boneappleteeth.ui.search.model.Search;
import com.app.boneappleteeth.ui.tambahresep.TambahResepActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoriteFragment extends Fragment {

    private FavoriteViewModel notificationsViewModel;
    private FragmentFavoriteBinding binding;
    private FavoriteAdapter adapter;
    private List<Favorite> favoriteList = new ArrayList<>();
    private Button button;

    private SharedPreferences sharedPreferences;
    final String PREF_NAME = "ACCOUNT";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(FavoriteViewModel.class);

        binding = FragmentFavoriteBinding.inflate(inflater, container, false);

        initAdapter();
        loadData();
        binding.tambahResep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTambahResep();
            }
        });
        return binding.getRoot();
    }

    public void openTambahResep() {
        Intent intent = new Intent(getActivity(), TambahResepActivity.class);
        startActivity(intent);
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
        sharedPreferences = this.getActivity().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("account", "");
        AccountModel account = gson.fromJson(json, AccountModel.class);

        ResepServices service = ResepRepository.create();
        service.getUserResep(account.getUsername()).enqueue(new Callback<List<ResepModel>>() {
            @Override
            public void onResponse(Call<List<ResepModel>> call, Response<List<ResepModel>> response) {
                for(ResepModel resep : response.body()){
                    Log.d("RESEP" , resep.toString());
                    Favorite favorite = new Favorite();
                    favorite.setId(resep.getId_menu());
                    favorite.setMenit(String.valueOf(resep.getWaktu()));
                    favorite.setImagePath(R.drawable.image_buah);
                    favorite.setTitle(resep.getNama());
                    Log.d("FAVORITE", favorite.toString());
                    favoriteList.add(favorite);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<ResepModel>> call, Throwable t) {

            }
        });

        /*Favorite favorite = new Favorite();
        favorite.setId(0);
        favorite.setTitle("Tes test");
        favorite.setMenit("10 Menit");
        favorite.setImagePath(R.drawable.image_buah);
        favoriteList.add(favorite);

        favorite = new Favorite();
        favorite.setId(0);
        favorite.setTitle("Tes test");
        favorite.setMenit("10 Menit");
        favorite.setImagePath(R.drawable.image_buah);
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
        favorite.setImagePath(R.drawable.image_buah);
        favoriteList.add(favorite);*/

        adapter.notifyDataSetChanged();
    }
}