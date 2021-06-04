package com.example.nonawn.Common.SignUpLogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.chaos.view.PinView;
import com.example.nonawn.R;

public class OTPVerify extends AppCompatActivity {

    PinView otp_verify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verify);

        otp_verify = findViewById(R.id.otp_pinview);
    }

    private boolean validateOTPpass(){
        String val = otp_verify.getText().toString().trim();
        String check_otp = "^"+"(?=.*[0-9])"+".{4,}";

        if (val.isEmpty()){
            otp_verify.setError("Harus diisi");
            return false;
        }
        else if(!val.matches(check_otp)){
            otp_verify.setError("Harus mengandung 4 angka");
            return false;
        }
        else if(val.matches(check_otp)){
            return true;
        }
        else {
            return true;
        }

    }
    public void submitOTP(View view) {
        startActivity(new Intent(getApplicationContext(),SetNewPass.class));
        finish();
    }

    public void exitOTP(View view) {
        startActivity(new Intent(getApplicationContext(),PilihanReset.class));
        finish();
    }


}