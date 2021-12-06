package com.app.boneappleteeth.ui.foodpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.app.boneappleteeth.ResepModel;
import com.app.boneappleteeth.ResepRepository;
import com.app.boneappleteeth.ResepServices;
import com.app.boneappleteeth.databinding.ActivityFoodBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodActivity extends AppCompatActivity {

    private ActivityFoodBinding binding;

    TextView tv_rating;
    TextView tv_time;
    TextView tv_kesulitan;
    TextView tv_nama_resep;
    TextView tv_resep;

    String nama_resep;
    String waktu_resep;

    int id_resep;
    ResepModel resep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFoodBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle("Food Detail");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String html = "<ul type='square'>\n" +
                "        <li>600 gram nasi putih</li>" +
                "        <li>1 Siung bawang putih</li>" +
                "        <li>1 Sdm royco</li>" +
                "        <li>2 Sdm minyak goreng</li>" +
                "        <li>3 Putri bawang merah</li>" +
                "        <li>2 Sdm kecap bango</li>" +
                "        <li>1 Batang daun bawang</li>" +
                "        <li>1 Butir telur</li>" +
                "    </ul>"
                ;
       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            binding.tvBahan.setText(Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY));
        } else {
            binding.tvBahan.setText(Html.fromHtml(html));
        }*/

        Bundle extras = getIntent().getExtras();

        //nama_resep = extras.getString("NAMA");
        //waktu_resep = extras.getString("MENIT");
        id_resep = extras.getInt("ID");

        Log.d("ID", String.valueOf(id_resep));

        apiCall();
        //setTextViews();

        //binding.tvNamaResep.setText(nama_resep);
        //binding.tvTime.setText(waktu_resep);
        Log.d("onCreate", String.valueOf(resep));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void apiCall(){
        Log.d("apiCall", "Started");
        ResepServices resepServices = ResepRepository.create();
        resepServices.getResepId(id_resep).enqueue(new Callback<List<ResepModel>>() {
            @Override
            public void onResponse(Call<List<ResepModel>> call, Response<List<ResepModel>> response) {
                Log.d("responseBody", String.valueOf(response.body()));
                for(ResepModel r : response.body()){
                    resep = r;
                    Log.d("Nama", r.getNama());
                    Log.d("waktu", String.valueOf(r.getWaktu()));
                    Log.d("Resep object", String.valueOf(resep));

                    binding.tvNamaResep.setText(r.getNama());
                    binding.tvTime.setText(String.valueOf(r.getWaktu()) + " Menit");
                    binding.tvRating.setText(String.valueOf(r.getRating()) + " (" + String.valueOf(r.getRating_count()) + ")");
                    binding.tvThumbs.setText(String.valueOf(r.getKesulitan()));
                    binding.tvBahan.setText(r.getResep());
                }
            }

            @Override
            public void onFailure(Call<List<ResepModel>> call, Throwable t) {
                Log.e("Fail", "onFailure: ", t);
            }
        });
    }

    private void setTextViews(){
        /*binding.tvNamaResep.setText(resep.getNama());
        binding.tvTime.setText(resep.getWaktu());
        binding.tvRating.setText(String.valueOf(resep.getRating()) + " (" + String.valueOf(resep.getRating_count()) + ")");
        binding.tvKesulitan.setText(resep.getKesulitan());
        binding.tvResep.setText(resep.getResep());*/
    }
}