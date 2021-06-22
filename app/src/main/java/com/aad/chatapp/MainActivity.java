package com.aad.chatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private EditText username, email, password;
    private Button signUP;
    private TextView logIn;
    private TextInputLayout inputLayout;

    private boolean isSigningUp = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.passwrd);
        signUP = findViewById(R.id.signupbtn);
        logIn = findViewById(R.id.logintxt);
        inputLayout = findViewById(R.id.inputLayout);

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isSigningUp){

                    isSigningUp = false;
                    username.setVisibility(View.GONE);
                    inputLayout.setVisibility(View.GONE);
                    signUP.setText("Log In");
                    logIn.setText("Don`t have an account? Sign Up");

                } else {

                    isSigningUp = true;
                    inputLayout.setVisibility(View.VISIBLE);
                    username.setVisibility(View.VISIBLE);
                    signUP.setText("Sign Up");
                    logIn.setText("Already have an account? Log in");

                }

            }
        });

    }
}