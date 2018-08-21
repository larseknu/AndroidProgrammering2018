package com.example.larseknu.playingwithintents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class OtherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
    }

    public void getPicture(View view) {
        Toast.makeText(this, "Getting picture", Toast.LENGTH_SHORT).show();
    }
}
