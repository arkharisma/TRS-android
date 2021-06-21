package com.velxoz.finalproject.models;

import com.velxoz.finalproject.entity.ListResponse;
import com.velxoz.finalproject.entity.ObjectResponse;
import com.velxoz.finalproject.entity.tripschedule.TripScheduleResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TripInterface {
    @GET("v1/reservation/tripsbyallindicator")
    Call<ListResponse<TripScheduleResponse>> getTripsAllIndicator(@Query("sourceStop") String sourceStop, @Query("destStop") String destStop, @Query("date") String date);

    @GET("v1/reservation/tripsbystops")
    Call<ListResponse<TripScheduleResponse>> getTripsByStops(@Query("sourceStop") String sourceStop, @Query("destStop") String destStop);

    @GET("v1/reservation/trip/detail/{id}")
    Call<ObjectResponse<TripScheduleResponse>> getTripDetail(@Path("id") String id);
}
