package com.example.patelneh.testproject;


import android.app.Activity;
import android.content.Context;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

    }

    public void showOther(View v){
        EditText et = new EditText(this);
        et = findViewById(R.id.search);
        String searchQuery = et.getText().toString();

        if(searchQuery.trim().isEmpty()){
            Toast.makeText(this, "Enter a value", Toast.LENGTH_SHORT).show();
            return;
        }else {
            tags = searchQuery.split("\\s");
            for(int i = 0; i < tags.length ;i++){
                Log.d("TAG:" , tags[i]);
            }
        }
        new FlickrQuery().execute();

    }

    private class FlickrQuery extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            FlickrPhotos fp = new FlickrPhotos();
            fp.init(tags);

            titles =  new ArrayList<>(fp.getTitle());
            photoURL = new ArrayList<>(fp.getPhotoURL());
            latitude = new ArrayList<>(fp.getLat());
            longitude = new ArrayList<>(fp.getLon());

            return null;
        }

        @Override
        protected void onPostExecute(Void params) {
            super.onPostExecute(params);
            Log.d("STATUS", "COMPLETE");

            //Latitude Array
            float[] latArray = floatListToPrim(latitude);

            //Longitude Array
            float[] lonArray = floatListToPrim(longitude);

            //Titles Array
            String[] titleArray = new String[titles.size()];
            titles.toArray(titleArray);

            //Photo URL Array
            String [] photoURLArray = new String[photoURL.size()];
            photoURL.toArray(photoURLArray);


            Intent maps = new Intent(Main.this, TestProjMap.class);

            maps.putExtra("LATITUDE", latArray);
            maps.putExtra("LONGITUDE",lonArray);
            maps.putExtra("TITLES",titleArray);
            maps.putExtra("PHOTO_URL", photoURLArray);

            maps.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getApplicationContext().startActivity(maps);
        }
    }


    private float[] floatListToPrim (List<Float> fList){
        float[] prim = new float[fList.size()];

        for (int i = 0; i < prim.length; i++) {
            prim[i] = fList.get(i);
        }
        return prim;

    }
    
}
