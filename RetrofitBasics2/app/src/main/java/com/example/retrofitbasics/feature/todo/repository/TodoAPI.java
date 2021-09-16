package com.example.retrofitbasics.feature.todo.repository;

import com.example.retrofitbasics.feature.todo.model.Todo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TodoAPI {

    @GET("todos")
    Call<List<Todo>> getTodos();

    @GET("todos/{id}")
    Call<Todo> getTodosById(@Path("id") int id);

    @POST("todos")
    Call<Todo> createTodo(@Body Todo todo);

    @DELETE("todos/{id}")
    Call<Todo> deleteTodo(@Path("id")int id);

    @PUT("todos/{id}")
    Call<Todo> updateTodo(@Path("id")int id, @Body Todo todo);
}
