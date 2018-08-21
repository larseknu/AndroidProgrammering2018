package com.example.larseknu.playingwithintents;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //TODO 4: Add a navigateToOtherActivity(View view), method for a button-click
    //TODO 5: Create an Intent to start OtherActivity in the navigate... method
    //TODO 6: Start OtherActivity with the intent

    public void navigateToOtherActivity(View view) {
        Intent intent = new Intent(this, OtherActivity.class);

        startActivity(intent);
    }

    public void openWebPage(View view) {

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.parse("http://hiof.no");
        intent.setData(uri);

        startActivity(intent);
    }
}
