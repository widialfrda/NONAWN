package com.example.nonawn.Databases;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {

    //variables
    SharedPreferences userSession;
    SharedPreferences.Editor editor;
    Context context;

    private static final String IS_LOGIN = "IsLoggedIn";

    public static final String KEY_PhoneNumber = "phone";

    public SessionManager (Context _context){
        context = _context;
        userSession = context.getSharedPreferences("userLoginSession", Context.MODE_PRIVATE);
        editor = userSession .edit();
    }

    public void createLoginSession(String phone){
        editor.putBoolean(IS_LOGIN,true);

        editor.putString(KEY_PhoneNumber, phone);

        editor.commit();
    }
    public HashMap<String, String> getUserDetailFromSession(){
        HashMap<String, String> userData = new HashMap<String, String>();

        userData.put(KEY_PhoneNumber,userSession.getString(KEY_PhoneNumber,null));

        return userData;
    }

    public boolean checkLogin(){
        if (userSession.getBoolean(IS_LOGIN, false)){
            return true;
        }
        else
            return false;
    }

    public void logoutUserFromSession(){
        editor.clear();
        editor.commit();
    }

}
