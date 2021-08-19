package com.example.nonawn.Common.SignUpLogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.nonawn.R;
import com.example.nonawn.User.UserDashboard;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    TextInputLayout var_login_email, var_login_pass;
    Button btnsignin;
    FirebaseAuth firebaseAuth;
    ProgressBar progressBarLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_login);

        //variables for validate email & pass
        var_login_email = findViewById(R.id.signin_email);
        var_login_pass =  findViewById(R.id.signin_password);
        btnsignin = findViewById(R.id.signin_btn);
        firebaseAuth = firebaseAuth.getInstance();



    }

//    public void forgetpass(View view) {
//        startActivity(new Intent(getApplicationContext(),ForgetPassword.class));
//        finish();
//    }

    public void backtoWelcomeScreen(View view) {
        startActivity(new Intent(getApplicationContext(),RetailerWelcomeScreen.class));
        finish();
    }

    public void go_signup2(View view) {
        startActivity(new Intent(getApplicationContext(),Signup.class));
        finish();
    }

    private boolean signin_validateEmail(){
        String val = var_login_email.getEditText().getText().toString().trim();
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()){
            var_login_email.setError("Harus diisi");
            return false;
        }
        else if(!val.matches(checkEmail)){
            var_login_email.setError("Email salah");
            return false;
        }
        else{
            var_login_email.setError(null);
            var_login_email.setErrorEnabled(false);
            return true;
        }
    }

    private boolean signin_validatePassword(){
        String val = var_login_pass.getEditText().getText().toString().trim();
        String checkPass = "^"+"(?=.*[a-zA-Z])"+"(?=.*[0-9])"+"(?=.*[@#$%^&+=])"+"(?=\\S+$)"+".{6,15}"+"$";


        if (val.isEmpty()){
            var_login_pass.setError("Harus diisi");
            return false;
        }
        else if(!val.matches(checkPass)){
            var_login_pass.setError("Harus mengandung minimal 6 karakter, disertai angka dan karakter khusus");
            return false;
        }
        else{
            var_login_pass.setError(null);
            var_login_pass.setErrorEnabled(false);
            return true;
        }
    }

    public void login_userdashboard(View view) {
        firebaseAuth.signInWithEmailAndPassword(var_login_email.getEditText().getText().toString(),var_login_pass.getEditText().getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Intent intent = new Intent(getApplicationContext(),UserDashboard.class);
                        if(task.isSuccessful()&!signin_validateEmail() | !signin_validatePassword()&(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP)){
                            startActivity(intent);
                        }
//                        else if (!signin_validateEmail() | !signin_validatePassword()){
//                            Toast.makeText(Login.this,"Akun tidak terdaftar", Toast.LENGTH_LONG).show();
////                            return;
//                        }
                        else{
                            Toast.makeText(Login.this,"Akun tidak terdaftar", Toast.LENGTH_LONG).show();
                            startActivity(intent);
                            return;
                        }
                    }
                });

//        if (!signin_validateEmail() | !signin_validatePassword()){
//            return;
//        }
//        String getEmail = getIntent().getStringExtra("email");
//        String getPassword = getIntent().getStringExtra("password");

//        Intent intent = new Intent(getApplicationContext(),UserDashboard.class);

//        intent.putExtra("email",getEmail);
//        intent.putExtra("password",getPassword);

//        Pair[] pairs = new Pair[1];
//
//        pairs[0] = new Pair<View,String>(btnsignin,"trans_signin");
//
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//
//            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this, pairs);
//            startActivity(intent,options.toBundle());
//        } else {
//
//            startActivity(intent);
//        }
    }
}