package com.example.patelneh.testproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.ui.IconGenerator;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MapView extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private List<FlickrPhotos> flickrParcelable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Values provided from the Main class
        Intent extras = getIntent();
        flickrParcelable = new ArrayList<FlickrPhotos>(extras.<FlickrPhotos>getParcelableArrayListExtra("FLICKR"));

//        latitude = floatArrayToDouble(extras.getFloatArrayExtra("LATITUDE"));
//        longitude = floatArrayToDouble(extras.getFloatArrayExtra("LONGITUDE"));
//        titles = extras.getStringArrayExtra("TITLES");
//        photoURL = extras.getStringArrayExtra("PHOTO_URL");

        setContentView(R.layout.map);

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

        try {
            flickrMarkers(
                    googleMap ,
                    floatListToDouble(flickrParcelable.get(0).getLat()) ,
                    floatListToDouble(flickrParcelable.get(0).getLon()) ,
                    flickrParcelable.get(0).getTitle() ,
                    flickrParcelable.get(0).getPhotoURL()
            );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(0,0)));
    }



    private void flickrMarkers (GoogleMap gm, double[] lat , double[] lon, List<String> title, List<String> url ) throws MalformedURLException {
        int arrayLength = 0;
        if(lat.length == lon.length && lat.length == title.size() && lat.length == url.size()){
            arrayLength = lat.length;
        }else{
            Log.d("ERR", "UNEVEN ARRAY SIZES");
            return;
        }

        IconGenerator ig = new IconGenerator(this);
        Bitmap bmp;

        for(int i = 0 ; i < arrayLength ; i++){
            LatLng position = new LatLng(lat[i], lon[i]);
            bmp = loadImageFromWeb(url.get(i));
            gm.addMarker(new MarkerOptions().position(position).icon(BitmapDescriptorFactory.fromBitmap(bmp)));
        }

    }

    private  double[] floatListToDouble(List<Float> fList){
        if(fList == null){
            Log.d("FLOAT ARRAY", "EMPTY");
        }
        double[] dArray = new double[fList.size()];
        for(int i =0 ; i < fList.size() ; i++){
            dArray[i] = fList.get(i);
        }
        return dArray;
    }

    public static Bitmap loadImageFromWeb(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Bitmap b = BitmapFactory.decodeStream(is);
            Log.d("DRAWABLE", "COMPLETE");
            return b;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
