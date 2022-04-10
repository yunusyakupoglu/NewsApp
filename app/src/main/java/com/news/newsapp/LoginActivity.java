package com.news.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferenceConfiguration sharedPreferenceConfiguration;
    private TextInputEditText UsernameTextInputEditText,PasswordTextInputEditText;
    private FloatingActionButton fabActivityLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fabActivityLogin = findViewById(R.id.fab_activity_login);
        UsernameTextInputEditText = findViewById(R.id.TextInputEditTextUsername);
        PasswordTextInputEditText = findViewById(R.id.TextInputEditTextPassword);

        sharedPreferenceConfiguration = new SharedPreferenceConfiguration(getApplicationContext());
        fabActivityLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toMainActivity = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(toMainActivity);
            }
        });


        if (sharedPreferenceConfiguration.loginStatusRead()){
            Intent intent = new Intent(LoginActivity.this,MainAdminActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void login(View view){
        //Butona tıklandığında giriş yapacak

        String username,password;
        username = UsernameTextInputEditText.getText().toString();
        password = PasswordTextInputEditText.getText().toString();

        if (username.equals(getResources().getString(R.string.username))
                && password.equals(getResources().getString(R.string.password)))
        {
            Toast.makeText(this, "Giriş başarılı...", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this,MainAdminActivity.class);
            startActivity(intent);

            sharedPreferenceConfiguration.loginStatusWrite(true);
            finish();
        }
        else
        {
            Toast.makeText(this, "Bilgilerinizi kontrol ediniz...", Toast.LENGTH_SHORT).show();
            UsernameTextInputEditText.setText("");
            PasswordTextInputEditText.setText("");
        }
    }
}