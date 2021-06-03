package com.example.nonawn.Common.SignUpLogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.nonawn.R;

public class SetNewPass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_new_pass);
    }

    public void setnewpass(View view) {
        startActivity(new Intent(getApplicationContext(),SuksesForgetPassMsg.class));
        finish();
    }
}