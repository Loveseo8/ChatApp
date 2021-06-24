package com.aad.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MessageActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditText editText;
    private TextView textView;
    private ImageView imageView, imageViewSend;
    private ProgressBar progressBar;
    private ArrayList<Message> messages;

    String username, email, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        username = getIntent().getStringExtra("username");
        email = getIntent().getStringExtra("email");

        recyclerView = findViewById(R.id.recycler);
        editText = findViewById(R.id.edt);
        textView = findViewById(R.id.usernametoolbar);
        imageView = findViewById(R.id.imagetoolbar);
        progressBar = findViewById(R.id.progress_bar);
        imageViewSend = findViewById(R.id.send);

        textView.setText(username);

        messages = new ArrayList<>();

        setUpChat();

    }

    private void setUpChat(){

        FirebaseDatabase.getInstance().getReference("user/" + FirebaseAuth.getInstance().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                String myName = snapshot.getValue(User.class).getUsername();
                if(username.compareTo(myName) > 0){

                    id = myName + username;

                } else if (username.compareTo(myName) == 0){

                    id = username + myName;

                } else {

                    id = username + myName;

                }

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

    }

    private void attachMessageListener(String id){

        FirebaseDatabase.getInstance().getReference("messages/" + id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                messages.clear();

                for (DataSnapshot d : snapshot.getChildren()){

                    messages.add(d.getValue(Message.class));

                }

                recyclerView.scrollToPosition(messages.size() - 1);
                recyclerView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

    }

}