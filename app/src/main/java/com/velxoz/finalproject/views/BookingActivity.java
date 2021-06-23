package com.velxoz.finalproject.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.velxoz.finalproject.R;
import com.velxoz.finalproject.entity.ObjectResponse;
import com.velxoz.finalproject.entity.booking.BookingRequest;
import com.velxoz.finalproject.entity.booking.BookingResponse;
import com.velxoz.finalproject.entity.tripschedule.TripScheduleResponse;
import com.velxoz.finalproject.models.APIClient;
import com.velxoz.finalproject.models.TicketInterface;
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
    TicketInterface ticketInterface;
    MainSession mainSession;
    HashMap<String, String> user;
    String token, user_id, id_trip;

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

        btnPesan.setOnClickListener(v -> bookTicket());
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
        user_id = user.get("_id");
        token = user.get("token");

        Bundle bundle = getIntent().getExtras();
        id_trip = bundle.getString("id_trip");
    }

    private void bookTicket(){
        BookingRequest bookingRequest = new BookingRequest(false, tvTanggal.getText().toString(), user_id, 1, id_trip);
        ticketInterface = APIClient.getClient(token).create(TicketInterface.class);
        Call<ObjectResponse<BookingResponse>> bookCall = ticketInterface.bookTicket(bookingRequest);

        bookCall.enqueue(new Callback<ObjectResponse<BookingResponse>>() {
            @Override
            public void onResponse(Call<ObjectResponse<BookingResponse>> call, Response<ObjectResponse<BookingResponse>> response) {
                if (response.body() != null){
                    ObjectResponse<BookingResponse> bookObject = response.body();
                    if(bookObject.getSuccess()){
                        Bundle bundle = new Bundle();
                        bundle.putString("id_ticket", bookObject.getObject().getId());
                        Intent i = new Intent(BookingActivity.this, TicketActivity.class);
                        i.putExtras(bundle);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                        finish();
                    }
                } else{
                    Toast.makeText(BookingActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ObjectResponse<BookingResponse>> call, Throwable t) {
                Toast.makeText(BookingActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void callApi(){
        tripInterface = APIClient.getClient(token).create(TripInterface.class);
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