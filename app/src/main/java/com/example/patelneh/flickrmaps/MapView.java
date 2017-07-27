package com.example.patelneh.flickrmaps;

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

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MapView extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private List<FlickrPhotos> flickrParcelable;

    private List<Bitmap> flickrImgs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent extras = getIntent();
        String [] userTags = extras.getStringArrayExtra("TAGS");

        try {
            flickrParcelable = new FlickrAsyncTask().execute(userTags).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        for (String url :flickrParcelable.get(0).getPhotoURL()) {
            try {
                flickrImgs.add(new FetchImgAsyncTask().execute(url).get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        setContentView(R.layout.map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


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
                gm.addMarker(new MarkerOptions().position(position).title(title.get(i)).icon(BitmapDescriptorFactory.fromBitmap(flickrImgs.get(i))));

        }

    }

    private double[] floatListToDouble(List<Float> fList){
        if(fList == null){
            Log.d("FLOAT ARRAY", "EMPTY");
        }
        double[] dArray = new double[fList.size()];
        for(int i = 0 ; i < fList.size() ; i++){
            dArray[i] = fList.get(i);
        }
        return dArray;
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
