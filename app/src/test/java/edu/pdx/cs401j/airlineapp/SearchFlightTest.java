package edu.pdx.cs401j.airlineapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.view.MenuItem;
import android.widget.Button;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.fakes.RoboMenuItem;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowAlertDialog;

import java.util.ArrayList;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {Build.VERSION_CODES.O_MR1})
public class SearchFlightTest {
    @Test
    public void OnlyAirlineProvided() {
        Intent intent = new Intent();
        ArrayList<String> flights=new ArrayList<>();
        flights.add("Alaska;123;PDX;1/1/00 1:30 AM;LAX;1/1/00 2:30 AM");
        intent.putStringArrayListExtra("airline",flights);
        Main3Activity activity= Robolectric.buildActivity(Main3Activity.class,intent)
                .create()
                .start()
                .resume()
                .visible()
                .get();
    }

    @Test
    public void AirlineSrcDestProvided() {
        Intent intent = new Intent();
        ArrayList<String> flights=new ArrayList<>();
        flights.add("Alaska;123;PDX;1/1/00 1:30 AM;LAX;1/1/00 2:30 AM");
        flights.add("Alaska;123;PDX;1/1/00 1:30 AM;LAX;1/1/00 2:30 AM");
        intent.putStringArrayListExtra("airline",flights);
        intent.putExtra("src","PDX");
        intent.putExtra("dest","LAX");
        Main3Activity activity= Robolectric.buildActivity(Main3Activity.class,intent)
                .create()
                .start()
                .resume()
                .visible()
                .get();


    }

    @Test
    public void StringNotFormatted_Error() {
        Intent intent = new Intent();
        ArrayList<String> flights=new ArrayList<>();
        flights.add("Alaska123;PDX;1/1/00 1:30 AM;LAX;1/1/00 2:30 AM");
        intent.putStringArrayListExtra("airline",flights);
        intent.putExtra("src","PDX");
        intent.putExtra("dest","LAX");
        Main3Activity activity= Robolectric.buildActivity(Main3Activity.class,intent)
                .create()
                .start()
                .resume()
                .visible()
                .get();
        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();
        assertTrue(dialog.isShowing());
        ShadowAlertDialog sAlert = shadowOf(dialog);
        assertThat(sAlert.getMessage().toString(),equalTo("Flight number is not set properly"));


    }

    @Test
    public void helpTest(){
        Intent intent = new Intent();
        ArrayList<String> flights=new ArrayList<>();
        flights.add("Alaska;123;PDX;1/1/00 1:30 AM;LAX;1/1/00 2:30 AM");
        intent.putStringArrayListExtra("airline",flights);
        intent.putExtra("src","PDX");
        intent.putExtra("dest","LAX");
        Main3Activity activity= Robolectric.buildActivity(Main3Activity.class,intent)
                .create()
                .start()
                .resume()
                .visible()
                .get();
        MenuItem menuItem = new RoboMenuItem(R.menu.help_menu);
        activity.onOptionsItemSelected(menuItem);
        MenuItem menuItem2 = new RoboMenuItem(R.id.readme);
        activity.onOptionsItemSelected(menuItem2);
        Intent expectedIntent = new Intent(activity, README.class);
        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        assertTrue(actualIntent.filterEquals(expectedIntent));
    }


    @Test
    public void NoFlight() {
        Intent intent = new Intent();
        ArrayList<String> flights=new ArrayList<>();
        intent.putStringArrayListExtra("airline",flights);
        Main3Activity activity= Robolectric.buildActivity(Main3Activity.class,intent)
                .create()
                .start()
                .resume()
                .visible()
                .get();
    }


    @Test
    public void NoFlightButSrcDestPresent() {
        Intent intent = new Intent();
        ArrayList<String> flights=new ArrayList<>();
        intent.putStringArrayListExtra("airline",flights);
        Main3Activity activity= Robolectric.buildActivity(Main3Activity.class,intent)
                .create()
                .start()
                .resume()
                .visible()
                .get();
         }

         @Test
    public void PrettyPrintTest(){
             Intent intent = new Intent();
             ArrayList<String> flights=new ArrayList<>();
             flights.add("Alaska123;PDX;1/1/00 1:30 AM;LAX;1/1/00 2:30 AM");
             intent.putStringArrayListExtra("airline",flights);
             intent.putExtra("src","PDX");
             intent.putExtra("dest","LAX");
             Main3Activity activity= Robolectric.buildActivity(Main3Activity.class,intent)
                     .create()
                     .start()
                     .resume()
                     .visible()
                     .get();
             Button PP=(Button)activity.findViewById(R.id.PrettyPrint);
             PP.performClick();
             Intent expectedIntent = new Intent(activity, SearchResult.class);
             ShadowActivity shadowActivity = Shadows.shadowOf(activity);
             Intent actualIntent = shadowActivity.getNextStartedActivity();
             assertTrue(actualIntent.filterEquals(expectedIntent));
         }

}
