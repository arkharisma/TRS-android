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
import com.velxoz.finalproject.entity.auth.signin.SignInRequest;
import com.velxoz.finalproject.entity.auth.signin.SignInResponse;
import com.velxoz.finalproject.util.session.MainSession;
import com.velxoz.finalproject.models.APIClient;
import com.velxoz.finalproject.models.AuthApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    String strUsername, strPassword;
    Button btnLogin;
    TextView tvRegister;
    Intent i;
    AuthApiInterface authApiInterface;
    MainSession mainSession;

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
                final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this, R.style.dialogWaiting);
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
                                    loginProcess();
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    private void loginProcess(){
        authApiInterface = APIClient.getClient().create(AuthApiInterface.class);
        SignInRequest signInRequest = new SignInRequest(strUsername, strPassword);
        Call<SignInResponse> signInCall = authApiInterface.loginUser(signInRequest);

        signInCall.enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                SignInResponse signInResponse = response.body();
                if(response.code() == 200){
                    mainSession = new MainSession(LoginActivity.this);
                    mainSession.createLoginSession(signInResponse.getId(), signInResponse.getUsername(), signInResponse.getFirstName(), signInResponse.getLastName(), signInResponse.getMobileNumber(), signInResponse.getAccessToken());
                    i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Login Failed: " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}