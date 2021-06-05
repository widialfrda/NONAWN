package com.example.nonawn.Common.SignUpLogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.example.nonawn.R;

public class OTPVerify extends AppCompatActivity {

    PinView otpverify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verify);

        otpverify = findViewById(R.id.otp_pinview);
    }

    public void submitOTP(View view) {

        String val = otpverify.getText().toString().trim();

        if (val.length() < 4){
            otpverify.setError("Isi harus berupa 4 angka");
            otpverify.requestFocus();
            return;
        }
        else {
            Toast.makeText(this, "Berhasil!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(),SetNewPass.class));
            finish();
        }
    }

    public void exitOTP(View view) {
        startActivity(new Intent(getApplicationContext(),PilihanReset.class));
        finish();
    }


}