package com.velxoz.finalproject.util.session;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.velxoz.finalproject.views.auth.LoginActivity;

import java.util.HashMap;

public class MainSession {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "User";
    private static final String IS_LOGIN = "IsLoggedIn";

    public static final String KEY_ID = "_id";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_FIRST_NAME = "first_name";
    public static final String KEY_LAST_NAME = "last_name";
    public static final String KEY_MOBILE_NUMBER = "mobile_number";
    public static final String KEY_TOKEN = "token";

    public MainSession(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void createLoginSession(String _id, String username, String first_name, String last_name, String mobile_number, String token){
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_ID, _id);
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_FIRST_NAME, first_name);
        editor.putString(KEY_LAST_NAME, last_name);
        editor.putString(KEY_MOBILE_NUMBER, mobile_number);
        editor.putString(KEY_TOKEN, token);
        editor.commit();
    }

    public void checkLogin(){
        if(!this.isLoggedIn()){
            Intent i = new Intent(context, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }

    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<>();

        user.put(KEY_USERNAME, sharedPreferences.getString(KEY_USERNAME, null));
        user.put(KEY_ID, sharedPreferences.getString(KEY_ID, null));
        user.put(KEY_FIRST_NAME, sharedPreferences.getString(KEY_FIRST_NAME, null));
        user.put(KEY_LAST_NAME, sharedPreferences.getString(KEY_LAST_NAME, null));
        user.put(KEY_MOBILE_NUMBER, sharedPreferences.getString(KEY_MOBILE_NUMBER, null));
        user.put(KEY_TOKEN, sharedPreferences.getString(KEY_TOKEN, null));

        return user;
    }

    public void logoutUser(){
        editor.clear();
        editor.commit();

        Intent i = new Intent(context, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(i);
    }

    public boolean isLoggedIn(){
        return sharedPreferences.getBoolean(IS_LOGIN, false);
    }
}
