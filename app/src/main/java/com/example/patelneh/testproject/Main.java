package com.example.patelneh.testproject;


import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class Main extends Activity {

    List <Float> latitudeArray= new ArrayList<>();
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
        }

        startActivity(new Intent(this, TestProjMap.class));
    }

    public class FlickrQuery extends AsyncTask <Void, Void, Float[]> {

        @Override
        protected Float[] doInBackground(Void ... params) {
            FlickrPhotos fp = new FlickrPhotos();
            return fp.lat();
        }

        @Override
        protected void onPostExecute(Float[] aFloat) {
            super.onPostExecute(aFloat);
            for(int i =0; i<aFloat.length; i++){
                latitudeArray.add(aFloat[i]);
            }
        }
    }

    public void flickrCheck(View v) {
        new FlickrQuery().execute();
        Float [] lat = new Float[latitudeArray.size()];

        for (int i = 0; i<lat.length ; i++){
            String iter = "RESULT" +i;
            String val = Float.toString(lat[i]);
            Log.d(iter, val);
        }
    }
    
}
