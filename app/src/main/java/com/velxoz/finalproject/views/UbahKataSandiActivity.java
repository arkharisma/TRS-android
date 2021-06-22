package com.velxoz.finalproject.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.velxoz.finalproject.R;
import com.velxoz.finalproject.entity.ObjectResponse;
import com.velxoz.finalproject.entity.user.ChangePasswordRequest;
import com.velxoz.finalproject.entity.user.UserResponse;
import com.velxoz.finalproject.models.APIClient;
import com.velxoz.finalproject.models.UserInterface;
import com.velxoz.finalproject.util.session.MainSession;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UbahKataSandiActivity extends AppCompatActivity {

    EditText etNewPassword;
    Button btnUbahPassword;
    UserInterface userInterface;
    MainSession mainSession;
    HashMap<String, String> user;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_kata_sandi);

        loadComponent();

        btnUbahPassword.setOnClickListener(v -> {
            final ProgressDialog progressDialog = new ProgressDialog(UbahKataSandiActivity.this, R.style.dialogWaiting);
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            progressDialog.show();

            new Thread(){
                @Override
                public void run(){
                    super.run();
                    try{
                        Thread.sleep(1000);
                        if(progressDialog.isShowing()){
                            progressDialog.dismiss();
                        }
                        try{
                            runOnUiThread(() -> {
                                btnUbahClicked();
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
        });
    }

    private void loadComponent(){
        etNewPassword = findViewById(R.id.etNewPassword);
        btnUbahPassword = findViewById(R.id.btnUbahKataSandi);
        mainSession = new MainSession(this);
        user = mainSession.getUserDetails();
        token = user.get("token");
    }

    private void btnUbahClicked(){
        if (etNewPassword.getText().toString().equals("")){
            etNewPassword.setError("Harap isi kata sandi baru");
            etNewPassword.findFocus();
        } else{
            ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest(etNewPassword.getText().toString());
            userInterface = APIClient.getClient(token).create(UserInterface.class);

            Call<ObjectResponse<UserResponse>> userCall = userInterface.changePassword(changePasswordRequest);

            userCall.enqueue(new Callback<ObjectResponse<UserResponse>>() {
                @Override
                public void onResponse(Call<ObjectResponse<UserResponse>> call, Response<ObjectResponse<UserResponse>> response) {
                    if (response.body() != null){
                        ObjectResponse<UserResponse> userResp = response.body();
                        if (userResp.getSuccess()){
                            Toast.makeText(UbahKataSandiActivity.this, "Kata sandi berhasil diubah.", Toast.LENGTH_SHORT).show();
                            finish();
                        } else{
                            Toast.makeText(UbahKataSandiActivity.this, userResp.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else{
                        Toast.makeText(UbahKataSandiActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ObjectResponse<UserResponse>> call, Throwable t) {
                    Toast.makeText(UbahKataSandiActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}