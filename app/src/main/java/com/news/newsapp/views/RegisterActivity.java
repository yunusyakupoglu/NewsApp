package com.news.newsapp.views;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.news.newsapp.R;

public class RegisterActivity extends AppCompatActivity {
    TextInputEditText textRegEmail, textRegPassword;
    Button buttonRegister;
    TextView tvLoginHere;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initComponents();
        registerEventHandlers();
    }

    private void initComponents(){
        textRegEmail = findViewById(R.id.textRegEmail);
        textRegPassword = findViewById(R.id.textRegPassword);
        tvLoginHere = findViewById(R.id.textViewLoginHere);
        buttonRegister = findViewById(R.id.buttonRegister);
        mAuth = FirebaseAuth.getInstance();
    }

    private void registerEventHandlers(){
        buttonRegister.setOnClickListener(v -> {
            createUser();
        });

        tvLoginHere.setOnClickListener(v -> {
            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
        });
    }

    private void createUser(){
        String email = textRegEmail.getText().toString();
        String password = textRegPassword.getText().toString();

        if (TextUtils.isEmpty(email)){
            textRegEmail.setError("Email cannot be empty");
            textRegEmail.requestFocus();
        } else if (TextUtils.isEmpty(password)){
            textRegPassword.setError("Password cannot be empty");
            textRegPassword.requestFocus();
        } else {
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this, "User registered successfully.", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                    } else {
                        Toast.makeText(RegisterActivity.this, "Registration Error: "+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}