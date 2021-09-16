package com.example.retrofitbasics.feature.todo.presentation.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.retrofitbasics.feature.todo.repository.TodoCallBack;
import com.example.retrofitbasics.feature.todo.repository.TodoClient;
import com.example.retrofitbasics.feature.todo.model.Todo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TodoViewModel extends ViewModel {

    private final TodoClient client;

    public TodoViewModel() {
        client = new TodoClient();
    }

    public void getTodos(TodoCallBack callBack) {
        client.getTodos().enqueue(new Callback<List<Todo>>() {
            @Override
            public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
                callBack.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Todo>> call, Throwable t) {
                callBack.onError(t);
            }
        });
    }

    public void getTodoById(int id, TodoCallBack callBack) {
        client.getTodoById(id).enqueue(new Callback<Todo>() {
            @Override
            public void onResponse(Call<Todo> call, Response<Todo> response) {
                callBack.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Todo> call, Throwable t) {
                callBack.onError(t);
            }
        });
    }

    public void createTodo(Todo todo, TodoCallBack callBack){
        client.createTodo(todo).enqueue(new Callback<Todo>() {
            @Override
            public void onResponse(Call<Todo> call, Response<Todo> response) {
                callBack.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Todo> call, Throwable t) {
                callBack.onError(t);
            }
        });
    }

    public void deleteTodo(int id, TodoCallBack callBack){
        client.deleteTodo(id).enqueue(new Callback<Todo>() {
            @Override
            public void onResponse(Call<Todo> call, Response<Todo> response) {
                callBack.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Todo> call, Throwable t) {
                callBack.onError(t);
            }
        });
    }

    public void updateTodo(int id, Todo todo, TodoCallBack callBack){
        client.updateTodo(id, todo).enqueue(new Callback<Todo>() {
            @Override
            public void onResponse(Call<Todo> call, Response<Todo> response) {
                callBack.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Todo> call, Throwable t) {
                callBack.onError(t);
            }
        });
    }
}
