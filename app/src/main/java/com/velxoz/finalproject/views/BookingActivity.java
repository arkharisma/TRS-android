package com.velxoz.finalproject.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.velxoz.finalproject.R;
import com.velxoz.finalproject.entity.ObjectResponse;
import com.velxoz.finalproject.entity.tripschedule.TripScheduleResponse;
import com.velxoz.finalproject.models.APIClient;
import com.velxoz.finalproject.models.TripInterface;
import com.velxoz.finalproject.util.Currency;
import com.velxoz.finalproject.util.session.MainSession;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingActivity extends AppCompatActivity {

    Button btnPesan;
    TextView tvAgencyName, tvBusCode, tvSourceStop, tvDestStop, tvTanggal, tvJam, tvSeats, tvHarga;
    TripInterface tripInterface;
    MainSession mainSession;
    HashMap<String, String> user;
    String token, id_trip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        loadComponents();

        final ProgressDialog progressDialog = new ProgressDialog(BookingActivity.this, R.style.dialogWaiting);
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
                            callApi();
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

    private void loadComponents(){
        btnPesan = findViewById(R.id.btnPesan);
        tvAgencyName = findViewById(R.id.tvAgencyName);
        tvBusCode = findViewById(R.id.tvBusCode);
        tvSourceStop = findViewById(R.id.tvSourceStop);
        tvDestStop = findViewById(R.id.tvDestStop);
        tvTanggal = findViewById(R.id.tvTanggal);
        tvJam = findViewById(R.id.tvJam);
        tvSeats = findViewById(R.id.tvSeats);
        tvHarga = findViewById(R.id.tvHarga);

        mainSession = new MainSession(this);
        user = mainSession.getUserDetails();
        token = user.get("token");

        tripInterface = APIClient.getClient(token).create(TripInterface.class);

        Bundle bundle = getIntent().getExtras();
        id_trip = bundle.getString("id_trip");
    }

    private void callApi(){
        Call<ObjectResponse<TripScheduleResponse>> tripCall = tripInterface.getTripDetail(id_trip);

        tripCall.enqueue(new Callback<ObjectResponse<TripScheduleResponse>>() {
            @Override
            public void onResponse(Call<ObjectResponse<TripScheduleResponse>> call, Response<ObjectResponse<TripScheduleResponse>> response) {
                if (response.body() != null){
                    ObjectResponse<TripScheduleResponse> getResponse = response.body();
                    if(getResponse.getSuccess()){
                        tvAgencyName.setText(getResponse.getObject().getAgencyName());
                        tvBusCode.setText(getResponse.getObject().getBusCode());
                        tvSourceStop.setText(getResponse.getObject().getSourceStop());
                        tvDestStop.setText(getResponse.getObject().getDestStop());
                        tvTanggal.setText(getResponse.getObject().getTripDate());
                        tvJam.setText(getResponse.getObject().getJourneyTime());
                        tvSeats.setText(getResponse.getObject().getAvailableSeats());
                        tvHarga.setText(Currency.getCurrencyFormat(Integer.valueOf(getResponse.getObject().getFare())));
                    }
                }
            }

            @Override
            public void onFailure(Call<ObjectResponse<TripScheduleResponse>> call, Throwable t) {
                Toast.makeText(BookingActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}