package com.example.patelneh.testproject;


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
        this.flickrParcelable = new ArrayList<FlickrPhotos>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

    }


    public void showMap(View v){

        if(checkIfEmptyTags()){
            return;
        }

        new FlickrQuery().execute();

        Intent maps = new Intent(Main.this, MapView.class);
        maps.putParcelableArrayListExtra("FLICKR", flickrParcelable);


        maps.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplicationContext().startActivity(maps);

    }

    public void showList(View v){

        if(checkIfEmptyTags()){
            return;
        }

        new FlickrQuery().execute();

        Intent photoList = new Intent(Main.this, PhotoListActivity.class);
        photoList.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplicationContext().startActivity(photoList);

    }

    private class FlickrQuery extends AsyncTask <Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {

            FlickrPhotos fp = new FlickrPhotos();
            fp.init(tags);
            flickrParcelable.add(fp);
            return null;

        }

        @Override
        protected void onPostExecute(Void params) {
            super.onPostExecute(params);
            Log.d("STATUS", "COMPLETE");
        }
    }

    public boolean checkIfEmptyTags (){
        EditText et = new EditText(this);
        et = findViewById(R.id.search);
        String searchQuery = et.getText().toString();

        if(searchQuery.trim().isEmpty()){
            Toast.makeText(this, "Enter a value", Toast.LENGTH_SHORT).show();
            return true;
        }

        return false;
    }
    
}
