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
import com.velxoz.finalproject.entity.user.ChangeUserRequest;
import com.velxoz.finalproject.entity.user.GetUserResponse;
import com.velxoz.finalproject.entity.user.UserResponse;
import com.velxoz.finalproject.models.APIClient;
import com.velxoz.finalproject.models.UserInterface;
import com.velxoz.finalproject.util.session.MainSession;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UbahDataActivity extends AppCompatActivity {

    EditText etFirstName, etLastName, etPhoneNumber;
    Button btnUbah;
    MainSession mainSession;
    UserInterface userInterface;
    HashMap<String, String> user;
    String token, user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_data);
        loadComponent();

        btnUbah.setOnClickListener(v -> {
            final ProgressDialog progressDialog = new ProgressDialog(UbahDataActivity.this, R.style.dialogWaiting);
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
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        btnUbah = findViewById(R.id.btnUbahDataDiri);
        mainSession = new MainSession(this);
        user = mainSession.getUserDetails();
        token = user.get("token");
        user_id = user.get("_id");
        etFirstName.setText(user.get("first_name"));
        etLastName.setText(user.get("last_name"));
        etPhoneNumber.setText(user.get("mobile_number"));
    }

    private void btnUbahClicked(){
        if(etFirstName.getText().toString().equals("")){
            etFirstName.setError("Harap diisi");
        }
        if (etLastName.getText().toString().equals("")){
            etLastName.setError("Harap diisi");
        }
        if (etPhoneNumber.getText().toString().equals("")){
            etPhoneNumber.setError("Harap diisi");
        } else{
            userInterface = APIClient.getClient(token).create(UserInterface.class);
            ChangeUserRequest changeUserRequest = new ChangeUserRequest(etFirstName.getText().toString(), etLastName.getText().toString(), etPhoneNumber.getText().toString());
            Call<ObjectResponse<UserResponse>> userCall = userInterface.updateUser(changeUserRequest);
            userCall.enqueue(new Callback<ObjectResponse<UserResponse>>() {
                @Override
                public void onResponse(Call<ObjectResponse<UserResponse>> call, Response<ObjectResponse<UserResponse>> response) {
                    if (response.body() != null){
                        ObjectResponse<UserResponse> userObj = response.body();
                        if(userObj.getSuccess()){
                            mainSession.updateSession(userObj.getObject().getFirstName(), userObj.getObject().getLastName(), userObj.getObject().getMobileNumber());
                            Toast.makeText(UbahDataActivity.this, "Data berhasil diubah.", Toast.LENGTH_SHORT).show();
                            finish();
                        } else{
                            Toast.makeText(UbahDataActivity.this, userObj.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else{
                        Toast.makeText(UbahDataActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ObjectResponse<UserResponse>> call, Throwable t) {
                    Toast.makeText(UbahDataActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}