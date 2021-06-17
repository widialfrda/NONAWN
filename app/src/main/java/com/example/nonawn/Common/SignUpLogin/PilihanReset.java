package com.example.nonawn.Common.SignUpLogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.nonawn.R;

public class PilihanReset extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilihan_reset);
    }


    public void resetbysms(View view) {
        startActivity(new Intent(getApplicationContext(), OTPVerify_forgetpass.class));
        finish();
    }

    public void resetbyemail(View view) {
        startActivity(new Intent(getApplicationContext(), OTPVerify_forgetpass.class));
        finish();
    }

    public void pilihanreset_backlogin(View view) {
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }
}