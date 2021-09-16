package com.example.retrofitbasics.feature.todo.presentation.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.retrofitbasics.R;
import com.example.retrofitbasics.feature.todo.model.Todo;
import com.example.retrofitbasics.feature.todo.presentation.viewmodel.TodoViewModel;
import com.example.retrofitbasics.feature.todo.repository.TodoCallBack;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class AddTodoActivity extends AppCompatActivity implements TodoCallBack {

    private static final String TAG = AddTodoActivity.class.getCanonicalName();
    private static final String ADD_TODO_ID = "ADD_TODO_ID";

    private TodoViewModel viewModel;

    private EditText etDescription;
    private EditText etOwner;
    private EditText etAssignedTo;
    private CheckBox cbCompleted;

    private Todo todo;

    public static Intent newInstance(Context context) {
        return new Intent(context, AddTodoActivity.class);
    }

    public static Intent newInstance(Context context, Todo todo) {
        Intent intent = new Intent(context, AddTodoActivity.class);
        intent.putExtra(ADD_TODO_ID, todo);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        viewModel = new TodoViewModel();

        etDescription = findViewById(R.id.etDescription);
        etOwner = findViewById(R.id.etOwner);
        etAssignedTo = findViewById(R.id.etAssignedTo);
        cbCompleted = findViewById(R.id.cbCompleted);
        Button btAddTodo = findViewById(R.id.btAddTodo);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        todo = (Todo) getIntent().getSerializableExtra(ADD_TODO_ID);

        cbCompleted.setOnCheckedChangeListener((buttonView, isChecked) -> {
            Log.d(TAG, "setOnCheckedChangeListener: " + isChecked);
        });

        btAddTodo.setOnClickListener(view -> createTodo());
    }

    private void createTodo() {
        Todo todo = new Todo();
        todo.setDescription(etDescription.getText().toString());
        todo.setOwner(etOwner.getText().toString());
        todo.setAssignedTo(etAssignedTo.getText().toString());
        todo.setCompleted(cbCompleted.isChecked());
        todo.setCreatedAt(new Date());
        todo.setUpdatedAt(new Date());

        viewModel.createTodo(todo, this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (todo != null) {
            viewModel.getTodoById(todo.getId(), this);
        }
    }

    @Override
    public void onSuccess(List<Todo> todos) {
        String todosStr = new Gson().toJson(todos);
        Log.d(TAG, todosStr);
    }

    @Override
    public void onSuccess(Todo todo) {
        String todoStr = new Gson().toJson(todo);

        this.todo = todo;

        View contextView = findViewById(android.R.id.content);
        Snackbar.make(contextView, R.string.add_todo_success_label, Snackbar.LENGTH_SHORT).show();
        finish();
        Log.d(TAG, todoStr);
    }

    @Override
    public void onError(Throwable error) {
        Log.e(TAG, "", error);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }
}