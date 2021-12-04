package com.app.boneappleteeth;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BahanServices {
    @GET("bahan")
    Call<List<BahanModel>> getBahan();

    @GET("bahan/{id}")
    Call<List<BahanModel>> getBahanId(@Path("id") int id);
}
