package com.app.boneappleteeth.ui.login;

import static androidx.core.content.ContextCompat.startActivity;
import static com.app.boneappleteeth.TextMultipleColor.getColoredSpanned;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.app.boneappleteeth.AccountRepository;
import com.app.boneappleteeth.AccountServices;
import com.app.boneappleteeth.FullAccountModel;
import com.app.boneappleteeth.LoginModel;
import com.app.boneappleteeth.MainActivity;
import com.app.boneappleteeth.R;
import com.app.boneappleteeth.ResponseModel;
import com.app.boneappleteeth.TextMultipleColor;
import com.app.boneappleteeth.databinding.ActivityLoginBinding;
import com.app.boneappleteeth.ui.register.RegisterActivity;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity{
    private ActivityLoginBinding binding;
    private boolean visible = false;
    private Button button;

    EditText et_username;
    EditText et_password;

    final String PREF_NAME = "ACCOUNT";
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        Boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

        if(isLoggedIn){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

        button = (Button) findViewById(R.id.tv_register);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegister();
            }
        });

        et_username = findViewById(R.id.et_user);
        et_password = findViewById(R.id.et_password);

        String string1 = getColoredSpanned("Belum punya akun?", "#000000");
        String string2 = getColoredSpanned("Register", "#4E9F3D");
        binding.tvRegister.setText(Html.fromHtml(string1 + " " + string2));

        binding.ivVisible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!visible) {
                    binding.etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    binding.ivVisible.setBackgroundResource(R.drawable.ic_baseline_visibility_off_24);
                    visible = true;
                } else {
                    binding.etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    binding.ivVisible.setBackgroundResource(R.drawable.ic_baseline_visibility_24);
                    visible = false;
                }
            }
        });

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
                //startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });
    }
    public void openRegister() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }

    void login(){
        String username = et_username.getText().toString();
        String password = et_password.getText().toString();

        if(!validate(username, password)){
            if(username.isEmpty()) et_username.setError("Cannot be empty!");
            if(password.isEmpty()) et_password.setError("Cannot be empty!");
            return;
        }

        LoginModel loginAccount = new LoginModel(username, password);

        AccountServices service = AccountRepository.create();
        service.login(loginAccount).enqueue(new Callback<FullAccountModel>() {
            @Override
            public void onResponse(Call<FullAccountModel> call, Response<FullAccountModel> response) {
                try{
                    if(response.body().getClass() != FullAccountModel.class){
                        et_username.setError("Invalid credentials!");
                    }
                    if(response.isSuccessful()) {
                        FullAccountModel account = response.body();
                        if(account.getEmail() == null){
                            et_username.setError("Invalid credentials!");
                            return;
                        }
                        Log.d("Response body:", account.toString());
                        SharedPreferences.Editor preferencesEditor = sharedPreferences.edit();
                        Gson gson = new Gson();
                        String json = gson.toJson(account);
                        preferencesEditor.putBoolean("isLoggedIn", true);
                        preferencesEditor.putString("account", json);
                        preferencesEditor.apply();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    }else{
                        et_username.setError("Invalid credentials!");
                    }
                }
                catch(Exception e){
                    et_username.setError("Invalid credentials!");
                }
            }

            @Override
            public void onFailure(Call<FullAccountModel> call, Throwable t) {
                et_username.setError("Unknown error!");
            }
        });


    }

    boolean validate(String username, String password){
        return !username.isEmpty() && !password.isEmpty();
    }
}
