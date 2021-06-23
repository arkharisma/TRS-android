package com.velxoz.finalproject.views.fragments;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.velxoz.finalproject.R;
import com.velxoz.finalproject.adapter.TicketListAdapter;
import com.velxoz.finalproject.entity.ListResponse;
import com.velxoz.finalproject.entity.ticket.Ticket;
import com.velxoz.finalproject.models.APIClient;
import com.velxoz.finalproject.models.TicketInterface;
import com.velxoz.finalproject.util.session.MainSession;
import com.velxoz.finalproject.views.TicketActivity;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TicketFragment extends Fragment {

    TextView tvNone;
    View view;
    RecyclerView rvTicket;
    private RecyclerView.Adapter mTripAdapter;
    private RecyclerView.LayoutManager mLayoutManagerTrip;
    TicketInterface ticketInterface;
    MainSession mainSession;
    HashMap<String, String> user;
    String token;

    public TicketFragment() {
    }

    public static TicketFragment newInstance() {
        TicketFragment fragment = new TicketFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_ticket, container, false);

        mainSession = new MainSession(view.getContext());
        user = mainSession.getUserDetails();
        token = user.get("token");
        tvNone = view.findViewById(R.id.tvNone);
        rvTicket = view.findViewById(R.id.rvTicket);
        mLayoutManagerTrip = new LinearLayoutManager(view.getContext());
        rvTicket.setLayoutManager(mLayoutManagerTrip);
        ticketInterface = APIClient.getClient(token).create(TicketInterface.class);

        final ProgressDialog progressDialog = new ProgressDialog(view.getContext(), R.style.dialogWaiting);
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
                        getActivity().runOnUiThread(() -> {
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

        return view;
    }

    private void refresh(){
        Call<ListResponse<Ticket>> ticketCall = ticketInterface.ticketList();
        ticketCall.enqueue(new Callback<ListResponse<Ticket>>() {
            @Override
            public void onResponse(Call<ListResponse<Ticket>> call, Response<ListResponse<Ticket>> response) {
                if(response.body() != null) {
                    ListResponse<Ticket> ticketList = response.body();
                    if(ticketList.getSuccess()) {
                        if(ticketList.getData().size() != 0) {
                            mTripAdapter = new TicketListAdapter(ticketList, view.getContext());
                            rvTicket.setAdapter(mTripAdapter);
                        } else{
                            tvNone.setVisibility(View.VISIBLE);
                        }
                    } else{
                        tvNone.setText(ticketList.getMessage());
                        tvNone.setVisibility(View.VISIBLE);
                    }
                } else{
                    tvNone.setText(response.message());
                    tvNone.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ListResponse<Ticket>> call, Throwable t) {
                tvNone.setText(t.toString());
                tvNone.setVisibility(View.VISIBLE);
            }
        });
    }
}