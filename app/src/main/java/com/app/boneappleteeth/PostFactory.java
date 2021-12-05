package com.app.boneappleteeth;

import android.util.Log;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostFactory {

    public PostFactory() {
    }

    private ResponseModel responseModel = new ResponseModel();

    public ResponseModel register(AccountModel account){
        AccountServices service = AccountRepository.create();

        return responseModel;
    }
}
