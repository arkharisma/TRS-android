package com.velxoz.finalproject.entity.tripschedule;

import com.google.gson.annotations.SerializedName;

public class TripScheduleResponse {
    @SerializedName("id")
    private String id;
    @SerializedName("trip_date")
    private String tripDate;
    @SerializedName("available_seats")
    private String availableSeats;
    @SerializedName("fare")
    private Integer fare;
    @SerializedName("source_stop")
    private String sourceStop;
    @SerializedName("dest_stop")
    private String destStop;
    @SerializedName("journey_time")
    private String journeyTime;
    @SerializedName("agency_name")
    private String agencyName;
    @SerializedName("bus_code")
    private String busCode;

    public TripScheduleResponse() {
    }

    public TripScheduleResponse(String id, String tripDate, String availableSeats, Integer fare, String sourceStop, String destStop, String journeyTime, String agencyName, String busCode) {
        this.id = id;
        this.tripDate = tripDate;
        this.availableSeats = availableSeats;
        this.fare = fare;
        this.sourceStop = sourceStop;
        this.destStop = destStop;
        this.journeyTime = journeyTime;
        this.agencyName = agencyName;
        this.busCode = busCode;
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

    public String getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(String availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Integer getFare() {
        return fare;
    }

    public void setFare(Integer fare) {
        this.fare = fare;
    }

    public String getSourceStop() {
        return sourceStop;
    }

    public void setSourceStop(String sourceStop) {
        this.sourceStop = sourceStop;
    }

    public String getDestStop() {
        return destStop;
    }

    public void setDestStop(String destStop) {
        this.destStop = destStop;
    }

    public String getJourneyTime() {
        return journeyTime;
    }

    public void setJourneyTime(String journeyTime) {
        this.journeyTime = journeyTime;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getBusCode() {
        return busCode;
    }

    public void setBusCode(String busCode) {
        this.busCode = busCode;
    }
}
