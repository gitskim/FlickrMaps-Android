package com.example.patelneh.testproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.util.Log;

import com.flickr4java.flickr.*;

import com.flickr4java.flickr.photos.PhotoList;
import com.flickr4java.flickr.photos.SearchParameters;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.ui.IconGenerator;

import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TestProjMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    public double[] latitude;
    public double[] longitude;
    public String[] titles;
    public String[] photoURL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Values provided from the Main class
        Intent extras = getIntent();
        latitude = floatArrayToDouble(extras.getFloatArrayExtra("LATITUDE"));
        longitude = floatArrayToDouble(extras.getFloatArrayExtra("LONGITUDE"));
        titles = extras.getStringArrayExtra("TITLES");
        photoURL = extras.getStringArrayExtra("PHOTO_URL");

        setContentView(R.layout.activity_test_proj_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        try {
            flickrMarkers(googleMap , latitude , longitude, titles, photoURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(0,0)));

    }



    private void flickrMarkers (GoogleMap gm, double[] lat , double[] lon, String[] title, String[] Url ) throws MalformedURLException {
        int arrayLength = 0;
        if(lat.length == lon.length && lat.length == title.length && lat.length == Url.length){
            arrayLength = lat.length;
        }else{
            Log.d("ERR", "UNEVEN ARRAY SIZES");
            return;
        }

        IconGenerator ig = new IconGenerator(this);

        Bitmap bmp;

        for(int i = 0 ; i < arrayLength ; i++){
            LatLng position = new LatLng(latitude[i], longitude[i]);
            bmp = ig.makeIcon(title[i]);
            gm.addMarker(new MarkerOptions().position(position).icon(BitmapDescriptorFactory.fromBitmap(bmp)));
        }

    }

    private static double[] floatArrayToDouble(float[] fArray){
        if(fArray == null){
            Log.d("FLOAT ARRAY", "EMPTY");
        }
        double[] dArray = new double[fArray.length];
        for(int i =0 ; i < fArray.length ; i++){
            dArray[i] = fArray[i];
        }
        return dArray;
    }

}
