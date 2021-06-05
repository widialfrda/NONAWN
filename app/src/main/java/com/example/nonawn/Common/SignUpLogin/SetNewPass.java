package com.example.nonawn.Common.SignUpLogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.nonawn.R;
import com.google.android.material.textfield.TextInputLayout;

public class SetNewPass extends AppCompatActivity {
    TextInputLayout pass_lama, pass_baru;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_new_pass);

        pass_lama =  findViewById(R.id.set_pass_lama);
        pass_baru = findViewById(R.id.set_pass_baru);
    }

    private boolean validate_pass_lama(){
        String val = pass_lama.getEditText().getText().toString().trim();
        String check_pass_lama = "^"+"(?=.*[a-zA-Z])"+"(?=.*[0-9])"+"(?=.*[@#$%^&+=])"+"(?=\\S+$)"+".{6,}"+"$";

        if (val.isEmpty()){
            pass_lama.setError("Harus diisi");
            return false;
        }
        else if(!val.matches(check_pass_lama)){
            pass_lama.setError("Harus mengandung minimal 6 karakter, disertai angka dan karakter khusus");
            return false;
        }
        else if(val.length() > 15){
            pass_lama.setError("Maksimal mengandung 15 karakter");
            return false;
        }
        else{
            pass_lama.setError(null);
            pass_lama.setErrorEnabled(false);
            return true;
        }
    }
    public void setnewpass(View view) {

        startActivity(new Intent(getApplicationContext(),SuksesForgetPassMsg.class));
        finish();
    }

    public void backOTP(View view) {
        startActivity(new Intent(getApplicationContext(),OTPVerify.class));
        finish();
    }
}