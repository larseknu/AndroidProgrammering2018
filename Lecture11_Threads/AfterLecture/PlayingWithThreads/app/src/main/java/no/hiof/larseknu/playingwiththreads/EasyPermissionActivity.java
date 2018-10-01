package no.hiof.larseknu.playingwiththreads;

import android.Manifest;
import android.location.Location;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class EasyPermissionActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {
    private AsyncTaskWorker asyncTaskWorker;
    private TextView statusText;

    private static final int MY_PERMISSIONS_ACCESS_LOCATION_AND_STORAGE = 1;
    private String[] neededPermissions = { Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_permission);
        getSupportActionBar().setTitle("EasyPermissions");

        statusText = findViewById(R.id.statusText);
    }


    public void doAsyncWork(View view) {
        retrieveAndSaveAddress();
    }

    @AfterPermissionGranted(MY_PERMISSIONS_ACCESS_LOCATION_AND_STORAGE)
    private void retrieveAndSaveAddress() {
        asyncTaskWorker = new AsyncTaskWorker();

        if (EasyPermissions.hasPermissions(this, neededPermissions))
            asyncTaskWorker.execute();
        else
            EasyPermissions.requestPermissions(this,
                    "Location and local storage access needed to be able to save a local address",
                    MY_PERMISSIONS_ACCESS_LOCATION_AND_STORAGE, neededPermissions);

    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        Log.d("EasyPermission", "Permissions granted");
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    public class AsyncTaskWorker extends AsyncTask<Void, String, Boolean> {
        @Override
        protected Boolean doInBackground(Void... voids) {
            Worker worker = new Worker(EasyPermissionActivity.this);
            publishProgress("Starting");

            JSONObject jsonObject = worker.getJSONObjectFromURL("http://www.omdbapi.com/?i=tt3896198&apikey=33f85a4c");
            publishProgress("Retrieved JSON");

            Location foundLocation = worker.getLocation();
            publishProgress("Retrieved Location");

            String address = worker.reverseGeocode(foundLocation);
            publishProgress("Retrieved Address");

            try {
                worker.saveToFile(foundLocation, address, jsonObject.getString("Title"), "NonThreadFile.txt");
            } catch (JSONException e) {
                e.printStackTrace();
                return false;
            }

            publishProgress("Done");

            return true;
        }

        @Override
        protected void onProgressUpdate(String... messages) {
            statusText.setText(messages[0]);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result)
                statusText.setText("Done");
            else
                statusText.setText("Something failed =(");
        }
    }
}
