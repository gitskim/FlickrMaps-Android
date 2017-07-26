package com.example.patelneh.flickrmaps;


import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class Main extends Activity {

    public String[] tags;
    public List<String> titles;
    public List<String> photoURL;
    public List<Float> latitude;
    public List<Float> longitude;
    public ArrayList<FlickrPhotos> flickrParcelable;

    public Main (){
        this.flickrParcelable = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

    }


    public void showMap(View v){

        setTags();

        if(tags.length == 0){
            Toast.makeText(this, "Enter a value", Toast.LENGTH_SHORT).show();
            return;
        }



        EditText et = new EditText(this).findViewById(R.id.search);
        String searchQuery = et.getText().toString();

        tags = searchQuery.split("\\s");
//        new FlickrQuery().execute();

        Intent maps = new Intent(Main.this, MapView.class);
        maps.putParcelableArrayListExtra("FLICKR", flickrParcelable);


        maps.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplicationContext().startActivity(maps);

    }

    public void showList(View v){

        setTags();

        if(tags.length == 0){
            Toast.makeText(this, "Enter a value", Toast.LENGTH_SHORT).show();
            return;
        }

        //MOVE THIS OUTSIDE TO THE APPROPRIATE CLASSES.... RUN IT THERE... AND
        //FOR THE EXTRAS... ADD THE STRING[] TAGS SO IT COULD BE USED ON THE ASYNC TASK

        Intent photoList = new Intent(Main.this, PhotoListActivity.class);
        photoList.putExtra("TAGS", tags);
        photoList.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplicationContext().startActivity(photoList);

    }

    public void setTags() {
        EditText et = new EditText(this);
        et = findViewById(R.id.search);
        String searchQuery = et.getText().toString();

        tags = searchQuery.split("\\s");

    }
    
}
