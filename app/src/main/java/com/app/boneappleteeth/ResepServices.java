package com.app.boneappleteeth;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ResepServices {
    @GET("resep")
    Call<List<ResepModel>> getResep();

    @GET("resep/{id}")
    Call<List<ResepModel>> getResepId(@Path("id") int id);

    @GET("resepuser/{username}")
    Call<List<ResepModel>> getUserResep(@Path("username") String username);

    @POST("resep")
    Call<ResponseModel> addResep(@Body NewRecipeModel newRecipeModel);
}
