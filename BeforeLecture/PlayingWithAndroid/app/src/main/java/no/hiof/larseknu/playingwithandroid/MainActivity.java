package no.hiof.larseknu.playingwithandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private String LOG_TAG = "PlayingWithAndroid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(LOG_TAG, "onCreate() ran");
    }

    //TODO 1: Override the onStart() ActivityLifecycle event and add logging (Ctrl + O - Override shortcut)

    //TODO 2: Override the onResume() ActivityLifecycle event and add logging

    //TODO 3: Override the onPause() ActivityLifecycle event and add logging

    //TODO 4: Override the onStop() ActivityLifecycle event and add logging

    //TODO 5: Override the onRestart() ActivityLifecycle event and add logging

    //TODO 6: Override the onDestroy() ActivityLifecycle event and add logging

    //TODO 7: Create OtherActivity.java with the "New->Activity->Empty activity" dropdown menu
    //TODO 8: Add a TextView to OtherActivity with the text "Hello Android!"

    //TODO 11: Add a navigateToOtherActivity(View view), method for a button-click
    //TODO 12: Create an Intent to start OtherActivity in the navigate... method
    //TODO 13: Start OtherActivity with the intent
}
