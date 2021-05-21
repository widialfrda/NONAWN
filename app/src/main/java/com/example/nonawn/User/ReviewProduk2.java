package com.example.nonawn.User;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.example.nonawn.R;

import java.io.File;
import java.io.IOException;

public class ReviewProduk2 extends AppCompatActivity {

    private String currentPhotoPath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_review_design);

        findViewById(R.id.linear_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String filePhoto = "photo";
                File storageDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

                try {
                    File imageFile = File.createTempFile(filePhoto, ".jpg", storageDirectory);
                    currentPhotoPath = imageFile.getAbsolutePath();
                    Uri imageUri = FileProvider.getUriForFile(ReviewProduk2.this, "com.example.nonawn.reviewprovider",imageFile);

                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, 1);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK){
            Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath);
            ImageView imageView = findViewById(R.id.upload_image1);
            imageView.setImageBitmap(bitmap);
    }
}
}


