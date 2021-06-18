package com.velxoz.finalproject.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.velxoz.finalproject.MainActivity;
import com.velxoz.finalproject.R;
import com.velxoz.finalproject.util.session.MainSession;
import com.velxoz.finalproject.views.auth.LoginActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private static Integer SPLASH_TIME_OUT = 1500;
    MainSession mainSession;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mainSession = new MainSession(SplashScreenActivity.this);
                i = (mainSession.isLoggedIn() ? new Intent(SplashScreenActivity.this, MainActivity.class) : new Intent(SplashScreenActivity.this, LoginActivity.class));
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}