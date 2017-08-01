package com.example.patelneh.flickrmaps;


import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class Main extends Activity {

    private String[] tags;
    private List<String> titles;
    private List<String> photoURL;
    private List<Float> latitude;
    private List<Float> longitude;
    private ArrayList<FlickrPhotos> flickrParcelable;


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

        Intent maps = new Intent(Main.this, MapView.class);
        maps.putExtra("TAGS", tags);

        maps.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplicationContext().startActivity(maps);


    }

    public void showList(View v){

        setTags();

        if(tags.length == 0){
            Toast.makeText(this, "Enter a value", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent photoList = new Intent(Main.this, PhotoListActivity.class);
        photoList.putExtra("TAGS", tags);
        getApplicationContext().startActivity(photoList);

    }

    public void setTags() {
        EditText et = new EditText(this);
        et = findViewById(R.id.search);
        String searchQuery = et.getText().toString();

        tags = searchQuery.split("\\s");

    }
    
}
