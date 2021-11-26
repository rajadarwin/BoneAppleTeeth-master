package com.app.boneappleteeth;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ResepServices {
    @GET("resep")
    Call<List<ResepModel>> getResep();

}
