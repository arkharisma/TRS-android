package com.velxoz.finalproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.velxoz.finalproject.R;
import com.velxoz.finalproject.entity.ListResponse;
import com.velxoz.finalproject.entity.ticket.Ticket;
import com.velxoz.finalproject.util.Currency;
import com.velxoz.finalproject.views.BookingActivity;

import org.jetbrains.annotations.NotNull;

public class TicketListAdapter extends RecyclerView.Adapter<TicketListAdapter.MyViewHolder> {

    ListResponse<Ticket> ticketList;
    private Context context;

    public TicketListAdapter(ListResponse<Ticket> ticketList, Context context) {
        this.ticketList = ticketList;
        this.context = context;
    }

    @Override
    public TicketListAdapter.@NotNull MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_ticket_item, parent, false);
        return new MyViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull TicketListAdapter.MyViewHolder holder, int position) {
        holder.tvAgencyName.setText(ticketList.getData().get(position).getTripSchedule().getTripDetail().getAgency().getName());
        holder.tvBusCode.setText(ticketList.getData().get(position).getTripSchedule().getTripDetail().getBus().getCode());
        holder.tvSourceStop.setText(ticketList.getData().get(position).getTripSchedule().getTripDetail().getSourceStop().getName());
        holder.tvDestStop.setText(ticketList.getData().get(position).getTripSchedule().getTripDetail().getDestStop().getName());
        holder.tvTanggal.setText(ticketList.getData().get(position).getJourneyDate());
        holder.tvTime.setText(ticketList.getData().get(position).getTripSchedule().getTripDetail().getJourneyTime());
    }

    @Override
    public int getItemCount() {
        return ticketList.getData().size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView tvAgencyName, tvBusCode, tvSourceStop, tvDestStop, tvTanggal, tvTime;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            tvAgencyName = itemView.findViewById(R.id.tvAgencyName);
            tvBusCode = itemView.findViewById(R.id.tvBusCode);
            tvSourceStop = itemView.findViewById(R.id.tvSourceStop);
            tvDestStop = itemView.findViewById(R.id.tvDestStop);
            tvTanggal = itemView.findViewById(R.id.tvTanggal);
            tvTime = itemView.findViewById(R.id.tvTime);
        }
    }
}
