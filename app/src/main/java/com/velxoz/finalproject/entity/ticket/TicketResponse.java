package com.velxoz.finalproject.entity.ticket;

import com.google.gson.annotations.SerializedName;

public class TicketResponse {
    @SerializedName("id")
    private String id;
    @SerializedName("cancellable")
    private Boolean cancellable;
    @SerializedName("journey_date")
    private String journeyDate;
    @SerializedName("seat_number")
    private Integer seatNumber;
    @SerializedName("passenger")
    private String passenger;
    @SerializedName("trip_schedule")
    private String tripSchedule;

    public TicketResponse() {
    }

    public TicketResponse(String id, Boolean cancellable, String journeyDate, Integer seatNumber, String passenger, String tripSchedule) {
        this.id = id;
        this.cancellable = cancellable;
        this.journeyDate = journeyDate;
        this.seatNumber = seatNumber;
        this.passenger = passenger;
        this.tripSchedule = tripSchedule;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getPassenger() {
        return passenger;
    }

    public void setPassenger(String passenger) {
        this.passenger = passenger;
    }

    public String getTripSchedule() {
        return tripSchedule;
    }

    public void setTripSchedule(String tripSchedule) {
        this.tripSchedule = tripSchedule;
    }
}
