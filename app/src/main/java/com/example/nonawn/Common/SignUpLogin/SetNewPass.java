package com.example.nonawn.Common.SignUpLogin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;

import com.example.nonawn.R;
import com.google.android.material.textfield.TextInputLayout;

public class SetNewPass extends AppCompatActivity {
    TextInputLayout pass_lama, pass_baru;
    Button setNewPass_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_new_pass);

        pass_lama =  findViewById(R.id.set_pass_lama);
        pass_baru = findViewById(R.id.set_pass_baru);
        setNewPass_btn = findViewById(R.id.setNewPass_btn);
    }

    private boolean validate_pass_lama(){
        String val_pass_lama = pass_lama.getEditText().getText().toString().trim();
        String check_pass_lama = "^"+"(?=.*[a-zA-Z])"+"(?=.*[0-9])"+"(?=.*[@#$%^&+=])"+"(?=\\S+$)"+".{6,}"+"$";

        if (val_pass_lama.isEmpty()){
            pass_lama.setError("Harus diisi");
            return false;
        }
        else if(!val_pass_lama.matches(check_pass_lama)){
            pass_lama.setError("Harus mengandung minimal 6 karakter, disertai angka dan karakter khusus");
            return false;
        }
        else if(val_pass_lama.length() > 15){
            pass_lama.setError("Maksimal mengandung 15 karakter");
            return false;
        }
        else{
            pass_lama.setError(null);
            pass_lama.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validate_pass_baru(){
        String val_pass_baru = pass_baru.getEditText().getText().toString().trim();
        String check_pass_baru = "^"+"(?=.*[a-zA-Z])"+"(?=.*[0-9])"+"(?=.*[@#$%^&+=])"+"(?=\\S+$)"+".{6,15}"+"$";

        if (val_pass_baru.isEmpty()){
            pass_baru.setError("Harus diisi");
            return false;
        }
        else if(!val_pass_baru.matches(check_pass_baru)){
            pass_baru.setError("Harus mengandung minimal 6 karakter dan maksimal 15 karakter, disertai angka dan karakter khusus");
            return false;
        }
        else{
            pass_baru.setError(null);
            pass_baru.setErrorEnabled(false);
            return true;
        }
    }
    public void setnewpass(View view) {
        if (!validate_pass_lama() | !validate_pass_baru()){
            return;
        }

        String getPassLama = getIntent().getStringExtra("pass_lama");
        String getPassBaru = getIntent().getStringExtra("pass_baru");

        Intent intent = new Intent(getApplicationContext(), SuksesOrder.class);

        intent.putExtra("pass_lama",getPassLama);
        intent.putExtra("pass_baru",getPassBaru);

        Pair[] pairs = new Pair[1];

        pairs[0] = new Pair<View,String>(setNewPass_btn,"trans_setNewPass");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {

            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SetNewPass.this, pairs);
            startActivity(intent,options.toBundle());
        } else {

            startActivity(intent);
        }
    }

    public void backOTP(View view) {
        startActivity(new Intent(getApplicationContext(), OTPVerify_forgetpass.class));
        finish();
    }
}