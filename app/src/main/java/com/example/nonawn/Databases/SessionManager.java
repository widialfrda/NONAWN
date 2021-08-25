package com.example.nonawn.Databases;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {

    //variables
    SharedPreferences userSession;
    SharedPreferences.Editor editor;

    public static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_PhoneNumber = "phone";

    public SessionManager (Context context){
        userSession = context.getSharedPreferences("userLoginSession", Context.MODE_PRIVATE);
        editor = userSession .edit();
    }

    public void saveSPString(String key, String value){
        editor.putString(key, value);
        editor.commit();
    }
    public void saveSPBoolean(String key, boolean value){
        editor.putBoolean(key, value);
        editor.commit();
    }

    public String getSPNo(){
        return userSession.getString(KEY_PhoneNumber,"");
    }

    public boolean getSPLoggedIn(){
        return userSession.getBoolean(IS_LOGIN,false);
    }

    public void logoutUserFromSession(){

        editor.clear();
        editor.commit();
    }

}
