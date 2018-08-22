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

    private static final int REQUEST_IMAGE_GET = 9001;

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        // TODO X6: Get a reference to the intent we receive from MainActivity
        Intent receivedIntent = getIntent();

        // TODO X7: Get the extra "name" information from the intent
        String name = receivedIntent.getStringExtra(MainActivity.KEY_NAME);

        // TODO X8: Fill the TextView with the String from the extra-data
        TextView textView = findViewById(R.id.textView);
        textView.setText(name);

        imageView = findViewById(R.id.imageView);
    }

    public void getPicture(View view) {
        //Toast.makeText(this, "Getting picture", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_IMAGE_GET);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_GET && resultCode == RESULT_OK) {
            try {
                Uri fullPhotoUri = data.getData();
                Bitmap picture = MediaStore.Images.Media.getBitmap(getContentResolver(), fullPhotoUri);
                imageView.setImageBitmap(picture);
            } catch (IOException e) {
                Toast.makeText(this, "Couldn't get picture", Toast.LENGTH_SHORT).show();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
