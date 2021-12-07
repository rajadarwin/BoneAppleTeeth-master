package com.app.boneappleteeth;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;

import com.app.boneappleteeth.ui.login.LoginActivity;
import com.app.boneappleteeth.ui.register.RegisterActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.app.boneappleteeth.databinding.ActivityMainBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    final String PREF_NAME = "ACCOUNT";
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        Boolean loggedIn = sharedPreferences.getBoolean("isLoggedIn", false);


        if (loggedIn) {
            startActivity(new Intent(this, RegisterActivity.class));
            finish();
        }

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_search, R.id.navigation_favorite, R.id.navigation_profile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
        apiTest();
    }

    void apiTest(){
        //bahanApiCall();
        //resepApiCall();
    }

    void bahanApiCall(){
        BahanServices bahanServices = BahanRepository.create();
        bahanServices.getBahan().enqueue(new Callback<List<BahanModel>>() {
            @Override
            public void onResponse(Call<List<BahanModel>> call, Response<List<BahanModel>> response) {
                for(BahanModel bahan : response.body()){
                    Log.d("ID : ", String.valueOf(bahan.getId_bahan()));
                    Log.d("Nama : ", bahan.getNama());
                }
            }

            @Override
            public void onFailure(Call<List<BahanModel>> call, Throwable t) {
                Log.e("Error", "Error:", t);
            }
        });
    }

    void resepApiCall(){
        ResepServices resepServices = ResepRepository.create();
        resepServices.getResep().enqueue(new Callback<List<ResepModel>>() {
            @Override
            public void onResponse(Call<List<ResepModel>> call, Response<List<ResepModel>> response) {
                for(ResepModel resep : response.body()) {
                    Log.d("ID : ", String.valueOf(resep.getId_menu()));
                    Log.d("Nama : ", resep.getNama());
                    for(BahanModel bahan : resep.getBahan()){
                        Log.d("Bahan :" , bahan.getNama());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ResepModel>> call, Throwable t) {
                Log.e("Error", "Error:", t);
            }
        });
    }
}