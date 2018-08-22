package com.example.larseknu.playingwithintents;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    //TODO 2.4: Create a constant (public static final) to represent the key in the extra for the intent

    //TODO 1.3: Create a field variable for the button navigateToOtherButton

    //TODO 2.2: Create a field variable (nameEditText) for the EditText view

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO 2.3: Instantiate nameEditText

        // TODO 1.4: Instantiate navigateToOtherButton
        // TODO 1.5: Add a onClickListener to the navigateToOtherButton

        // TODO 1.6: Create an Intent to start OtherActivity in the navigate... method

        // TODO 2.5: Get the text from the EditText when navigateToOtherActivity is ran
        // TODO 2.6: Put the text from the EditText into the extra information in the Intent

        // TODO 1.7: Start OtherActivity with the intent
    }

    // TODO 3.3: Create openWebPage(View view) method to handle the click
    // TODO 3.4: Create an Intent
    // TODO 3.5: Set action ACTION_VIEW to the intent
    // TODO 3.6: Create an URI to a webpage and set it as data for the intent
    // TODO 3.7: Start the activity with the intent


    // TODO 4.3: Create openMaps(View view) method to handle the click
    // TODO 4.4: Create an URI to https://maps.google.com?q=59.128708,11.353176

    // TODO 4.7: Create an URI with the geo:lan,lot instead

    // TODO 4.5: Create an Intent with ACTION_VIEW and the URI
    // TODO 4.6: Start the activity with the intent
}
