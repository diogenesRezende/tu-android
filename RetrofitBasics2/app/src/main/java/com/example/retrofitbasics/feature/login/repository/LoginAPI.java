package com.example.retrofitbasics.feature.login.repository;

import com.example.retrofitbasics.feature.login.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginAPI {

    @POST("login")
    Call<User> login(@Body User user);
}
