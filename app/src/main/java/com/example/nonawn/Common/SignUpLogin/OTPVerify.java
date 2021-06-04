package com.example.nonawn.Common.SignUpLogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.chaos.view.PinView;
import com.example.nonawn.R;
import com.google.android.material.textfield.TextInputLayout;

public class OTPVerify extends AppCompatActivity {

    TextInputLayout otp_verify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verify);

        //variable for otp
        otp_verify = findViewById(R.id.otp_pinview);
    }

    private boolean validateOTPPass(){
        String val = otp_verify.getEditText().getText().toString().trim();
        String checkOTP = "^"+"(?=.*[0-9])"+".{4,}";

        if (val.isEmpty()){
            otp_verify.setError("Harus diisi");
            return false;
        }
        else if(!val.matches(checkOTP)){
            otp_verify.setError("Harus mengandung minimal 4 karakter, disertai angka dan karakter khusus");
            return false;
        }
        else{
            otp_verify.setError(null);
            otp_verify.setErrorEnabled(false);
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