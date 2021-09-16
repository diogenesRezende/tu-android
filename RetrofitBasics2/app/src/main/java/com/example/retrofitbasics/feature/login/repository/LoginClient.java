package com.example.retrofitbasics.feature.login.repository;

import com.example.retrofitbasics.core.config.ApiFactory;
import com.example.retrofitbasics.feature.login.model.User;

import retrofit2.Call;

public class LoginClient {

    private LoginAPI loginAPI;

    public LoginClient() {
        this.loginAPI = new ApiFactory().create().create(LoginAPI.class);
    }

    public Call<User> login(User user) {
        return this.loginAPI.login(user);
    }
}
