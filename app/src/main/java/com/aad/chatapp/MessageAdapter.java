package com.aad.chatapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageHolder> {

    private ArrayList<Message> messages;
    private String senderImage, reciverImage;
    private Context context;

    public MessageAdapter(ArrayList<Message> messages, String senderImage, String reciverImage, Context context) {
        this.messages = messages;
        this.senderImage = senderImage;
        this.reciverImage = reciverImage;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public MessageHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.message_holder, parent, false);
        return new MessageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MessageAdapter.MessageHolder holder, int position) {

        holder.message.setText(messages.get(position).getContent());
        ConstraintLayout constraintLayout = holder.constraintLayout;
        if(messages.get(position).getSender() == FirebaseAuth.getInstance().getCurrentUser().getEmail()){

            Glide.with(context).load(senderImage).error(R.drawable.ic_baseline_account_circle_24).placeholder(R.drawable.ic_baseline_account_circle_24).into(holder.profileImage);

            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.clear(R.id.cardview, ConstraintSet.LEFT);
            constraintSet.clear(R.id.msg, ConstraintSet.LEFT);
            constraintSet.connect(R.id.cardview, ConstraintSet.RIGHT, R.id.idll, ConstraintSet.RIGHT, 0);
            constraintSet.connect(R.id.msg, ConstraintSet.RIGHT, R.id.cardview, ConstraintSet.LEFT, 0);
            constraintSet.applyTo(constraintLayout);

        } else {
            Glide.with(context).load(reciverImage).error(R.drawable.ic_baseline_account_circle_24).placeholder(R.drawable.ic_baseline_account_circle_24).into(holder.profileImage);

            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.clear(R.id.cardview, ConstraintSet.RIGHT);
            constraintSet.clear(R.id.msg, ConstraintSet.RIGHT);
            constraintSet.connect(R.id.cardview, ConstraintSet.LEFT, R.id.idll, ConstraintSet.LEFT, 0);
            constraintSet.connect(R.id.msg, ConstraintSet.LEFT, R.id.cardview, ConstraintSet.RIGHT, 0);
            constraintSet.applyTo(constraintLayout);

        }

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    class MessageHolder extends RecyclerView.ViewHolder{

        ConstraintLayout constraintLayout;
        TextView message;
        ImageView profileImage;


        public MessageHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            constraintLayout = itemView.findViewById(R.id.idll);
            message = itemView.findViewById(R.id.msg);
            profileImage = itemView.findViewById(R.id.smallpp);
        }
    }

}
