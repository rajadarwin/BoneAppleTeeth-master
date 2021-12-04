package com.app.boneappleteeth;

import android.util.Log;

import com.app.boneappleteeth.ui.search.model.Search;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetFactory {

    public GetFactory() { };
    private List<ResepModel> recipes;
    private List<BahanModel> bahan;

    public List<ResepModel> getAllRecipes(){

        ResepServices resepServices = ResepRepository.create();
        resepServices.getResep().enqueue(new Callback<List<ResepModel>>() {
            @Override
            public void onResponse(Call<List<ResepModel>> call, Response<List<ResepModel>> response) {
                recipes =  response.body();
            }

            @Override
            public void onFailure(Call<List<ResepModel>> call, Throwable t) {
                Log.e("Error", "Error:", t);
            }
        });
        return recipes;
    }

    public List<ResepModel> getRecipe(int id){
        ResepServices resepServices = ResepRepository.create();
        resepServices.getResepId(id).enqueue(new Callback<List<ResepModel>>() {
            @Override
            public void onResponse(Call<List<ResepModel>> call, Response<List<ResepModel>> response) {
                recipes = response.body();
            }

            @Override
            public void onFailure(Call<List<ResepModel>> call, Throwable t) {

            }
        });
        return recipes;
    }

    public List<BahanModel> getBahan(){
        BahanServices bahanServices = BahanRepository.create();
        bahanServices.getBahan().enqueue(new Callback<List<BahanModel>>() {
            @Override
            public void onResponse(Call<List<BahanModel>> call, Response<List<BahanModel>> response) {
                bahan = response.body();
            }

            @Override
            public void onFailure(Call<List<BahanModel>> call, Throwable t) {

            }
        });

        return bahan;
    }

    public List<BahanModel> getBahanId(int id){
        BahanServices bahanServices = BahanRepository.create();
        bahanServices.getBahanId(id).enqueue(new Callback<List<BahanModel>>() {
            @Override
            public void onResponse(Call<List<BahanModel>> call, Response<List<BahanModel>> response) {
                bahan = response.body();
            }

            @Override
            public void onFailure(Call<List<BahanModel>> call, Throwable t) {

            }
        });

        return bahan;
    }

}
