package com.example.nonawn.Common.SignUpLogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.nonawn.R;
import com.example.nonawn.User.UserDashboard;

public class SuksesOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sukses_order);
    }

    public void go_dashboard(View view) {
        startActivity(new Intent(getApplicationContext(), UserDashboard.class));
        finish();
    }
}