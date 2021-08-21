package com.example.nonawn.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.nonawn.Common.SignUpLogin.Login;
import com.example.nonawn.R;

public class Checkout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
    }

    public void back_cart(View view) {
        startActivity(new Intent(getApplicationContext(), Cart.class));
        finish();
    }
}