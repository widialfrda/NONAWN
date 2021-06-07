package com.example.nonawn.Common.SignUpLogin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nonawn.R;
import com.google.android.material.textfield.TextInputLayout;

public class Signup extends AppCompatActivity {

    //variables
    ImageView backlogin, logo;
    Button signupbtn;
    TextView backlogin2;

    //variabel getting data
    TextInputLayout fullname, email, password, no_telp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_signup);

        //hooks for animations
        backlogin = findViewById(R.id.backtologin);
        backlogin2 = findViewById(R.id.backtologin2);
        signupbtn = findViewById(R.id.gosignup);
        logo = findViewById(R.id.logo_signup);

        //hooks for getting data
        fullname = findViewById(R.id.signup_fullname);
        email = findViewById(R.id.signup_email);
        password = findViewById(R.id.signup_password);
        no_telp = findViewById(R.id.signup_no_telp);
    }


    public void backtologin(View view) {
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }

    public void backtologin2(View view) {
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }

    private boolean validateFullname(){
        String val = fullname.getEditText().getText().toString().trim();

        if (val.isEmpty()){
            fullname.setError("Harus diisi");
            return false;
        }
        else{
            fullname.setError(null);
            fullname.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateEmail(){
        String val = email.getEditText().getText().toString().trim();
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()){
            email.setError("Harus diisi");
            return false;
        }
        else if(!val.matches(checkEmail)){
            email.setError("Email salah");
            return false;
        }
        else{
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePassword(){
        String val = password.getEditText().getText().toString().trim();
        String checkPass = "^"+"(?=.*[a-zA-Z])"+"(?=.*[0-9])"+"(?=.*[@#$%^&+=])"+"(?=\\S+$)"+".{6,15}"+"$";

        if (val.isEmpty()){
            password.setError("Harus diisi");
            return false;
        }
        else if(!val.matches(checkPass)){
            password.setError("Harus mengandung minimal 6 karakter, disertai angka dan karakter khusus");
            return false;
        }
        else{
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateNoTelp(){
        String val = no_telp.getEditText().getText().toString().trim();
        String checkno_telp = "(?=.*[0-9])"+"(?=\\S+$)"+".{11,13}";

        if (val.isEmpty()){
            no_telp.setError("Harus diisi");
            return false;
        }
        else if(!val.matches(checkno_telp)){
            no_telp.setError("Harus berupa angka dan min. 11 karakter");
            return false;
        }
        else{
            no_telp.setError(null);
            no_telp.setErrorEnabled(false);
            return true;
        }
    }



    public void go_signup(View view) {

        if (!validateFullname() | !validateEmail() | !validatePassword() | !validateNoTelp()){
            return;
        }
        String getFullname = getIntent().getStringExtra("fullname");
        String getEmail = getIntent().getStringExtra("email");
        String getPassword = getIntent().getStringExtra("password");
        String getNoTelp = getIntent().getStringExtra("no_telp");

        Intent intent = new Intent(getApplicationContext(),RetailerWelcomeScreen.class);

        intent.putExtra("fullname",getFullname);
        intent.putExtra("email",getEmail);
        intent.putExtra("password",getPassword);
        intent.putExtra("no_telp",getNoTelp);

        Pair[] pairs = new Pair[1];

//        pairs[0] = new Pair<View,String>(backlogin,"trans_backlogin");
//        pairs[1] = new Pair<View,String>(backlogin2,"trans_backlogin2");
//        pairs[2] = new Pair<View,String>(logo,"trans_logo_signup");
        pairs[0] = new Pair<View,String>(signupbtn,"trans_signup");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {

            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Signup.this, pairs);
            startActivity(intent,options.toBundle());
        } else {

            startActivity(intent);
        }

        Toast.makeText(this,"Daftar akun berhasil",Toast.LENGTH_SHORT).show();
    }
}