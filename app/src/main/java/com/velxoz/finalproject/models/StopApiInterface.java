package com.velxoz.finalproject.models;

import com.velxoz.finalproject.entity.ListResponse;
import com.velxoz.finalproject.entity.stop.DataGetResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface StopApiInterface {
    @GET("v1/reservation/stops")
    Call<ListResponse<DataGetResponse>> getAllStops();
}
