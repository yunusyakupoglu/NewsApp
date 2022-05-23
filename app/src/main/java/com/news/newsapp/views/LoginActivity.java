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
import com.google.firebase.auth.FirebaseUser;
import com.news.newsapp.R;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    TextInputEditText textLogEmail, textLogPassword;
    TextView tvRegisterHere;
    Button buttonLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initComponents();
        registerEventHandlers();
    }
    private void initComponents(){
        textLogEmail = findViewById(R.id.textLogEmail);
        textLogPassword = findViewById(R.id.textLogPassword);
        tvRegisterHere = findViewById(R.id.textViewregisterHere);
        buttonLogin = findViewById(R.id.buttonLogin);
        mAuth = FirebaseAuth.getInstance();
    }
    private void registerEventHandlers(){
        buttonLogin.setOnClickListener(v -> {
            loginUser();
        });
        tvRegisterHere.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
        });
    }
    private void loginUser(){
        String email = textLogEmail.getText().toString();
        String password = textLogPassword.getText().toString();
        if (TextUtils.isEmpty(email)){
            textLogEmail.setError("Email cannot be empty");
            textLogEmail.requestFocus();
        } else if (TextUtils.isEmpty(password)){
            textLogPassword.setError("Password cannot be empty");
            textLogPassword.requestFocus();
        } else {
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(LoginActivity.this, "User logged in successfully.", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this,NavigationActivity.class));
                    } else {
                        Toast.makeText(LoginActivity.this, "Log in Error: "+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }
    private void updateUI(FirebaseUser currentUser) {
    }
}