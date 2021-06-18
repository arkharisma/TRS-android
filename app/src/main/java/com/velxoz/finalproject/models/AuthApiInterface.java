package com.velxoz.finalproject.models;

import com.velxoz.finalproject.entity.auth.signin.SignInRequest;
import com.velxoz.finalproject.entity.auth.signin.SignInResponse;
import com.velxoz.finalproject.entity.auth.signup.SignUpRequest;
import com.velxoz.finalproject.entity.auth.signup.SignUpResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthApiInterface {
    @POST("auth")
    Call<SignInResponse> loginUser(@Body SignInRequest signInRequest);

    @POST("v1/user/signup")
    Call<SignUpResponse> registerUser(@Body SignUpRequest signUpRequest);
}
