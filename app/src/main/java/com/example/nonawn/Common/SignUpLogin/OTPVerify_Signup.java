package com.example.nonawn.Common.SignUpLogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.example.nonawn.Databases.UserHelperClass;
import com.example.nonawn.R;
import com.example.nonawn.User.UserDashboard;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class OTPVerify_Signup extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    PinView otpverify_signup;
    String codebySystem;

    String fullname, email, password, PhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_otp_verify_signup);

        //hooks
        otpverify_signup = findViewById(R.id.otp_pinview_signup);

        String phone = getIntent().getStringExtra("phone");

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull @NotNull PhoneAuthCredential credential) {
                Toast.makeText(OTPVerify_Signup.this, ""+credential, Toast.LENGTH_SHORT).show();
                signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(@NonNull @NotNull FirebaseException e) {

                Toast.makeText(OTPVerify_Signup.this, ""+e, Toast.LENGTH_SHORT).show();

                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    Toast.makeText(OTPVerify_Signup.this, "Invalid", Toast.LENGTH_SHORT).show();
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    Toast.makeText(OTPVerify_Signup.this, "Melebihi batas kuota", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCodeSent(@NonNull @NotNull String verificationId, @NonNull @NotNull PhoneAuthProvider.ForceResendingToken token) {
                Toast.makeText(OTPVerify_Signup.this, ""+verificationId, Toast.LENGTH_SHORT).show();

                mVerificationId = verificationId;
                mResendToken = token;
            }
        };

//        fullname = getIntent().getStringExtra("fullname");
//        email = getIntent().getStringExtra("email");
//        password = getIntent().getStringExtra("password");
//        PhoneNumber = getIntent().getStringExtra("phoneNo");
//
        sendVerificationCode(phone);
    }

    private void sendVerificationCode(String phoneNumber) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Intent intent = new Intent(OTPVerify_Signup.this, UserDashboard.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                    startActivity(intent);
                }

                else {
                    Toast.makeText(OTPVerify_Signup.this, "gagal", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void submitOTP_signup(View view) {

        String val = otpverify_signup.getText().toString().trim();

        if (val.length() < 4){
            otpverify_signup.setError("Isi harus berupa 4 angka");
            otpverify_signup.requestFocus();
            return;
        }
        else {
            Toast.makeText(this, "Berhasil!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), UserDashboard.class));
            finish();
        }
    }

    public void exitOTP(View view) {
        startActivity(new Intent(getApplicationContext(),Signup.class));
        finish();
    }


}