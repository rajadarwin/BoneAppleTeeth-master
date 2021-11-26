package com.app.boneappleteeth;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ResepServices {
    @GET("resep")
    Call<List<ResepModel>> getResep();

    @GET("resep/{id}")
    Call<List<ResepModel>> getResepId(@Path("id") int id);
}
