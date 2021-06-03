package com.example.nonawn.Common.SignUpLogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.nonawn.R;
import com.example.nonawn.User.UserDashboard;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_login);
    }

    public void forgetpass(View view) {
        startActivity(new Intent(getApplicationContext(),ForgetPassword.class));
        finish();
    }

    public void backtoWelcomeScreen(View view) {
        startActivity(new Intent(getApplicationContext(),RetailerWelcomeScreen.class));
        finish();
    }

    public void go_signup2(View view) {
        startActivity(new Intent(getApplicationContext(),Signup.class));
        finish();
    }

    public void login_userdashboard(View view) {
        startActivity(new Intent(getApplicationContext(), UserDashboard.class));
        finish();
    }
}