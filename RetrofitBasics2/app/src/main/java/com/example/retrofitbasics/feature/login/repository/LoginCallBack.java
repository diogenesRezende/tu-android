package com.example.retrofitbasics.feature.login.repository;

import com.example.retrofitbasics.feature.login.model.User;

public interface LoginCallBack {
    void onSuccess(User user);

    void onError(Throwable error);
}
