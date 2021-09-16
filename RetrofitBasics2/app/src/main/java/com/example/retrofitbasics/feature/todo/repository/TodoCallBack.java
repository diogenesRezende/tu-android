package com.example.retrofitbasics.feature.todo.repository;

import com.example.retrofitbasics.feature.todo.model.Todo;

import java.util.List;

public interface TodoCallBack {
    void onSuccess(List<Todo> todos);
    void onSuccess(Todo todo);
    void onError(Throwable error);
}
