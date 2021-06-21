package com.velxoz.finalproject.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.velxoz.finalproject.R;
import com.velxoz.finalproject.adapter.TripScheduleListAdapter;
import com.velxoz.finalproject.entity.ListResponse;
import com.velxoz.finalproject.entity.tripschedule.TripScheduleResponse;
import com.velxoz.finalproject.models.APIClient;
import com.velxoz.finalproject.models.TripInterface;
import com.velxoz.finalproject.util.session.MainSession;
import com.velxoz.finalproject.views.auth.LoginActivity;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchTripActivity extends AppCompatActivity {

    RecyclerView rvListTrip;
    TripInterface tripInterface;
    String sourceStop, destStop, tanggal, token;
    HashMap<String, String> user;
    MainSession mainSession;
    private RecyclerView.Adapter mTripAdapter;
    private RecyclerView.LayoutManager mLayoutManagerTrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_trip);

        mainSession = new MainSession(SearchTripActivity.this);
        user = mainSession.getUserDetails();
        token = user.get("token");
        rvListTrip = findViewById(R.id.rvListTrip);
        mLayoutManagerTrip = new LinearLayoutManager(this);
        rvListTrip.setLayoutManager(mLayoutManagerTrip);
        tripInterface = APIClient.getClient(token).create(TripInterface.class);

        final ProgressDialog progressDialog = new ProgressDialog(SearchTripActivity.this, R.style.dialogWaiting);
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
                            refresh();
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

    private void refresh(){
        Bundle bundle = getIntent().getExtras();
        tanggal = bundle.getString("tanggal").toString();
        Log.d("TANGGAL", "refresh: " + tanggal);
        sourceStop = bundle.getString("source_stop").toString();
        destStop = bundle.getString("dest_stop").toString();
        Call<ListResponse<TripScheduleResponse>> tripCall;
        if(tanggal.equals("")){
            tripCall = tripInterface.getTripsByStops(sourceStop, destStop);
        } else{
            tripCall = tripInterface.getTripsAllIndicator(sourceStop, destStop, tanggal);
        }
        tripCall.enqueue(new Callback<ListResponse<TripScheduleResponse>>() {
            @Override
            public void onResponse(Call<ListResponse<TripScheduleResponse>> call, Response<ListResponse<TripScheduleResponse>> response) {
                ListResponse<TripScheduleResponse> tripList = response.body();
                mTripAdapter = new TripScheduleListAdapter(tripList, SearchTripActivity.this);
                rvListTrip.setAdapter(mTripAdapter);
            }

            @Override
            public void onFailure(Call<ListResponse<TripScheduleResponse>> call, Throwable t) {
                Toast.makeText(SearchTripActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}