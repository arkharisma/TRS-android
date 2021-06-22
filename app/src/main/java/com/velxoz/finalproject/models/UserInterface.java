package com.velxoz.finalproject.models;

import com.velxoz.finalproject.entity.ObjectResponse;
import com.velxoz.finalproject.entity.booking.BookingRequest;
import com.velxoz.finalproject.entity.booking.BookingResponse;
import com.velxoz.finalproject.entity.user.ChangePasswordRequest;
import com.velxoz.finalproject.entity.user.ChangeUserRequest;
import com.velxoz.finalproject.entity.user.GetUserResponse;
import com.velxoz.finalproject.entity.user.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserInterface {
    @PUT("v1/user/changepassword")
    Call<ObjectResponse<UserResponse>> changePassword(@Body ChangePasswordRequest changePasswordRequest);

    @GET("/v1/user/{id}")
    Call<ObjectResponse<GetUserResponse>> getDataUser(@Path("id") String id);

    @PUT("v1/user/")
    Call<ObjectResponse<UserResponse>> updateUser(@Body ChangeUserRequest changeUserRequest);
}
