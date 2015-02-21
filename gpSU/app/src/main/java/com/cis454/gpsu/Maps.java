package com.cis454.gpsu;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Maps extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();

        if (mMap != null) {
            mMap.setMyLocationEnabled(true);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link com.google.android.gms.maps.SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                        .getMap();
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker").snippet("snippet"));

        //Enable MyLocation Layer of Google Map
        mMap.setMyLocationEnabled(true);

        //Get LocationManager object from System Service LOCATION_SERVICE
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        //Create a criteria object to retrieve provider
        Criteria criteria = new Criteria();

        //Get the name of the best provider
        String provider = locationManager.getBestProvider(criteria, true);

        //Get Current Location
        Location myLocation = locationManager.getLastKnownLocation(provider);
        if(myLocation == null) {

        }

        //set map type
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        //Get latitude of the current location
        double latitude = 43.0377; //myLocation.getLatitude();

        //Get longitude of the current location
        double longitude = -76.1340; //myLocation.getLongitude();

        //Create LatLng object for the current location
        LatLng latLng = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());

        //Show the current location in Google Map
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

        //Zoom in the Google Map
        mMap.animateCamera(CameraUpdateFactory.zoomTo(7));
      /*  mMap.addMarker(new MarkerOptions().position(new LatLng(latitude,longitude))
                .title("You are here!").snippet("Consider yourself located"));
*/

        LatLng myCoordinates = new LatLng(latitude, longitude);
        CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(myCoordinates, 10);
        mMap.animateCamera(yourLocation);

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(myCoordinates)      // Sets the center of the map to LatLng (refer to previous snippet)
                .zoom(7)                   // Sets the zoom
                .bearing(0)                // Sets the orientation of the camera to north
                .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition)); //moves camera to user's position

    }
}
