package com.example.nonawn.User;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.nonawn.R;

public class Tentang extends AppCompatActivity {

    ImageView backtodashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentang);

        //Hooks
        backtodashboard = findViewById(R.id.back_dashboard);

        backtodashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tentang.super.onBackPressed();
            }
        });
    }
}