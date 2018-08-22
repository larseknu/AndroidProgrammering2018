package com.example.larseknu.playingwithintents;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    //TODO X4: Create a constant (public static final) to represent the key in the extra for the intent
    public static final String KEY_NAME = "name";

    //TODO Z1: Create a field variable for the button navigateToOtherButton
    private Button navigateToOtherButton;
    //TODO X2: Create a field variable (nameEditText) for the EditText view
    private EditText nameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO X3: Instantiate nameEditText
        nameEditText = findViewById(R.id.nameEditText);

        // TODO Z2: Instantiate navigateToOtherButton
        navigateToOtherButton = findViewById(R.id.navigateToOtherButton);

        // TODO 4: Add a onClickListener to the navigateToOtherButton
        navigateToOtherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO 5: Create an Intent to start OtherActivity in the navigate... method
                Intent intent = new Intent(view.getContext(), OtherActivity.class);

                // TODO X4: Get the text from the EditText when navigateToOtherActivity is ran
                String name = nameEditText.getText().toString();

                // TODO X5: Put the text from the EditText into the extra information in the Intent
                intent.putExtra(KEY_NAME, name);

                // TODO 6: Start OtherActivity with the intent
                startActivity(intent);
            }
        });

    }

    public void openWebPage(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.parse("http://hiof.no");
        intent.setData(uri);

        startActivity(intent);
    }

    public void openMaps(View view) {
        //Uri uri = Uri.parse("https://maps.google.com?q=59.128708,11.353176");
        Uri uri = Uri.parse("geo:59.128708,11.353176");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        startActivity(intent);
    }
}
