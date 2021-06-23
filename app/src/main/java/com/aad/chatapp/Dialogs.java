package com.aad.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Dialogs extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<User> users;
    private ProgressBar progressBar;
    private DialogsAdapter dialogsAdapter;
    DialogsAdapter.onUserClickListener onUserClickListener;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogs);

        recyclerView = findViewById(R.id.recycler);
        progressBar = findViewById(R.id.progress_bar);
        users = new ArrayList<>();
        swipeRefreshLayout = findViewById(R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                getUsers();
                swipeRefreshLayout.setRefreshing(false);

            }
        });

        onUserClickListener = new DialogsAdapter.onUserClickListener() {
            @Override
            public void onUserClicked(int position) {

            }
        };

        getUsers();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.profile, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.menu_item_profile){

            startActivity(new Intent(Dialogs.this, Profile.class));

        }
        return super.onOptionsItemSelected(item);
    }

    private void getUsers(){

        users.clear();

        FirebaseDatabase.getInstance().getReference("user").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                for (DataSnapshot ds  : snapshot.getChildren()){

                    users.add(ds.getValue(User.class));

                }

                dialogsAdapter = new DialogsAdapter(users,Dialogs.this, onUserClickListener);
                recyclerView.setLayoutManager(new LinearLayoutManager(Dialogs.this));
                recyclerView.setAdapter(dialogsAdapter);
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

    }


}