package com.sadmi.project.model;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by s on 14/05/17.
 */

public class UserSharedPref {

    private Context context;

    public UserSharedPref(Context context) {
        this.context = context;
    }

    public boolean saveConnectedUser(String usr){

        SharedPreferences pref = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("connected",true);
        editor.putString("username",usr);

        return editor.commit();
    }

    public boolean isConnected(){
        SharedPreferences pref = context.getSharedPreferences("user",Context.MODE_PRIVATE);
        return pref.getBoolean("connected",true);
    }

    public String getConnectedUser(){
        SharedPreferences pref = context.getSharedPreferences("user",Context.MODE_PRIVATE);
        return pref.getString("username",null);
    }

    public boolean disconnectUser()
    {
        SharedPreferences pref = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("connected",false);
        return editor.commit();
    }
}
