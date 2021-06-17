package com.example.nonawn.Common.SignUpLogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class OTPVerify_Signup extends AppCompatActivity {

    PinView otpverify_signup;
    String codebySystem;

    String fullname, email, password, PhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_otp_verify_signup);

        //hooks
        otpverify_signup = findViewById(R.id.otp_pinview_signup);

        fullname = getIntent().getStringExtra("fullname");
        email = getIntent().getStringExtra("email");
        password = getIntent().getStringExtra("password");
        PhoneNumber = getIntent().getStringExtra("phoneNo");

        sendVerificationCode(PhoneNumber);
    }

    private void sendVerificationCode(String phoneNo) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNo, //Phone number to verify
                60, //Timeout Duration
                TimeUnit.SECONDS, //Unit of Timeout
                TaskExecutors.MAIN_THREAD, //Activity (for callback binding)
                mCallbacks);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                @Override
                public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);
                    codebySystem = s;
                }

                @Override
                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                    String code = phoneAuthCredential.getSmsCode();
                    if(code!=null){
                        otpverify_signup.setText(code);
                        verifyCode(code);
                    }

                }

                @Override
                public void onVerificationFailed(@NonNull FirebaseException e) {
                    Toast.makeText(OTPVerify_Signup.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            };

    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codebySystem,code);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            storeNewUserData();

                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(OTPVerify_Signup.this,"Verifikasi Gagal, Coba lagi.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private void storeNewUserData() {

        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        DatabaseReference reference = rootNode.getReference("Users");

        UserHelperClass addNewUser = new UserHelperClass(fullname,email,password,PhoneNumber);

        reference.child(PhoneNumber).setValue(addNewUser);
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