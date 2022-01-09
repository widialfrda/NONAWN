package com.example.nonawn.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nonawn.Common.SignUpLogin.RetailerWelcomeScreen;
import com.example.nonawn.R;

public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_TIMER = 5000;

    //variables
    ImageView logo_nonawn;
    TextView slogan_nonawn;

    //Animations
    Animation sideAnim, bottomAnim;

    SharedPreferences onBoardingScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        //Hooks
        logo_nonawn = findViewById(R.id.logo);
        slogan_nonawn = findViewById(R.id.slogan);

        //Animations
        sideAnim = AnimationUtils.loadAnimation(this, R.anim.side_anim);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.botttom_anim);

        //set Animation
        logo_nonawn.setAnimation(sideAnim);
        slogan_nonawn.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){

                onBoardingScreen = getSharedPreferences("onBoardingScreen", MODE_PRIVATE);
                boolean isFirstTime = onBoardingScreen.getBoolean("firsTime", true);

                if(isFirstTime) {

                    SharedPreferences.Editor editor = onBoardingScreen.edit();
                    editor.putBoolean("firstTime", false);
                    editor.commit();
                    Intent intent = new Intent(getApplicationContext(), OnBoarding.class);
                    startActivity(intent);
                    finish();


                }
                else {
                    Intent intent = new Intent(getApplicationContext(), RetailerWelcomeScreen.class);
                    startActivity(intent);
                    finish();
                }
            }
        },SPLASH_TIMER);
    }
}