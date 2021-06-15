package com.velxoz.finalproject.views.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.velxoz.finalproject.MainActivity;
import com.velxoz.finalproject.R;

public class LoginActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    String strUsername, strPassword;
    Button btnLogin;
    TextView tvRegister;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);

        tvRegister.setOnClickListener(v -> {
            i = new Intent(this, RegisterActivity.class);
            startActivity(i);
        });

        btnLogin.setOnClickListener(v -> {
            etUsername = findViewById(R.id.etUsername);
            etPassword = findViewById(R.id.etPassword);
            strUsername = etUsername.getText().toString();
            strPassword = etPassword.getText().toString();
            if(strUsername.equals("")){
                etUsername.setError("Username cannot be empty");
            } else if(strPassword.equals("")){
                etPassword.setError("Password cannot be empty");
            } else {
                i = new Intent(this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}