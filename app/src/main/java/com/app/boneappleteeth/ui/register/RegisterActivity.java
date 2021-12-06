package com.app.boneappleteeth.ui.register;

import static com.app.boneappleteeth.TextMultipleColor.getColoredSpanned;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.boneappleteeth.AccountModel;
import com.app.boneappleteeth.AccountRepository;
import com.app.boneappleteeth.AccountServices;
import com.app.boneappleteeth.FullAccountModel;
import com.app.boneappleteeth.MainActivity;
import com.app.boneappleteeth.PostFactory;
import com.app.boneappleteeth.R;
import com.app.boneappleteeth.ResponseModel;
import com.app.boneappleteeth.databinding.ActivityRegisterBinding;
import com.app.boneappleteeth.ui.login.LoginActivity;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private boolean visible = false;
    private Button button;
    private EditText et_user, et_email, et_password;
    final String PREF_NAME = "ACCOUNT";
    private SharedPreferences sharedPreferences;
    private String sharedPrefFile;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        context = this;
        sharedPrefFile = context.getPackageName();
        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        Boolean loggedIn = sharedPreferences.getBoolean("isLoggedIn", false);


        if(loggedIn){
            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
        }

        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        et_user = findViewById(R.id.et_user);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);



        button = (Button) findViewById(R.id.tv_login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLogin();
            }
        });

        String string1 = getColoredSpanned("Sudah punya akun?", "#000000");
        String string2 = getColoredSpanned("Log in", "#4E9F3D");
        binding.tvLogin.setText(Html.fromHtml(string1 + " " + string2));

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

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Loading...", Toast.LENGTH_LONG);
                register();
                /*if(register()) {
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                }*/
            }
        });
    }
    public void openLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void register(){
        if(!valid()) return;
        registerAPI();
        /*if(responseModel.getStatus().equals("200")){
            Toast.makeText(getApplicationContext(), "Successfully registered!", Toast.LENGTH_SHORT).show();
            return true;
        }*/
        //return true;
    }

    public void registerAPI(){
        String username = et_user.getText().toString();
        String email = et_email.getText().toString();
        String password = et_password.getText().toString();

        AccountModel account = new AccountModel(username, email, password,"placeholder.png", "placeholder");

        AccountServices service = AccountRepository.create();
        service.register(account).enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                try {
                    Log.d("Login status", response.body().getStatus());
                    if(response.body().getStatus().equals("200")){
                        SharedPreferences.Editor preferencesEditor = sharedPreferences.edit();
                        Gson gson = new Gson();
                        FullAccountModel fullAccountModel = new FullAccountModel(account.getUsername(), account.getEmail(), account.getPassword(), account.getFoto(), account.getAlamat(), response.body().getMessage());
                        String json = gson.toJson(fullAccountModel);
                        preferencesEditor.putBoolean("isLoggedIn", true);
                        preferencesEditor.putString("account", json);
                        preferencesEditor.apply();
                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                    }
                    else{
                        et_user.setError("Username is already taken!");
                    }
                }catch (Exception e){
                    et_user.setError("Username is already taken!");
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG);
                    Log.e("Error", e.getMessage());
                }

            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {

            }
        });
    }

    public boolean valid(){
        String username = et_user.getText().toString();
        String email = et_email.getText().toString();
        String password = et_password.getText().toString();
        boolean isValid = true;

        if(username.isEmpty()){
            et_user.setError("Username should not be empty");
            isValid =  false;
        }
        if(email.isEmpty()){
            et_user.setError("Email should not be empty!");
            isValid = false;
        }
        if(password.isEmpty()){
            et_password.setError("Password should not be empty!");
            isValid = false;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            et_email.setError("Not a valid email address!");
            isValid = false;
        }

        return isValid;
    }
}