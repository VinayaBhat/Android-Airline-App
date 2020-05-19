package edu.pdx.cs401j.airlineapp;

import android.content.Intent;
import android.os.Build;
import android.view.MenuItem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.fakes.RoboMenuItem;
import org.robolectric.shadows.ShadowActivity;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {Build.VERSION_CODES.O_MR1})
public class READMETest {
    /**
     * Help Menu test
     */
    @Test
    public void helpTest(){
        README activity= Robolectric.buildActivity(README.class)
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

}
