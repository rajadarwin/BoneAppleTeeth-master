package com.app.boneappleteeth.ui.register;

import static com.app.boneappleteeth.TextMultipleColor.getColoredSpanned;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;

import com.app.boneappleteeth.MainActivity;
import com.app.boneappleteeth.R;
import com.app.boneappleteeth.TextMultipleColor;
import com.app.boneappleteeth.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private boolean visible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String string1 = getColoredSpanned("Belum punya akun?", "#000000");
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
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            }
        });
    }
}