package com.velxoz.finalproject.models;

import com.velxoz.finalproject.entity.ListResponse;
import com.velxoz.finalproject.entity.ObjectResponse;
import com.velxoz.finalproject.entity.booking.BookingRequest;
import com.velxoz.finalproject.entity.booking.BookingResponse;
import com.velxoz.finalproject.entity.ticket.Ticket;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TicketInterface {
    @POST("v1/reservation/bookticket")
    Call<ObjectResponse<BookingResponse>> bookTicket(@Body BookingRequest bookingRequest);

    @GET("v1/ticket/{id}")
    Call<ObjectResponse<Ticket>> ticketDetail(@Path("id") String id);

    @GET("v1/ticket/user")
    Call<ListResponse<Ticket>> ticketList();
}
