package com.example.patelneh.testproject;

import android.util.Log;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.photos.PhotoList;
import com.flickr4java.flickr.photos.SearchParameters;

import java.util.ArrayList;
import java.util.List;

public class FlickrPhotos {

        private final String FLICKR_API_KEY ="31971628224dc17872db15bdf51fc6cc";
        private final String FLICKR_SECRET = "2dcb543f349e5a8c";

        Flickr flickr = new Flickr(FLICKR_API_KEY, FLICKR_SECRET, new REST());


        public void lat (){
            List<Long> latitude = new ArrayList<>();
            String[] tags = new String[] {"Food"}; //TEST VALUE

            SearchParameters sp = new SearchParameters();
            sp.setTags(tags);

            Log.d(FLICKR_SECRET, "SUCCESS");

            try {
                PhotoList photoList = flickr.getPhotosInterface().search(sp, 100, 1);


            } catch (FlickrException e) {
                e.printStackTrace();
            }

        }

        //public void lon(){}
        //public void title(){}
        //public void photoID(){}
}
