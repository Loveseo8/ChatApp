package com.aad.chatapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class DialogsAdapter extends RecyclerView.Adapter<DialogsAdapter.DialogHolder> {

    private ArrayList<User> users;
    private Context context;
    private onUserClickListener onUserClickListener;

    public DialogsAdapter(ArrayList<User> users, Context context, DialogsAdapter.onUserClickListener onUserClickListener) {
        this.users = users;
        this.context = context;
        this.onUserClickListener = onUserClickListener;
    }

    interface onUserClickListener{

        void onUserClicked(int position);

    }

    @NonNull
    @NotNull
    @Override
    public DialogHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.rv_item, parent, false);
        return new DialogHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull DialogsAdapter.DialogHolder holder, int position) {

        holder.username.setText(users.get(position).getUsername());
        Glide.with(context).load(users.get(position).getProfilePicture()).error(R.drawable.ic_baseline_account_circle_24).placeholder(R.drawable.ic_baseline_account_circle_24).into(holder.prfileImage);

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class DialogHolder extends RecyclerView.ViewHolder{

        TextView username;
        ImageView prfileImage;


        public DialogHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    onUserClickListener.onUserClicked(getAdapterPosition());

                }
            });
            username = itemView.findViewById(R.id.username);
            prfileImage = itemView.findViewById(R.id.image_profile);

        }
    }

}
