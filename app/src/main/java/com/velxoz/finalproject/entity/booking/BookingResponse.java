package com.velxoz.finalproject.entity.booking;

import com.google.gson.annotations.SerializedName;

public class BookingResponse {
    @SerializedName("id")
    private String id;

    public BookingResponse() {
    }

    public BookingResponse(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
