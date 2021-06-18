package com.velxoz.finalproject.views.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.velxoz.finalproject.MainActivity;
import com.velxoz.finalproject.R;
import com.velxoz.finalproject.entity.auth.signup.SignUpRequest;
import com.velxoz.finalproject.entity.auth.signup.SignUpResponse;
import com.velxoz.finalproject.models.APIClient;
import com.velxoz.finalproject.models.AuthApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    String strUsername, strFirstName, strLastName, strPhoneNumber, strPassword;
    EditText etUsername, etFirstName, etLastName, etPhoneNumber, etPassword;
    Intent i;
    TextView tvLogin;
    Button btnRegister;
    AuthApiInterface authApiInterface;

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
                final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this, R.style.dialogWaiting);
                progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                progressDialog.show();

                new Thread(){
                    @Override
                    public void run(){
                        super.run();
                        try{
                            Thread.sleep(2000);
                            if(progressDialog.isShowing()){
                                progressDialog.dismiss();
                            }
                            try{
                                runOnUiThread(() -> {
                                    registerProcess();
                                });
                            } catch(Exception e) {
                                e.printStackTrace();
                                Log.wtf("Error : ", e.getMessage());
                            }
                        } catch(InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }

        });
    }

    public void registerProcess(){
        authApiInterface = APIClient.getClient().create(AuthApiInterface.class);
        List<String> role = new ArrayList<String>();
        role.add("user");
        SignUpRequest signUpRequest = new SignUpRequest(
                strFirstName, strLastName, strPhoneNumber, strPassword, role, strUsername
        );
        Call<SignUpResponse> registerCall = authApiInterface.registerUser(signUpRequest);
        registerCall.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                SignUpResponse signUpResponse = response.body();
                if(signUpResponse.getSuccess()){
                    Toast.makeText(RegisterActivity.this, signUpResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    i = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}