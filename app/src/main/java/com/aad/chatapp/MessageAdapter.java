package com.aad.chatapp;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageHolder> {

    @NonNull
    @NotNull
    @Override
    public MessageHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MessageAdapter.MessageHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MessageHolder extends RecyclerView.ViewHolder{


        public MessageHolder(@NonNull @NotNull View itemView) {
            super(itemView);
        }
    }

}
