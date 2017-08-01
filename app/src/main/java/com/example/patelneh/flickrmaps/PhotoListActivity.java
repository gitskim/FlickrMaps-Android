package com.example.patelneh.flickrmaps;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class PhotoListActivity extends AppCompatActivity {

    private List<FlickrPhotos> flickrParcelable;
    private List<Bitmap> flickrImgs = new ArrayList<>();
    private PhotoListAdapter photoListAdapter;
    private RecyclerView photoList;
    private ProgressBar pbLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.photo_list);

        photoList = (RecyclerView) findViewById(R.id.photoRecycler);


        Intent extras = getIntent();

        String [] userTags = extras.getStringArrayExtra("TAGS");

        try {
            flickrParcelable =  new FlickrAsyncTask().execute(userTags).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }finally {
        }

        for (String url :flickrParcelable.get(0).getPhotoURL()) {
            try {
                flickrImgs.add(new FetchImgAsyncTask().execute(url).get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }



        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
        photoList.setLayoutManager(layoutManager);
        photoList.setHasFixedSize(true);

        photoListAdapter = new PhotoListAdapter(flickrParcelable, flickrImgs);

        photoList.setAdapter(photoListAdapter);
    }
}