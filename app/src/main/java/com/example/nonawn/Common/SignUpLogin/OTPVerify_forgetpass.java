package com.example.nonawn.Common.SignUpLogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.example.nonawn.R;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OTPVerify_forgetpass extends AppCompatActivity {

    PinView otpverify_forgetpass;
    String codebySystem_forgetpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_otp_verify_forgetpass);

        //hooks
        otpverify_forgetpass = findViewById(R.id.otp_pinview_forgetpass);

        String PhoneNumber = getIntent().getStringExtra("phoneNo");

        sendVerificationCode(PhoneNumber);
    }

    private void sendVerificationCode(String phoneNo) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNo, //Phone number to verify
                60, //Timeout Duration
                TimeUnit.SECONDS, //Unit of Timeout
                (Activity) TaskExecutors.MAIN_THREAD, //Activity (for callback binding)
                mCallbacks);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                @Override
                public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);
                    codebySystem_forgetpass = s;
                }

                @Override
                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                    String code = phoneAuthCredential.getSmsCode();
                    if(code!=null){
                        otpverify_forgetpass.setText(code);
                        verifyCode(code);
                    }

                }

                @Override
                public void onVerificationFailed(@NonNull FirebaseException e) {
                    Toast.makeText(OTPVerify_forgetpass.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            };

    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codebySystem_forgetpass,code);
        forgetPassUsingCredential(credential);
    }

    private void forgetPassUsingCredential(PhoneAuthCredential credential) {
    }

    public void submitOTP_forgetpass(View view) {

        String val = otpverify_forgetpass.getText().toString().trim();

        if (val.length() < 4){
            otpverify_forgetpass.setError("Isi harus berupa 4 angka");
            otpverify_forgetpass.requestFocus();
            return;
        }
        else {
            Toast.makeText(this, "Berhasil!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(),SetNewPass.class));
            finish();
        }
    }

    public void exitOTP(View view) {
        startActivity(new Intent(getApplicationContext(),PilihanReset.class));
        finish();
    }


}