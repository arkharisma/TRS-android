package com.velxoz.finalproject.models;

import com.velxoz.finalproject.entity.stop.GetResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface StopApiInterface {
    @GET("v1/reservation/stops")
    Call<GetResponse> getAllStops();
}
