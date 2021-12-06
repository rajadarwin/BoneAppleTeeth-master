package com.app.boneappleteeth;

import android.accounts.Account;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AccountServices {
    @POST("/register")
    Call<ResponseModel>  register(@Body AccountModel accountModel);

    @POST("/login")
    Call<FullAccountModel>  login(@Body LoginModel loginModel);

}
