package com.example.nonawn.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nonawn.Common.SignUpLogin.Login;
import com.example.nonawn.Common.SignUpLogin.OTPVerify_Signup;
import com.example.nonawn.Common.SignUpLogin.RetailerWelcomeScreen;
import com.example.nonawn.Common.SignUpLogin.Signup;
import com.example.nonawn.Databases.SessionManager;
import com.example.nonawn.Databases.UserHelperClass;
import com.example.nonawn.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {
    TextView display_fullname, label_fullname, label_email, label_password, label_notelp;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth fAuth;
    String uipn;

    Button btn_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Hooks
        display_fullname = findViewById(R.id.text_namauser);
        label_fullname = findViewById(R.id.profile_fullname);
        label_email = findViewById(R.id.profile_email);
        label_password = findViewById(R.id.profile_password);
        label_notelp = findViewById(R.id.profile_notelp);

        fAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        Intent intent = getIntent();
        uipn = intent.getStringExtra("uipn");

        Log.e("UIPN",""+uipn);

        btn_logout = findViewById(R.id.btn_logout);

        showAllUser();
    }

    private void showAllUser() {

//        Intent intent = getIntent();
//        Intent intent = new Intent(getApplicationContext(), Signup.class);
//
//        intent.putExtra("display_fullname",display_fullname);
//        intent.putExtra("val",label_fullname);
//        intent.putExtra("email",label_email);
//        intent.putExtra("password",label_password);
//        intent.putExtra("no_telp",label_notelp);


//        display_fullname(show_labeluser);
//
//        label_fullname.setText(show_fullname);
//        label_email.setText(show_email);
//        label_password.setText(show_password);
//        label_notelp.setText(show_notelp);


        DatabaseReference databaseReference = firebaseDatabase.getReference("Users").child(uipn);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserHelperClass userHelperClass = snapshot.getValue(UserHelperClass.class);

                display_fullname.setText(userHelperClass.getFullname());
                label_fullname.setText(userHelperClass.getFullname());
                label_email.setText(userHelperClass.getEmail());
                label_password.setText(userHelperClass.getPassword());
                label_notelp.setText(""+userHelperClass.getPhoneNumber());


                Log.e("dis_email",""+label_email);
                Log.e("dis_notelp",""+label_notelp);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Profile.this, error.getCode(),Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void logout_profile(View view) {

                SessionManager sessionManager = new SessionManager(getApplicationContext());
                sessionManager.logoutUserFromSession();

                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
    }
}