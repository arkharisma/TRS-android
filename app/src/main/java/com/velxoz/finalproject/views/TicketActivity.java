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
import com.velxoz.finalproject.adapter.TicketListAdapter;
import com.velxoz.finalproject.entity.ListResponse;
import com.velxoz.finalproject.entity.ticket.Ticket;
import com.velxoz.finalproject.models.APIClient;
import com.velxoz.finalproject.models.TicketInterface;
import com.velxoz.finalproject.util.session.MainSession;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TicketActivity extends AppCompatActivity {

    RecyclerView rvTicket;
    private RecyclerView.Adapter mTripAdapter;
    private RecyclerView.LayoutManager mLayoutManagerTrip;
    TicketInterface ticketInterface;
    MainSession mainSession;
    HashMap<String, String> user;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        mainSession = new MainSession(TicketActivity.this);
        user = mainSession.getUserDetails();
        token = user.get("token");
        rvTicket = findViewById(R.id.rvTicket);
        mLayoutManagerTrip = new LinearLayoutManager(this);
        rvTicket.setLayoutManager(mLayoutManagerTrip);
        ticketInterface = APIClient.getClient(token).create(TicketInterface.class);

        final ProgressDialog progressDialog = new ProgressDialog(TicketActivity.this, R.style.dialogWaiting);
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
        Call<ListResponse<Ticket>> ticketCall = ticketInterface.ticketList();
        ticketCall.enqueue(new Callback<ListResponse<Ticket>>() {
            @Override
            public void onResponse(Call<ListResponse<Ticket>> call, Response<ListResponse<Ticket>> response) {
                ListResponse<Ticket> ticketList = response.body();
                mTripAdapter = new TicketListAdapter(ticketList, TicketActivity.this);
                rvTicket.setAdapter(mTripAdapter);
            }

            @Override
            public void onFailure(Call<ListResponse<Ticket>> call, Throwable t) {
                Toast.makeText(TicketActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}