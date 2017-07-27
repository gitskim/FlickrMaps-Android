package com.example.patelneh.flickrmaps;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class FetchImgAsyncTask extends AsyncTask<String, Void, Bitmap> {

    @Override
    protected Bitmap doInBackground (String... url) {
        URL myurl = null;
            try {
                myurl = new URL(url[0]);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        Bitmap bmp = null;
        try {
            bmp = BitmapFactory.decodeStream(myurl.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmp;
    }

    @Override
    protected void onPostExecute(Bitmap result) {

    }
}