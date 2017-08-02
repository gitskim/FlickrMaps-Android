package com.example.patelneh.flickrmaps;

import android.os.Parcel;
import android.os.Parcelable;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import com.flickr4java.flickr.photos.SearchParameters;
import com.flickr4java.flickr.photos.geo.GeoInterface;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FlickrPhotos implements Parcelable {

        private static final String FLICKR_API_KEY ="31971628224dc17872db15bdf51fc6cc";
        private static final String FLICKR_SECRET = "2dcb543f349e5a8c";

        private List <String> photoTitlesList;
        private List <String> photoUrl;
        private List <Float> latitudeList;
        private List <Float> longitudeList;

        Flickr flickr = new Flickr(FLICKR_API_KEY, FLICKR_SECRET, new REST());


        public FlickrPhotos () {

            this.photoTitlesList = new ArrayList<>();
            this.photoUrl = new ArrayList<>();
            this.latitudeList = new ArrayList<>();
            this.longitudeList = new ArrayList<>();
        }


        public void init (String[] userInputTags) {
            List<String> photoIdList = new ArrayList<>();
            SearchParameters sp = new SearchParameters();

            sp.setTags(userInputTags);
            sp.setHasGeo(true);
            sp.setBBox("-180", "-90", "180", "90");
            sp.setAccuracy(16);

            try {
                //FIXME when setting a number to more than 10, it crashes ...
                //FIXME async task blocks the whole program ...
                //FIXME Exception Caused by: java.lang.ClassNotFoundException:
                // Didn't find class "java.beans.Introspector" on path:
                PhotoList photoList = flickr.getPhotosInterface().search(sp, 10, 5);

                for(Iterator iterator = photoList.iterator(); iterator.hasNext();) {
                    Photo img = (Photo) iterator.next();
                    photoIdList.add(img.getId());
                    photoTitlesList.add(img.getTitle());
                    photoUrl.add(img.getMediumUrl());
                }

                setLatLon(photoIdList);

            } catch (FlickrException e) {
                e.printStackTrace();
            }
        }

        private void setLatLon(List<String> photoId) {
            GeoInterface gi = flickr.getGeoInterface();

            try {

                for(String s : photoId) {
                    latitudeList.add(gi.getLocation(s).getLatitude());
                    longitudeList.add(gi.getLocation(s).getLongitude());
                }

            } catch (FlickrException e) {
                    e.printStackTrace();
                }
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
            return photoUrl;
        }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(this.photoTitlesList);
        dest.writeStringList(this.photoUrl);
        dest.writeList(this.latitudeList);
        dest.writeList(this.longitudeList);
    }

    protected FlickrPhotos(Parcel in) {
        this.photoTitlesList = in.createStringArrayList();
        this.photoUrl = in.createStringArrayList();
        this.latitudeList = new ArrayList<Float>();
        in.readList(this.latitudeList, Float.class.getClassLoader());
        this.longitudeList = new ArrayList<Float>();
        in.readList(this.longitudeList, Float.class.getClassLoader());
    }

    public static final Parcelable.Creator<FlickrPhotos> CREATOR = new Parcelable.Creator<FlickrPhotos>() {
        @Override
        public FlickrPhotos createFromParcel(Parcel source) {
            return new FlickrPhotos(source);
        }

        @Override
        public FlickrPhotos[] newArray(int size) {
            return new FlickrPhotos[size];
        }
    };
}
