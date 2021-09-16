package com.example.retrofitbasics.feature.todo.presentation.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitbasics.R;
import com.example.retrofitbasics.feature.todo.model.Todo;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoViewHolder> {

    private List<Todo> datasource;
    private ItemClickListener itemClickListener;

    public interface ItemClickListener {
        void onClickItem(Todo todo);
    }

    public TodoAdapter(List<Todo> todos, ItemClickListener itemClickListener) {
        this.datasource = todos;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public TodoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_item_view_holder, parent, false);
        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TodoViewHolder holder, int position) {
        holder.setupInfo(this.datasource.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return this.datasource.size();
    }
}


