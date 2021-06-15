package com.velxoz.finalproject.views.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.velxoz.finalproject.MainActivity;
import com.velxoz.finalproject.R;

public class RegisterActivity extends AppCompatActivity {

    String strUsername, strFirstName, strLastName, strPhoneNumber, strPassword;
    EditText etUsername, etFirstName, etLastName, etPhoneNumber, etPassword;
    Intent i;
    TextView tvLogin;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnRegister = findViewById(R.id.btnRegister);
        tvLogin = findViewById(R.id.tvLogin);

        tvLogin.setOnClickListener(v -> {
            i = new Intent(this, LoginActivity.class);
            startActivity(i);
            finish();
        });

        btnRegister.setOnClickListener(v -> {
            etUsername = findViewById(R.id.etUsername);
            etFirstName = findViewById(R.id.etFirstName);
            etLastName = findViewById(R.id.etLastName);
            etPhoneNumber = findViewById(R.id.etPhoneNumber);
            etPassword = findViewById(R.id.etPassword);
            strUsername = etUsername.getText().toString();
            strFirstName = etFirstName.getText().toString();
            strLastName = etLastName.getText().toString();
            strPhoneNumber = etPhoneNumber.getText().toString();
            strPassword = etPassword.getText().toString();

            if(strUsername.equals("")){
                etUsername.setError("Username cannot be empty");
            } else if(strFirstName.equals("")){
                etFirstName.setError("First Name cannot be empty");
            } else if(strLastName.equals("")){
                etLastName.setError("Last Name cannot be empty");
            } else if(strPhoneNumber.equals("")){
                etPhoneNumber.setError("Phone Number cannot be empty");
            } else if(strPassword.equals("")){
                etPassword.setError("Password cannot be empty");
            } else{
                i = new Intent(this, MainActivity.class);
                startActivity(i);
                finish();
            }

        });
    }
}