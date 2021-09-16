package com.example.retrofitbasics.feature.todo.presentation.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitbasics.R;
import com.example.retrofitbasics.feature.todo.model.Todo;

public class TodoViewHolder extends RecyclerView.ViewHolder {

    private final TextView tvDescription;
    private final TextView tvOwner;

    public TodoViewHolder(View itemView) {
        super(itemView);

        tvDescription = itemView.findViewById(R.id.tvDescription);
        tvOwner = itemView.findViewById(R.id.tvOwner);
    }

    public void setupInfo(Todo todo, TodoAdapter.ItemClickListener itemClickListener) {
        itemView.setOnClickListener(view -> itemClickListener.onClickItem(todo));
        tvDescription.setText(todo.getDescription());
        tvOwner.setText(todo.getOwner());
    }
}
