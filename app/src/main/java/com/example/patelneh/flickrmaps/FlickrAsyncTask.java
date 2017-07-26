package com.example.patelneh.flickrmaps;

import android.os.AsyncTask;
import android.util.Log;

import com.example.patelneh.flickrmaps.FlickrPhotos;

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