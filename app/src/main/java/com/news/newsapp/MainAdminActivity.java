package com.news.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainAdminActivity extends AppCompatActivity {

    private SharedPreferenceConfiguration sharedPreferenceConfiguration;



    public static FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);


        fragmentManager = getSupportFragmentManager();
        if (findViewById(R.id.fragment_coverage)!=null){
            if(savedInstanceState != null){
                return;
            }

            fragmentManager.beginTransaction().add(R.id.fragment_coverage,new HomeFragment()).commit();
        }

        sharedPreferenceConfiguration = new SharedPreferenceConfiguration(getApplicationContext());


    }

    public void signout(View view){
        // cikis yapma kodları gelecek
        sharedPreferenceConfiguration.loginStatusWrite(false);
        Intent toLoginActivity = new Intent(MainAdminActivity.this,LoginActivity.class);
        startActivity(toLoginActivity);
        finish();
    }
}