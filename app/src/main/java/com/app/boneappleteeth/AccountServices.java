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

    @POST("/update")
    Call<ResponseModel> update(@Body AccountUpdateModel accountUpdateModel);

    @POST("/delete")
    Call<ResponseModel> delete(@Body DeleteAccountModel deleteAccountModel);

}
