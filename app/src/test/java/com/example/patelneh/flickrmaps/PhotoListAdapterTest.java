package com.example.patelneh.flickrmaps;


import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)


public class PhotoListAdapterTest {
    private PhotoListAdapter photoListAdapter;
    private List<FlickrPhotos> flickrPhotosListTest;
    private List<Bitmap> bitmapListTest;

    @Before
    public void setup(){
        photoListAdapter = new PhotoListAdapter(flickrPhotosListTest, bitmapListTest );
    }
    

}
