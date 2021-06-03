package com.example.nonawn.Common.SignUpLogin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;

import com.example.nonawn.R;

public class RetailerWelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_retailer_welcome_screen);
    }

    public void goto_login(View view) {

        Intent intent = new Intent(getApplicationContext(),Login.class);

        Pair[] pairs = new Pair[1];

        pairs [0] = new Pair<View,String>(findViewById(R.id.button_login),"trans_login");

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            ActivityOptions options= ActivityOptions.makeSceneTransitionAnimation(RetailerWelcomeScreen.this,pairs);
            startActivity(intent,options.toBundle());
        }
        else{
            startActivity(intent);
        }

    }

    public void goto_signup(View view) {
        Intent intent = new Intent(getApplicationContext(),Signup.class);

        Pair[] pairs = new Pair[1];

        pairs [0] = new Pair<View,String>(findViewById(R.id.button_signup),"trans_signup");

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            ActivityOptions options= ActivityOptions.makeSceneTransitionAnimation(RetailerWelcomeScreen.this,pairs);
            startActivity(intent,options.toBundle());
        }
        else{
            startActivity(intent);
        }

    }
}