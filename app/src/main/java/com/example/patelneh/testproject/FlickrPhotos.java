package com.example.patelneh.testproject;

import android.util.Log;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.photos.GeoData;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import com.flickr4java.flickr.photos.SearchParameters;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FlickrPhotos {

        private final String FLICKR_API_KEY ="31971628224dc17872db15bdf51fc6cc";
        private final String FLICKR_SECRET = "2dcb543f349e5a8c";

        public Float[] latitudePoints;
        public Float[] longitudePoints;
        public String[] photoTitles;
        //Store photos in either a Photo[] or String[]

        Flickr flickr = new Flickr(FLICKR_API_KEY, FLICKR_SECRET, new REST());


        public Float[] lat (){
            List<Float> latitude = new ArrayList<>();
            String[] tags = new String[] {"Food"}; //TEST VALUE

            SearchParameters sp = new SearchParameters();
            sp.setTags(tags);

            try {
                PhotoList photoList = flickr.getPhotosInterface().search(sp, 6, 1);
                for(Iterator iterator = photoList.iterator(); iterator.hasNext();){
                    Photo img = (Photo) iterator.next();
                    if(img.getGeoData().getLatitude() == 0.0) {
                        Log.d("ERR", "NULL");
                    } else{
                        String s = Float.toString(img.getGeoData().getLatitude());
                        Log.d("LAT", s);

                    }
//                    latitude.add(img.getGeoData().getLatitude());
                }

                //GET LONG VALUES

            } catch (FlickrException e) {
                e.printStackTrace();
            }
            Float[] latitudePoints = new Float[latitude.size()];
            return latitude.toArray(latitudePoints);
        }

}
