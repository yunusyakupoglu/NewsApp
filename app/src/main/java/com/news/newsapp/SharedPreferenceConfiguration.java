package com.news.newsapp;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceConfiguration {
    private SharedPreferences sharedPreferences;
    private Context context;

    public SharedPreferenceConfiguration(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(context.getResources().getString(R.string.login_preference),Context.MODE_PRIVATE);
    }

    public void loginStatusWrite(boolean status){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(context.getResources().getString(R.string.login_status_preference),status);
        editor.commit();
    }

    public boolean loginStatusRead(){
        boolean status = false;
        status = sharedPreferences.getBoolean(context.getResources().getString(R.string.login_status_preference),false);
        return status;
    }
}
