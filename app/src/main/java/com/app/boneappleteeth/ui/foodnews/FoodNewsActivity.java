package com.app.boneappleteeth.ui.foodnews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;


import com.app.boneappleteeth.databinding.ActivityFoodnewsBinding;

public class FoodNewsActivity extends AppCompatActivity {

    private ActivityFoodnewsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFoodnewsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle("Food News");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String html = "<ul type='square'>\n" +
                "        <li>Siang itu waktu rawan diserang rasa kantuk. Apalagi usai makan besar, mata rasanya sulit sekali untuk terbuka. Bagaimana caranya supaya tidak mengantuk berat usai makan siang?</li>" +
                "    </ul>"
                ;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            binding.isiBerita.setText(Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY));
        } else {
            binding.isiBerita.setText(Html.fromHtml(html));
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