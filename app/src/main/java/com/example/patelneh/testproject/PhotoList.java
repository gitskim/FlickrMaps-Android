package com.example.patelneh.testproject;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.flickr4java.flickr.photos.Photo;

import java.util.ArrayList;
import java.util.List;

public class PhotoList extends AppCompatActivity {

    private List<String> photoTitles;
    private PhotoListAdapter photoListAdapter;
    private RecyclerView photoList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_list);
        //Get image titles from Main
        Intent extras = getIntent();
        photoTitles = new ArrayList<>(extras.getStringArrayListExtra("TITLE"));

        photoList = (RecyclerView) findViewById(R.id.photoRecycler); //Recycler declaration

        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        photoList.setLayoutManager(layoutManager); //Sets the type of layout manager for the recycler view
        photoList.setHasFixedSize(true);

        photoListAdapter = new PhotoListAdapter(photoTitles); //Adapter declaration

        photoList.setAdapter(photoListAdapter);  //SETS THE ADAPTER TO THE RECYCLER VIEW
    }
}
