package com.app.boneappleteeth.ui.foodpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;

import com.app.boneappleteeth.databinding.ActivityFoodBinding;

public class FoodActivity extends AppCompatActivity {

    private ActivityFoodBinding binding;

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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            binding.tvBahan.setText(Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY));
        } else {
            binding.tvBahan.setText(Html.fromHtml(html));
        }
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
}