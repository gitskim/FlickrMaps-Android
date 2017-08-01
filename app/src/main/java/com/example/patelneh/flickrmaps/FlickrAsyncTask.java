package com.example.patelneh.flickrmaps;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;

import com.example.patelneh.flickrmaps.FlickrPhotos;

import java.net.NetworkInterface;
import java.util.ArrayList;

public class FlickrAsyncTask extends AsyncTask< String, Void, ArrayList<FlickrPhotos> > {

    private ArrayList<FlickrPhotos> flickrParcelable;


    @Override
    protected ArrayList<FlickrPhotos> doInBackground(String... userSearchTags) {
        flickrParcelable = new ArrayList<>();

        FlickrPhotos fp = new FlickrPhotos();
        fp.init(userSearchTags);
        flickrParcelable.add(fp);
        return flickrParcelable;

    }

    @Override
    protected void onPostExecute(ArrayList<FlickrPhotos> flickrArray ) {
        Log.d("fAsyTask doinBackground", "COMPLETE");
    }
}