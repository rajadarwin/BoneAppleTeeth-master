package com.app.boneappleteeth;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AccountRepository {
    static String BASE_URL = "https://api.hadehzone.xyz/";

    public static final AccountServices create(){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        return retrofit.create(AccountServices.class);
    }
}
