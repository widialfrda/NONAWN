package com.example.nonawn.User;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.nonawn.R;

public class ReviewProduk extends AppCompatActivity {
    //Menginisialisasi Variabel
    ImageView imageView;
    Button take_camera;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_design);

        //Asign variable
        imageView = findViewById(R.id.view_barang);
        take_camera = findViewById(R.id.upload_image1);

        //Request For Camera Permission
//        if (ContextCompat.checkSelfPermission(ReviewProduk.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
//            ActivityCompat.requestPermissions(ReviewProduk.this, new String[]{
//                    Manifest.permission.CAMERA
//            }, 100);
//        }
//        take_camera.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //Open Camera
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(intent,100);
//            }
//        });
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        if (requestCode == 100){
//            //Get capture image
//            Bitmap captureimage = (Bitmap) data.getExtras().get("data");
//            //set capture image to image view
//            imageView.setImageBitmap(captureimage);
//        }
    }
}
