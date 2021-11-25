package com.app.boneappleteeth;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BahanServices {
    @GET("bahan")
    Call<List<BahanModel>> getBahan();
}
