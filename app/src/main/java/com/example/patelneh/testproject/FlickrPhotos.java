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

        private List <String> photoTitlesList = new ArrayList<>();
        private List <Float> latitudeList;
        private List <Float> longitudeList;


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
                    photoTitlesList.add(img.getTitle()); //Add img titles to ArrayList
                }

                String[] photoIds = new String[photoIdList.size()];
                photoIdList.toArray(photoIds);

                setLat(photoIds); //Adds latitude to ArrayList
                setLon(photoIds); //Adds longitude to ArrayList

            } catch (FlickrException e) {
                e.printStackTrace();
            }

        }

        private void setLat(String[] photoId) {
            List <Float> latitudePoints = new ArrayList<>();
            GeoInterface gi = flickr.getGeoInterface();

            try {
                for(String s : photoId) {
                    latitudePoints.add(gi.getLocation(s).getLatitude());
                }
            } catch (FlickrException e) {
                    e.printStackTrace();
                }
            latitudeList = new ArrayList<>(latitudePoints);
        }

        private void setLon(String[] photoId) {
            List <Float> longitudePoints = new ArrayList<>();
            GeoInterface gi = flickr.getGeoInterface();

            try {
                for(String s : photoId) {
                    longitudePoints.add(gi.getLocation(s).getLatitude());
                }
            } catch (FlickrException e) {
                e.printStackTrace();
            }
            longitudeList = new ArrayList<>(longitudePoints);
        }

        public List<Float> getLat(){
            return latitudeList;
        }

        public List<Float> getLon(){
            return longitudeList;
        }

        public List<String> getTitle(){
            return photoTitlesList;
        }

        //Turn Setters into Voids
        //Have setters set the values of the private variables
        //Create getters for the private variables
        //Have the Main activity use getters and store variables locally
        //Check if the values can be displayed via LOOP
}
