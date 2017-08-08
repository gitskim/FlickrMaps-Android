package com.example.patelneh.flickrmaps;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by patelneh on 8/3/17.
 */

public class Photos {

    private List<String> photoTitlesList;
    private List <String> photoUrl;
    private List <Float> latitudeList;
    private List <Float> longitudeList;

    public Photos() {

        this.photoTitlesList = new ArrayList<>();
        this.photoUrl = new ArrayList<>();
        this.latitudeList = new ArrayList<>();
        this.longitudeList = new ArrayList<>();
    }

    public List<String> getPhotoTitlesList() {
        return photoTitlesList;
    }

    public void setPhotoTitlesList(List<String> photoTitlesList) {
        this.photoTitlesList = photoTitlesList;
    }

    public List<String> getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(List<String> photoUrl) {
        this.photoUrl = photoUrl;
    }

    public List<Float> getLatitudeList() {
        return latitudeList;
    }

    public void setLatitudeList(List<Float> latitudeList) {
        this.latitudeList = latitudeList;
    }

    public List<Float> getLongitudeList() {
        return longitudeList;
    }

    public void setLongitudeList(List<Float> longitudeList) {
        this.longitudeList = longitudeList;
    }
}
