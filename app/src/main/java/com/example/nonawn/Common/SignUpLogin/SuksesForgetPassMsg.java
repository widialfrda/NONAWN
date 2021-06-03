package com.example.nonawn.Common.SignUpLogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.nonawn.R;

public class SuksesForgetPassMsg extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sukses_forget_pass_msg);
    }

    public void suksesforget_gotologin(View view) {
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }
}