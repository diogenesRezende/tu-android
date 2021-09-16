package com.example.retrofitbasics.feature.todo.repository;

import com.example.retrofitbasics.core.config.ApiFactory;
import com.example.retrofitbasics.feature.todo.model.Todo;
import java.util.List;
import retrofit2.Call;

public class TodoClient {

    public TodoAPI api;

    public TodoClient() {
        this.api = new ApiFactory().create().create(TodoAPI.class);
    }

    public Call<List<Todo>> getTodos() {
        return this.api.getTodos();
    }

    public Call<Todo> getTodoById(int id) {
        return this.api.getTodosById(id);
    }

    public Call<Todo> createTodo(Todo todo) {
        return this.api.createTodo(todo);
    }

    public Call<Todo> deleteTodo(int id) {
        return this.api.deleteTodo(id);
    }

    public Call<Todo> updateTodo(int id, Todo todo) {
        return this.api.updateTodo(id, todo);
    }
}
