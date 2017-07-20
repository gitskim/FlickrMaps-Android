package com.example.patelneh.testproject;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.flickr4java.flickr.photos.Photo;

import java.util.ArrayList;
import java.util.List;

public class PhotoList extends AppCompatActivity{

    private List<String> photoTitles;
    private PhotoListAdapter photoListAdapter;
    private RecyclerView photoList;


    public PhotoList(List<String> photoTitles){
        this.photoTitles = new ArrayList<>(photoTitles);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_list);

        photoList = (RecyclerView) findViewById(R.id.photoRecycler); //Gets the recyclerView
        LinearLayoutManager layoutManager= new LinearLayoutManager(this);

        photoList.setLayoutManager(layoutManager); //Sets the type of layout manager for the recycler view
        photoList.setHasFixedSize(true);

        photoListAdapter = new PhotoListAdapter(photoTitles); //Gets the list adapter... The adapter sets the content in each item

        photoList.setAdapter(photoListAdapter);  //SETS THE ADAPTER TO THE RECYCLER VIEW!

    }
}
