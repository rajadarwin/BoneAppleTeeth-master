package com.app.boneappleteeth.ui.search;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.app.boneappleteeth.BahanModel;
import com.app.boneappleteeth.R;
import com.app.boneappleteeth.ResepModel;
import com.app.boneappleteeth.ResepRepository;
import com.app.boneappleteeth.ResepServices;
import com.app.boneappleteeth.TextMultipleColor;
import com.app.boneappleteeth.databinding.FragmentSearchBinding;
import com.app.boneappleteeth.ui.search.adapter.SearchAdapter;
import com.app.boneappleteeth.ui.search.model.Search;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {

    private SearchViewModel dashboardViewModel;
    private FragmentSearchBinding binding;
    private SearchAdapter adapter;
    private List<Search> searchList = new ArrayList<>();
    private List<ResepModel> list_of_resep = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(SearchViewModel.class);

        binding = FragmentSearchBinding.inflate(inflater, container, false);

        String text1 = TextMultipleColor.getColoredSpanned("masak", "#4E9F3D" );
        String text2 = TextMultipleColor.getColoredSpanned("hari ini?", "#000000");

        resepApiCall();
        for(ResepModel resep : list_of_resep){
            Log.d("LOOP", "LOOP!!");
            Search search = new Search();
            search.setId(resep.getId_menu());
            search.setTitle(resep.getNama());
            search.setMenit(resep.getWaktu() + " Menit");
            search.setImagePath(R.drawable.image_sayur);
            search.setFavorite(false);
            searchList.add(search);
        }

        binding.subTitle.setText(Html.fromHtml(text1 + " " + text2));

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
        adapter = new SearchAdapter(getContext(), searchList);
        binding.rvPencarian.setAdapter(adapter);
    }

    private void loadData() {
        Log.d("List of search", String.valueOf(searchList));

        /*Search search = new Search();
        search.setId(0);
        search.setTitle("Lamb Chop");
        search.setMenit("10 Menit");
        search.setImagePath(R.drawable.image_buah);
        searchList.add(search);

        search = new Search();
        search.setId(0);
        search.setTitle("Beef Lasagna");
        search.setMenit("20 Menit");
        search.setImagePath(R.drawable.image_ngantuk);
        searchList.add(search);

        search = new Search();
        search.setId(0);
        search.setTitle("Nasi goreng");
        search.setMenit("10 Menit");
        search.setImagePath(R.drawable.image_sayur);
        searchList.add(search);

        search = new Search();
        search.setId(0);
        search.setTitle("Tes test");
        search.setMenit("10 Menit");
        search.setImagePath(R.drawable.image_sehat);
        searchList.add(search);*/
        adapter.notifyDataSetChanged();
    }

    void resepApiCall(){
        ResepServices resepServices = ResepRepository.create();
        resepServices.getResep().enqueue(new Callback<List<ResepModel>>() {
            @Override
            public void onResponse(Call<List<ResepModel>> call, Response<List<ResepModel>> response) {
                for(ResepModel resep : response.body()) {
                    Log.d("Add resep", resep.getNama());
                    Search search = new Search();
                    search.setId(resep.getId_menu());
                    search.setTitle(resep.getNama());
                    search.setMenit(resep.getWaktu() + " Menit");
                    search.setImagePath(R.drawable.image_sayur);
                    search.setFavorite(false);
                    searchList.add(search);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<ResepModel>> call, Throwable t) {
                Log.e("Error", "Error:", t);
            }
        });
    }
}