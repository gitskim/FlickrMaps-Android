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
        private List <String> photoURL = new ArrayList<>();
        private List <Float> latitudeList;
        private List <Float> longitudeList;


        Flickr flickr = new Flickr(FLICKR_API_KEY, FLICKR_SECRET, new REST());


        public void init (String[] userInputTags) {
            List<String> photoIdList = new ArrayList<>();
            String[] tags = userInputTags;
            SearchParameters sp = new SearchParameters();

            sp.setTags(tags);
            sp.setHasGeo(true);
            sp.setBBox("-180", "-90", "180", "90");
            sp.setAccuracy(16);

            try {
                PhotoList photoList = flickr.getPhotosInterface().search(sp, 10, 5);

                for(Iterator iterator = photoList.iterator(); iterator.hasNext();) {
                    Photo img = (Photo) iterator.next();
                    photoIdList.add(img.getId());
                    photoTitlesList.add(img.getTitle()); //Add img titles to ArrayList
                    photoURL.add(img.getMediumUrl()); //Adds img URLs to ArrayList
                }

                String[] photoIds = new String[photoIdList.size()];
                photoIdList.toArray(photoIds);

                setLatLon(photoIds); //Adds latitude/longitude to ArrayList

            } catch (FlickrException e) {
                e.printStackTrace();
            }
        }

        private void setLatLon(String[] photoId) {
            List <Float> latitudePoints = new ArrayList<>();
            List <Float> longitudePoints = new ArrayList<>();
            GeoInterface gi = flickr.getGeoInterface();

            try {
                for(String s : photoId) {
                    latitudePoints.add(gi.getLocation(s).getLatitude());
                    longitudePoints.add(gi.getLocation(s).getLongitude());
                }
            } catch (FlickrException e) {
                    e.printStackTrace();
                }
            latitudeList = new ArrayList<>(latitudePoints);
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
        public List<String> getPhotoURL(){
            return photoURL;
        }
}
