package com.example.patelneh.flickrmaps;

import android.app.Activity;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;

import com.flickr4java.flickr.photos.PhotoList;

import org.apache.log4j.chainsaw.Main;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;
import org.robolectric.shadows.ShadowToast;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class MainActivityTest {
    private MainActivity mainActivity;

    @Before
    public void setup(){
         mainActivity = Robolectric.setupActivity(MainActivity.class);

    }

    @Test
    public void givenTagWithWhitespace_showList_splitValueEqualsArrayTagValue() {

        // GIVEN
        Button showListButton = mainActivity.findViewById(R.id.show_list_button);
        EditText editText = mainActivity.findViewById(R.id.search);
        editText.setText("Pizza Bagels");
        String[] result = editText.getText().toString().split("\\s");

        // WHEN
        showListButton.performClick();
        String mainActivityResult = mainActivity.getTags()[0];

        // THEN
        assertTrue(result[0].equals(mainActivityResult));
    }

    @Test
    public void emptyString_splitTheValuesAndPushToArray_arrayLengthIsOne(){
        // GIVEN
        String emptyString = "";
        String [] testArray;

        // WHEN
        testArray = emptyString.split("\\s");

        //THEN
        assertTrue(testArray.length == 1);
    }

    @Test
    public void noTagsAreGiven_showList_tagsArrayIsEmpty() {
        // GIVEN
        Button showListButton = mainActivity.findViewById(R.id.show_list_button);


        //WHEN
        showListButton.performClick();

        //THEN
        assertTrue(mainActivity.getTags() == null);
    }

    @Test
    public void tagsGivenAfterShowListCalledOnce_showListCalledAgain_tagsArrayIsNotNull(){
        // GIVEN
        Button showListButton = mainActivity.findViewById(R.id.show_list_button);
        showListButton.performClick();
        EditText editText = mainActivity.findViewById(R.id.search);
        editText.setText("Pizza Bagels");

        //WHEN
        showListButton.performClick();


        //THEN
        assertTrue(mainActivity.getTags() != null);

    }

    @Test
    public void noTagsAreGiven_showList_toastMessagePromptsToEnterValue (){
        //GIVEN
        Button showListButton = mainActivity.findViewById(R.id.show_list_button);


        //WHEN
        showListButton.performClick();

        //THEN
        assertTrue(ShadowToast.showedToast("Enter a value"));
    }

    @Test
    public void tagsAreSet_showListClickedExtrasAreSet_intentForPhotoListIsCreated(){
        // GIVEN
        Button showListButton = mainActivity.findViewById(R.id.show_list_button);
        EditText editText = mainActivity.findViewById(R.id.search);
        editText.setText("Pizza Bagels");
        mainActivity.setTags();

        // WHEN
        showListButton.performClick();
        Intent intent = new Intent(mainActivity, PhotoListActivity.class);
        intent.putExtra("TAGS", mainActivity.getTags());
        //THEN
        Intent shadowActivityIntent = Shadows.shadowOf(mainActivity).getNextStartedActivity();
        assert shadowActivityIntent.equals(intent);
//        mainActivity.getApplicationContext().startActivity();

    }

    //TODO Test similar code with showMap (If possible refactor code for re-use).

    @Test
    public void givenTagWithWhitespace_showMap_splitValueEqualsArrayTagValue() {

        // GIVEN
        Button showMapButton = mainActivity.findViewById(R.id.show_map_button);
        EditText editText = mainActivity.findViewById(R.id.search);
        editText.setText("Pizza Bagels");
        String[] result = editText.getText().toString().split("\\s");

        // WHEN
        showMapButton.performClick();
        String mainActivityResult = mainActivity.getTags()[0];

        // THEN
        assertTrue(result[0].equals(mainActivityResult));
    }

    @Test
    public void noTagsAreGiven_showMap_tagsArrayIsEmpty() {
        // GIVEN
        Button showMapButton = mainActivity.findViewById(R.id.show_map_button);


        //WHEN
        showMapButton.performClick();

        //THEN
        assertTrue(mainActivity.getTags() == null);
    }

    @Test
    public void tagsGivenAfterShowMapCalledOnce_showMapCalledAgain_tagsArrayIsNotNull(){
        // GIVEN
        Button showMapButton = mainActivity.findViewById(R.id.show_map_button);
        showMapButton.performClick();
        EditText editText = mainActivity.findViewById(R.id.search);
        editText.setText("Pizza Bagels");

        //WHEN
        showMapButton.performClick();


        //THEN
        assertTrue(mainActivity.getTags() != null);

    }

    @Test
    public void noTagsAreGiven_showMap_toastMessagePromptsToEnterValue (){
        //GIVEN
        Button showMapButton = mainActivity.findViewById(R.id.show_map_button);


        //WHEN
        showMapButton.performClick();

        //THEN
        assertTrue(ShadowToast.showedToast("Enter a value"));
    }

    @Test
    public void tagsAreSet_showMapClickedExtrasAreSet_intentForPhotoListIsCreated(){
        // GIVEN
        Button showMapButton = mainActivity.findViewById(R.id.show_map_button);
        EditText editText = mainActivity.findViewById(R.id.search);
        editText.setText("Pizza Bagels");
        mainActivity.setTags();

        // WHEN
        showMapButton.performClick();
        Intent intent = new Intent(mainActivity, MapView.class);
        intent.putExtra("TAGS", mainActivity.getTags());
        //THEN
        Intent shadowActivityIntent = Shadows.shadowOf(mainActivity).getNextStartedActivity();
        assert shadowActivityIntent.equals(intent);
//        mainActivity.getApplicationContext().startActivity();

    }



}