package com.example.retrofitbasics.feature.todo.presentation.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.retrofitbasics.R;
import com.example.retrofitbasics.feature.login.presentation.activity.LoginActivity;
import com.example.retrofitbasics.feature.todo.presentation.adapter.TodoAdapter;
import com.example.retrofitbasics.feature.todo.repository.TodoCallBack;
import com.example.retrofitbasics.feature.todo.presentation.viewmodel.TodoViewModel;
import com.example.retrofitbasics.feature.todo.model.Todo;
import com.google.gson.Gson;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TodoCallBack {

    private static final String TAG = MainActivity.class.getCanonicalName();

    private TextView tvText;
    private RecyclerView rvListOfTodos;
    private TodoViewModel viewModel;

    public static Intent newInstance(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvText = findViewById(R.id.tvText);
        rvListOfTodos = findViewById(R.id.rvListOfTodos);
        viewModel =  new TodoViewModel();
    }

    @Override
    protected void onResume() {
        super.onResume();

        viewModel.getTodos(this);
    }

    @Override
    public void onSuccess(List<Todo> todos) {
        String todosStr = new Gson().toJson(todos);
        Log.d(TAG, todosStr);

        rvListOfTodos.setAdapter(new TodoAdapter(todos, todo -> {
            startActivity(AddTodoActivity.newInstance(this, todo));
        }));
    }

    @Override
    public void onSuccess(Todo todo) {
        String todoStr = new Gson().toJson(todo);

        Log.d(TAG, todoStr);
        tvText.setText(todoStr);
    }

    @Override
    public void onError(Throwable error) {
        Log.e(TAG, "", error);

        tvText.setText(error.getMessage());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.todo_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.new_todo) {
            startActivity(AddTodoActivity.newInstance(this));
        }

        return super.onOptionsItemSelected(item);
    }
}