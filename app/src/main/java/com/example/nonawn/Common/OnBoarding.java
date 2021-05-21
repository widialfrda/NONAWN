package com.example.nonawn.Common;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nonawn.Common.SignUpLogin.RetailerWelcomeScreen;
import com.example.nonawn.HelperClasses.SliderAdapter;
import com.example.nonawn.R;
import com.example.nonawn.User.UserDashboard;

import static androidx.core.text.HtmlCompat.fromHtml;

public class OnBoarding extends AppCompatActivity {

    //Variables
    ViewPager viewPager;
    LinearLayout dotsLayout;

    SliderAdapter sliderAdapter;
    TextView[] dots;
    Button LetsGo;
    Animation animation;
    int currentPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_on_boarding);

        //Hooks
        viewPager = findViewById(R.id.slider);
        dotsLayout = findViewById(R.id.dots);
        LetsGo = findViewById(R.id.lets_go);

        //Call adapter
        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);

        addDots(0);
        viewPager.addOnPageChangeListener(changeListener);

    }


    public void skip(View view){
        startActivity(new Intent(getApplicationContext(),RetailerWelcomeScreen.class));
        finish();
    }

    public void letsgo(View view) {
        startActivity(new Intent(getApplicationContext(),RetailerWelcomeScreen.class));
        finish();
    }

    public void next(View view){
        viewPager.setCurrentItem(currentPos + 1);
    }

    private void addDots(int position){

        dots = new TextView[4];
        dotsLayout.removeAllViews();

        for(int i=0; i<dots.length; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);

            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[position].setTextColor(getResources().getColor(R.color.button));
        }

    }

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
            currentPos = position;

            if(position==0){
                LetsGo.setVisibility(View.INVISIBLE);
            } else if(position==1){
                LetsGo.setVisibility(View.INVISIBLE);

            } else if(position==2){
                LetsGo.setVisibility(View.INVISIBLE);

            } else {
                animation = AnimationUtils.loadAnimation(OnBoarding.this,R.anim.bottom_anim_2);
                LetsGo.setAnimation(animation);
                LetsGo.setVisibility(View.VISIBLE);

            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


}