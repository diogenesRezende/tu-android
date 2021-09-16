package com.example.retrofitbasics.feature.login.presentation.viewmodel;

import com.example.retrofitbasics.feature.login.model.User;
import com.example.retrofitbasics.feature.login.repository.LoginCallBack;
import com.example.retrofitbasics.feature.login.repository.LoginClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel {

    private LoginClient client;

    public LoginViewModel() {
        this.client = new LoginClient();
    }

    public void login(String email, String password, LoginCallBack callBack) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        client.login(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                callBack.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                callBack.onError(t);
            }
        });
    }
}
