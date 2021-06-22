package com.velxoz.finalproject.entity.booking;

import com.google.gson.annotations.SerializedName;

public class BookingRequest {
    @SerializedName("cancellable")
    private Boolean cancellable;
    @SerializedName("journey_date")
    private String journeyDate;
    @SerializedName("passenger")
    private String passenger;
    @SerializedName("seat_number")
    private Integer seatNumber;
    @SerializedName("trip_schedule")
    private String tripSchedule;

    public BookingRequest() {
    }

    public BookingRequest(Boolean cancellable, String journeyDate, String passenger, Integer seatNumber, String tripSchedule) {
        this.cancellable = cancellable;
        this.journeyDate = journeyDate;
        this.passenger = passenger;
        this.seatNumber = seatNumber;
        this.tripSchedule = tripSchedule;
    }

    public Boolean getCancellable() {
        return cancellable;
    }

    public void setCancellable(Boolean cancellable) {
        this.cancellable = cancellable;
    }

    public String getJourneyDate() {
        return journeyDate;
    }

    public void setJourneyDate(String journeyDate) {
        this.journeyDate = journeyDate;
    }

    public String getPassenger() {
        return passenger;
    }

    public void setPassenger(String passenger) {
        this.passenger = passenger;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getTripSchedule() {
        return tripSchedule;
    }

    public void setTripSchedule(String tripSchedule) {
        this.tripSchedule = tripSchedule;
    }
}
