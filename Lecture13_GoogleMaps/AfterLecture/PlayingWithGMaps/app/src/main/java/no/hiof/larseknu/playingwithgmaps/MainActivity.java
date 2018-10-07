package no.hiof.larseknu.playingwithgmaps;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Property;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.w3c.dom.Document;

import java.util.ArrayList;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener, AdapterView.OnItemSelectedListener {
    //region Variables
    private LatLng HIOF = new LatLng(59.12797849, 11.35272861);
    private LatLng FREDRIKSTAD = new LatLng(59.21047628, 10.93994737);

    private String[] locationPermission = {Manifest.permission.ACCESS_FINE_LOCATION};
    private static final int LOCATION_PERMISSION_ID = 1;

    private GoogleMap gMap;

    private int kittenCounter = 0;
    private ArrayList<Marker> kittenMarkers;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        kittenMarkers = new ArrayList<>();

        Spinner spinner = findViewById(R.id.layersSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.layers_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        new DrawRoute().execute(HIOF);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;

        gMap.setOnMapLongClickListener(this);

        setUpDefaultUISettings();

        gMap.addMarker(new MarkerOptions().position(HIOF).title("Østfold University College"));
        gMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(HIOF, 15, 0, 0)));

        gMap.addMarker(new MarkerOptions().position(FREDRIKSTAD).title("Fredrikstad Kino"));
        gMap.animateCamera(CameraUpdateFactory.newLatLng(FREDRIKSTAD), 2000, null);
    }

    // region UI handling
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String layerType = (String) parent.getItemAtPosition(position);

        if (layerType.equals(getString(R.string.hybrid)))
            gMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        else if (layerType.equals(getString(R.string.satellite)))
            gMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        else if (layerType.equals(getString(R.string.terrain)))
            gMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        else if (layerType.equals(getString(R.string.none)))
            gMap.setMapType(GoogleMap.MAP_TYPE_NONE);
        else
            gMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void setUpDefaultUISettings() {
        UiSettings uiSettings = gMap.getUiSettings();
        uiSettings.setCompassEnabled(true);
        uiSettings.setTiltGesturesEnabled(true);
        uiSettings.setZoomControlsEnabled(true);
        uiSettings.setMapToolbarEnabled(false);
        uiSettings.setMyLocationButtonEnabled(true);

    }
    // endregion

    //region Marker handling
    @Override
    public void onMapLongClick(LatLng latLng) {
        addKittenMarker(latLng, "Kitten Invation");

    }

    private void addKittenMarker(LatLng kittenLocation, String snippet) {
        // Get a kittenIcon from the drawable resources. Must be named "kitten_0X", where X is a number.
        BitmapDescriptor kittenIcon = BitmapDescriptorFactory.fromResource(getResources().getIdentifier("kitten_0" + (kittenCounter%3+1), "drawable", this.getPackageName()));

        kittenCounter++;

        MarkerOptions markerOptions = new MarkerOptions()
                .position(kittenLocation)
                .title("Mittens the " + kittenCounter + ".")
                .snippet(snippet)
                .icon(kittenIcon);

        Marker kittenMarker = gMap.addMarker(markerOptions);

        kittenMarkers.add(kittenMarker);
    }

    private void animateMarker(Marker kittenMarker, LatLng finalPosition) {
        TypeEvaluator<LatLng> typeEvaluator = new TypeEvaluator<LatLng>() {
            @Override
            public LatLng evaluate(float fraction, LatLng startValue, LatLng endValue) {
                double lat = (endValue.latitude - startValue.latitude) * fraction + startValue.latitude;
                double lng = (endValue.longitude - startValue.longitude) * fraction + startValue.longitude;

                return new LatLng(lat, lng);
            }
        };

        Property<Marker, LatLng> property = Property.of(Marker.class, LatLng.class, "position");
        ObjectAnimator animator = ObjectAnimator.ofObject(kittenMarker, property, typeEvaluator, finalPosition);
        animator.setDuration(1000);
        animator.start();
    }

    private void removeAllKittenMarkers() {
        for (Marker kittyMarker : kittenMarkers) {
            kittyMarker.remove();
        }
        kittenMarkers.clear();
        kittenCounter = 0;
    }
    // endregion

    //region Route drawing
    private class DrawRoute extends AsyncTask<LatLng, Void, PolylineOptions> {
        @Override
        protected PolylineOptions doInBackground(LatLng... location) {
            LatLng startLocation = HIOF;
            if (location[0] != null)
                startLocation = location[0];

            GMapV2Direction mapDirection = new GMapV2Direction();

            Document doc = mapDirection.getDocument(startLocation, FREDRIKSTAD, GMapV2Direction.MODE_DRIVING, getString(R.string.directions_api));

            ArrayList<LatLng> directionPoint = mapDirection.getDirection(doc);
            PolylineOptions rectLine = new PolylineOptions().width(3).color(Color.BLUE);

            for (int i = 0; i < directionPoint.size(); i++) {
                rectLine.add(directionPoint.get(i));
            }

            return rectLine ;
        }

        @Override
        protected void onPostExecute(PolylineOptions result) {
            gMap.addPolyline(result);
        }
    }

    @AfterPermissionGranted(LOCATION_PERMISSION_ID)
    private void drawRouteFromCurrentLocation() {
        if (EasyPermissions.hasPermissions(this, locationPermission)) {
            LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            LatLng myPosition = new LatLng(location.getLatitude(), location.getLongitude());
            new DrawRoute().execute(myPosition);
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, getString(R.string.no_location_permission),
                    LOCATION_PERMISSION_ID, locationPermission);
        }
    }
    //endregion

    //region Set up menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.animateKittens):
                for (Marker kittenMarker : kittenMarkers)
                    animateMarker(kittenMarker, FREDRIKSTAD);
                break;
            case (R.id.drawRoute):
                drawRouteFromCurrentLocation();
                break;
            case R.id.removeKittens:
                removeAllKittenMarkers();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
    //endregion
}
