package com.velxoz.finalproject.entity.ticket;

import com.google.gson.annotations.SerializedName;

public class TripSchedule {
    @SerializedName("id")
    private String id;
    @SerializedName("tripDate")
    private String tripDate;
    @SerializedName("availableSeats")
    private Integer availableSeats;
    @SerializedName("tripDetail")
    private TripDetail tripDetail;

    public TripSchedule() {
    }

    public TripSchedule(String id, String tripDate, Integer availableSeats, TripDetail tripDetail) {
        this.id = id;
        this.tripDate = tripDate;
        this.availableSeats = availableSeats;
        this.tripDetail = tripDetail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTripDate() {
        return tripDate;
    }

    public void setTripDate(String tripDate) {
        this.tripDate = tripDate;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }

    public TripDetail getTripDetail() {
        return tripDetail;
    }

    public void setTripDetail(TripDetail tripDetail) {
        this.tripDetail = tripDetail;
    }
}
