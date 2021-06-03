package com.example.nonawn.Common.SignUpLogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.nonawn.R;

public class OTPVerify extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verify);
    }

    public void submitOTP(View view) {
        startActivity(new Intent(getApplicationContext(),SetNewPass.class));
        finish();
    }
}