package com.example.patelneh.flickrmaps;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class PhotosTest {

    Photos photos = new Photos();

    @Test
    public void whenLatitudeListIsGiven_getsLatitueList_sizeOfTheListIsSame() {
        // GIVEN
        List<Float> latitudeList = photos.getLatitudeList();
        photos.setLatitudeList(latitudeList);

        // WHEN
        List<Float> result = photos.getLatitudeList();

        // THEN
        assert result.size() == latitudeList.size();
    }

    @Test
    public void givenImageUrl_getsPhotoUrlList_getsPhotoUrl() {

        // GIVEN
        String url = "http://test.jpg";

        // WHEN
        List<String> urlList = photos.getPhotoUrl();
        urlList.add(url);

        // THEN
        assert photos.getPhotoUrl().get(0) == url;
    }

    @Test
    public void givenTitleListOfStrings_setsTitleListtoPhotosTitleList_sizeOfListAreEqual() {
        // GIVEN
        List<String> testList = new ArrayList<>();

        // WHEN
        photos.setPhotoTitlesList(testList);

        // THEN
        assert photos.getPhotoTitlesList().size() == testList.size();
    }

    @Test
    public void givenLongitudeListOfFloats_setsLongitudeListToPhotosLongList_sizeOfListsAreEqual(){
        // GIVEN
        List<Float> longitudeList = new ArrayList<>();

        //WHEN
        photos.setLongitudeList(longitudeList);

        // THEN
        assert photos.getLongitudeList().size() == longitudeList.size();
    }
}
