package no.hiof.larseknu.playingwithservices;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import no.hiof.larseknu.playingwithservices.service.MyBoundService;

public class BoundActivity extends AppCompatActivity {
    private boolean isBound = false;
    private MyBoundService myBoundService;
    private TextView locationTextView;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyBoundService.MyLocalBinder myLocalBinder = (MyBoundService.MyLocalBinder) iBinder;

            myBoundService = myLocalBinder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bound);
        Intent intent = new Intent(this, MyBoundService.class);
        startService(intent);

        locationTextView = (TextView) findViewById(R.id.locationTextView);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = new Intent(this, MyBoundService.class);
        bindService(intent, connection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(connection);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void getLocation(View view) {
        if(isBound) {
            Location location = myBoundService.getCurrentLocation();
            if (location != null)
                locationTextView.setText("Lon: " + location.getLongitude() + "\nLat: " + location.getLatitude());
        }
    }
}
