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
import com.velxoz.finalproject.entity.tripschedule.TripScheduleResponse;
import com.velxoz.finalproject.views.BookingActivity;

import org.jetbrains.annotations.NotNull;

public class TripScheduleListAdapter extends RecyclerView.Adapter<TripScheduleListAdapter.MyViewHolder> {

    ListResponse<TripScheduleResponse> tripScheduleList;
    private Context context;

    public TripScheduleListAdapter(ListResponse<TripScheduleResponse> tripScheduleList, Context context) {
        this.tripScheduleList = tripScheduleList;
        this.context = context;
    }

    @Override
    public TripScheduleListAdapter.@NotNull MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_trip_schedule, parent, false);
        return new MyViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull TripScheduleListAdapter.MyViewHolder holder, int position) {
        holder.tvAgencyName.setText(tripScheduleList.getData().get(position).getAgencyName());
        holder.tvBusCode.setText(tripScheduleList.getData().get(position).getBusCode());
        holder.tvSourceStop.setText(tripScheduleList.getData().get(position).getSourceStop());
        holder.tvDestStop.setText(tripScheduleList.getData().get(position).getDestStop());
        holder.tvTanggal.setText(tripScheduleList.getData().get(position).getTripDate());
        holder.tvTime.setText(tripScheduleList.getData().get(position).getJourneyTime());
        holder.tvHarga.setText(Integer.toString(tripScheduleList.getData().get(position).getFare()));

        holder.itemView.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("id_trip", tripScheduleList.getData().get(position).getId());
            Intent i = new Intent(v.getContext(), BookingActivity.class);
            v.getContext().startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return tripScheduleList.getData().size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView tvAgencyName, tvBusCode, tvSourceStop, tvDestStop, tvTanggal, tvTime, tvHarga;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            tvAgencyName = itemView.findViewById(R.id.tvAgencyName);
            tvBusCode = itemView.findViewById(R.id.tvBusCode);
            tvSourceStop = itemView.findViewById(R.id.tvSourceStop);
            tvDestStop = itemView.findViewById(R.id.tvDestStop);
            tvTanggal = itemView.findViewById(R.id.tvTanggal);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvHarga = itemView.findViewById(R.id.tvHarga);
        }
    }
}
