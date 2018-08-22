package com.example.larseknu.playingwithintents;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class OtherActivity extends AppCompatActivity {
    // 5.9: Create a constant integer for the image request
    private static final int REQUEST_IMAGE_GET = 9001;

    // 5.4: Create a field variable for the ImageView
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        // 2.7: Get a reference to the intent we receive from MainActivity
        Intent receivedIntent = getIntent();

        // 2.8: Get the extra "name" information from the intent
        String name = receivedIntent.getStringExtra(MainActivity.KEY_NAME);

        // 2.9: Fill the TextView with the String from the extra-data
        TextView textView = findViewById(R.id.textView);
        textView.setText(name);

        // 5.5: Instantiate the ImageView
        imageView = findViewById(R.id.imageView);
    }

    // 5.6: Create getPicture(View view) method to handle the click
    public void getPicture(View view) {
        //Toast.makeText(this, "Getting picture", Toast.LENGTH_SHORT).show();
        // 5.7: Create Intent with ACTION_GET_CONTENT
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        // 5.8: Set the type of the intent to image/*
        intent.setType("image/*");
        // 5.10: Call the setActivityResult with the intent and the int constant
        startActivityForResult(intent, REQUEST_IMAGE_GET);
    }

    // 5.11: Override onActivityResult to handle result of the image request
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 5.12: Check that the requestCode matches the int constant, and that the resultCode is OK
        if (requestCode == REQUEST_IMAGE_GET && resultCode == RESULT_OK) {
            try {
                // 5.13: Get the data URI from the intent
                Uri fullPhotoUri = data.getData();
                // 5.14: Retrieve a bitmap of the picture from the URI, with the help of MediaStore.Images.Media.getBitMap
                Bitmap picture = MediaStore.Images.Media.getBitmap(getContentResolver(), fullPhotoUri);
                // 5.15: Set the bitmap to the imageView
                imageView.setImageBitmap(picture);
            } catch (IOException e) {
                Toast.makeText(this, "Couldn't get picture", Toast.LENGTH_SHORT).show();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
