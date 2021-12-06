package com.app.boneappleteeth;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.app.boneappleteeth.ui.register.RegisterActivity;

public class SplashActivity extends AppCompatActivity {

    Animation logoanim, tulisanim;
    ImageView foto, logo, title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        logoanim = AnimationUtils.loadAnimation(this, R.anim.foto_animation);
        tulisanim = AnimationUtils.loadAnimation(this, R.anim.tulisan_animation);

        foto = findViewById(R.id.foto);
        logo = findViewById(R.id.logo);
        title = findViewById(R.id.title);

        foto.setAnimation(logoanim);
        logo.setAnimation(tulisanim);
        title.setAnimation(tulisanim);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, RegisterActivity.class);

                Pair[] pairs = new Pair[2];
                pairs[0] = new Pair<View, String>(foto, "foto");
                pairs[1] = new Pair<View, String>(logo, "text");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SplashActivity.this,pairs);
                startActivity(intent,options.toBundle());
                finish();
            }
        }, 2000);

    }
}
