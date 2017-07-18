package com.example.patelneh.testproject;

import android.util.Log;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.photos.GeoData;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import com.flickr4java.flickr.photos.PhotosInterface;
import com.flickr4java.flickr.photos.SearchParameters;
import com.flickr4java.flickr.photos.geo.GeoInterface;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class FlickrPhotos {

        private final String FLICKR_API_KEY ="31971628224dc17872db15bdf51fc6cc";
        private final String FLICKR_SECRET = "2dcb543f349e5a8c";

        private List <Float> latitudePoints;
        private List <Float> longitudePoints;
        private List <String> photoTitles;

        Flickr flickr = new Flickr(FLICKR_API_KEY, FLICKR_SECRET, new REST());


        public void init () {
            List<String> photoIdList = new ArrayList<>();
            String[] tags = new String[] {"Birds", "Life", "Danger"}; //TEST VALUE
            SearchParameters sp = new SearchParameters();

            sp.setTags(tags);
            sp.setHasGeo(true);

            try {
                PhotoList photoList = flickr.getPhotosInterface().search(sp, 10, 1);

                for(Iterator iterator = photoList.iterator(); iterator.hasNext();) {
                    Photo img = (Photo) iterator.next();
                    photoIdList.add(img.getId());
                }

                String[] photoIds = new String[photoIdList.size()];
                photoIdList.toArray(photoIds);

                setLat(photoIds);
                setLon(photoIds);

            } catch (FlickrException e) {
                e.printStackTrace();
            }

        }

        private List<Float> setLat(String[] photoId) {
            List <Float> latitudePoints = new ArrayList<>();
            GeoInterface gi = flickr.getGeoInterface();

            try {
                for(String s : photoId) {
                    latitudePoints.add(gi.getLocation(s).getLatitude());
                }
            }
                 catch (FlickrException e) {
                    e.printStackTrace();
                }

            return latitudePoints;
        }

        private List<Float> setLon(String[] photoId) {
            List <Float> longitudePoints = new ArrayList<>();
            GeoInterface gi = flickr.getGeoInterface();

            try {
                for(String s : photoId) {
                    longitudePoints.add(gi.getLocation(s).getLatitude());
                }
            }
            catch (FlickrException e) {
                e.printStackTrace();
            }

            return longitudePoints;
        }


}
