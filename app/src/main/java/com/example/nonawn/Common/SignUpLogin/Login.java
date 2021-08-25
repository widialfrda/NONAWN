package com.example.nonawn.Common.SignUpLogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.nonawn.Databases.SessionManager;
import com.example.nonawn.R;
import com.example.nonawn.User.Profile;
import com.example.nonawn.User.UserDashboard;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    TextInputLayout var_login_email, var_login_pass, var_login_phoneNumber;
    Button btnsignin;
    FirebaseAuth firebaseAuth;
    ProgressBar progressBarLogin;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_login);

        //variables for validate email & pass
        var_login_email = findViewById(R.id.signin_email);
        var_login_pass =  findViewById(R.id.signin_password);
        var_login_phoneNumber = findViewById(R.id.signin_no_telp);
        btnsignin = findViewById(R.id.signin_btn);
        firebaseAuth = firebaseAuth.getInstance();

        sessionManager = new SessionManager(this);

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
//        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()){
            var_login_email.setError("Harus diisi");
            return false;
        }
//        else if(!val.matches(checkEmail)){
//            var_login_email.setError("Email salah");
//            return false;
//        }
        else{
            var_login_email.setError(null);
            var_login_email.setErrorEnabled(false);
            return true;
        }
    }

    private boolean signin_validatePassword(){
        String val = var_login_pass.getEditText().getText().toString().trim();
//        String checkPass = "^"+"(?=.*[a-zA-Z])"+"(?=.*[0-9])"+"(?=.*[@#$%^&+=])"+"(?=\\S+$)"+".{6,15}"+"$";


        if (val.isEmpty()){
            var_login_pass.setError("Harus diisi");
            return false;
        }
//        else if(!val.matches(checkPass)){
//            var_login_pass.setError("Harus mengandung minimal 6 karakter, disertai angka dan karakter khusus");
//            return false;
//        }
        else{
            var_login_pass.setError(null);
            var_login_pass.setErrorEnabled(false);
            return true;
        }
    }

    private boolean signin_validateNoTelp(){
        String val = var_login_phoneNumber.getEditText().getText().toString().trim();
        String checkno_telp = "^"+"(?=.*[a-zA-Z])"+"(?=.*[0-9])"+"(?=.*[@#$%^&+=])"+"(?=\\S+$)"+".{13,15}"+"$";

        if (val.isEmpty()){
            var_login_phoneNumber.setError("Harus diisi");
            return false;
        }
        else if(!val.matches(checkno_telp)){
//            var_login_phoneNumber.setError("Harus berupa angka dan min. 13 karakter");
            return false;
        }
        else{
            var_login_phoneNumber.setError(null);
            var_login_phoneNumber.setErrorEnabled(false);
            return true;
        }
    }

    public void login_userdashboard(View view) {

        if (!signin_validateEmail() | !signin_validatePassword() | signin_validateNoTelp()){
            return;
        }
        else {
            isUser();
        }

//        String getEmail = getIntent().getStringExtra("email");
//        String getPassword = getIntent().getStringExtra("password");
//
//        Intent intent = new Intent(getApplicationContext(),UserDashboard.class);
//
//        intent.putExtra("email",getEmail);
//        intent.putExtra("password",getPassword);
//
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

    private void isUser() {
        String userInputEmail = var_login_email.getEditText().getText().toString().trim();
        String userInputPassword = var_login_pass.getEditText().getText().toString().trim();
        String userInputPhoneNumber = var_login_phoneNumber.getEditText().getText().toString();


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");

        Query checkUser = reference.orderByChild("email").equalTo(userInputEmail);


        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    var_login_email.setError(null);
                    var_login_email.setErrorEnabled(false);

                    String checker = dataSnapshot.child(userInputPhoneNumber).child("password").getValue().toString();
                    Log.e("PW",""+checker);

                    //String passwordFromDB = dataSnapshot.child(userInputPassword).child("password").getValue(String.class);

                    if (checker.equals(userInputPassword)){

                        var_login_email.setError(null);
                        var_login_email.setErrorEnabled(false);

//
                        String emailFromDB = dataSnapshot.child("email").getValue(String.class);

                        Intent intent = new Intent(getApplicationContext(), UserDashboard.class);

                        intent.putExtra("email", emailFromDB);
                        intent.putExtra("password", checker);
                        intent.putExtra("uipn",userInputPhoneNumber);

                        //Create a session
                        sessionManager.saveSPString(SessionManager.KEY_PhoneNumber, userInputPhoneNumber);
                        sessionManager.saveSPBoolean(SessionManager.IS_LOGIN,true);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        var_login_pass.setError("Password Salah");
                        var_login_pass.requestFocus();
                    }
                }
                else{
                    var_login_email.setError("Email Salah");
                    var_login_email.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}