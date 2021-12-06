package com.app.boneappleteeth;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsRepository {
    static String BASE_URL = "https://api.hadehzone.xyz/";

    public static final NewsServices create(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(NewsServices.class);
    }
}
