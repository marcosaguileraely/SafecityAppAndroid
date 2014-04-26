package com.cool4code.safecity.safecity;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.Parse;
import com.parse.ParseObject;


public class HomeActivity extends FragmentActivity implements OnClickListener {
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Your Application ID and Client Key are defined elsewhere
        ParseObject.registerSubclass(socialPost.class);
        Parse.initialize(this, "XdWuoWJKZ4rL1hv2lGfgB9IP4fy5po65xayBwwNW", "Ky1GlA3n4jiEnTTLreBjDzo94J5tLshdnZ9sDR1e");
        setContentView(R.layout.activity_home);
        View insertButton= findViewById(R.id.reportButton);
        insertButton.setOnClickListener(this);
        setUpMapIfNeeded();
        GoogleMap map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        map.setMyLocationEnabled(true);

        Criteria criteria = new Criteria();
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        String provider = locationManager.getBestProvider(criteria, false);
        Location location = locationManager.getLastKnownLocation(provider);
        double lat =  location.getLatitude();
        double lng = location.getLongitude();
        LatLng coordinate = new LatLng(lat, lng);
        Log.d("Coordenadas","latlong");
        CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 15);
        map.animateCamera(yourLocation);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }


    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }

    @Override
    public void onClick(View v) {
        Log.d("MyApp", "I am here");
        Intent intent = new Intent(HomeActivity.this, SignUpActivity.class);
        startActivity(intent);
        /*EditText commentBox = (EditText) findViewById(R.id.comment);
        socialPost sp= new socialPost();
        sp.setDescription(commentBox.getText().toString());
        sp.saveEventually();*/
        Log.d("MyApp", "Ends here!");
    }
}
