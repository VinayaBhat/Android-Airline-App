package edu.pdx.cs401j.airlineapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.fakes.RoboMenuItem;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowAlertDialog;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

import static org.robolectric.Shadows.shadowOf;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {Build.VERSION_CODES.O_MR1})
public class MainActivityUnitTest{
    MainActivity activity;
    Button AddFlight;
    Button SearchFlight;
    EditText AirlineName;
    EditText Src;
    EditText Dest;
    Button prettyPrint;
    @Before
    public void SetUp(){

         activity = Robolectric.buildActivity(MainActivity.class).create()
                .start()
                .resume()
                .visible()
                .get();
         AddFlight = (Button) activity.findViewById(R.id.AddNewFlight);
        SearchFlight = (Button) activity.findViewById(R.id.SearchFlight);
        prettyPrint = (Button) activity.findViewById(R.id.PP);
    }

    /**
     * Add Flight navigates to next page
     */
    @Test
    public void AddFlight_NextActivity_Success(){
        AddFlight.performClick();
        Intent expectedIntent = new Intent(activity, AddFlightPage.class);
        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        assertTrue(actualIntent.filterEquals(expectedIntent));
    }

    /**
     * Search Flight with no input
     */
    @Test
    public void SearchFlight_NoInput_Error() {
        SearchFlight.performClick();
        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();
        assertTrue(dialog.isShowing());
        ShadowAlertDialog sAlert = shadowOf(dialog);
        assertThat(sAlert.getMessage().toString(),equalTo("Airline Name not provided"));
    }
    /**
     * Search Flight with dummy airline
     */
    @Test
    public void SearchFlight_DummyAirline_Error() {
        AirlineName=(EditText) activity.findViewById(R.id.AirlineNameField);
        AirlineName.setText("DummyAirline");
        SearchFlight.performClick();
        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();
        assertTrue(dialog.isShowing());
        ShadowAlertDialog sAlert = shadowOf(dialog);
        assertThat(sAlert.getMessage().toString(),equalTo("Airline not found in App. Please check Airline Name entered"));
    }
    /**
     * Search Flight with no destination
     */
    @Test
    public void SearchFlight_NoDestination_Error() {
        AirlineName=(EditText) activity.findViewById(R.id.AirlineNameField);
        AirlineName.setText("DummyAirline");
        Src=(EditText) activity.findViewById(R.id.SourceField);
        Src.setText("JFK");
        Dest=(EditText) activity.findViewById(R.id.DestinationField);
        SearchFlight.performClick();
        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();
        assertTrue(dialog.isShowing());
        ShadowAlertDialog sAlert = shadowOf(dialog);
        assertThat(sAlert.getMessage().toString(),equalTo("Source Code is given but destination code is not given."));
    }
    /**
     * Search Flight with no source
     */
    @Test
    public void SearchFlight_NoSource_Error() {
        AirlineName=(EditText) activity.findViewById(R.id.AirlineNameField);
        AirlineName.setText("DummyAirline");
        Src=(EditText) activity.findViewById(R.id.SourceField);
        Dest=(EditText) activity.findViewById(R.id.DestinationField);
        Dest.setText("JFK");
        SearchFlight.performClick();
        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();
        assertTrue(dialog.isShowing());
        ShadowAlertDialog sAlert = shadowOf(dialog);
        assertThat(sAlert.getMessage().toString(),equalTo("Source Code is not given but destination code is given."));
    }
    /**
     * Search Flight with invalid source
     */
    @Test
    public void SearchFlight_InvalidSource_Error() {
        AirlineName=(EditText) activity.findViewById(R.id.AirlineNameField);
        AirlineName.setText("DummyAirline");
        Src=(EditText) activity.findViewById(R.id.SourceField);
        Src.setText("AAA");
        Dest=(EditText) activity.findViewById(R.id.DestinationField);
        Dest.setText("JFK");
        SearchFlight.performClick();
        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();
        assertTrue(dialog.isShowing());
        ShadowAlertDialog sAlert = shadowOf(dialog);
        assertThat(sAlert.getMessage().toString(),equalTo("Source Code not valid!"));
    }
    /**
     * Search Flight with invalid destination
     */
    @Test
    public void SearchFlight_InvalidDestination_Error() {
        AirlineName=(EditText) activity.findViewById(R.id.AirlineNameField);
        AirlineName.setText("DummyAirline");
        Src=(EditText) activity.findViewById(R.id.SourceField);
        Src.setText("JFK");
        Dest=(EditText) activity.findViewById(R.id.DestinationField);
        Dest.setText("AAA");
        SearchFlight.performClick();
        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();
        assertTrue(dialog.isShowing());
        ShadowAlertDialog sAlert = shadowOf(dialog);
        assertThat(sAlert.getMessage().toString(),equalTo("Destination Code not valid!"));
    }
    /**
     * Pretty Print Search Flight with no input
     */
    @Test
    public void PrettyPrintSearchFlight_NoInput_Error() {
        prettyPrint.performClick();
        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();
        assertTrue(dialog.isShowing());
        ShadowAlertDialog sAlert = shadowOf(dialog);
        assertThat(sAlert.getMessage().toString(),equalTo("Airline Name not provided"));
    }
    /**
     * Pretty Print Search Flight with dummy airline
     */
    @Test
    public void PrettyPrintSearchFlight_DummyAirline_Error() {
        AirlineName=(EditText) activity.findViewById(R.id.AirlineNameField);
        AirlineName.setText("DummyAirline");
        prettyPrint.performClick();
        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();
        assertTrue(dialog.isShowing());
        ShadowAlertDialog sAlert = shadowOf(dialog);
        assertThat(sAlert.getMessage().toString(),equalTo("Airline not found in App. Please check Airline Name entered"));
    }
    /**
     * Pretty Print Search Flight with no destination
     */
    @Test
    public void PrettyPrintSearchFlight_NoDestination_Error() {
        AirlineName=(EditText) activity.findViewById(R.id.AirlineNameField);
        AirlineName.setText("DummyAirline");
        Src=(EditText) activity.findViewById(R.id.SourceField);
        Src.setText("JFK");
        Dest=(EditText) activity.findViewById(R.id.DestinationField);
        prettyPrint.performClick();
        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();
        assertTrue(dialog.isShowing());
        ShadowAlertDialog sAlert = shadowOf(dialog);
        assertThat(sAlert.getMessage().toString(),equalTo("Source Code is given but destination code is not given."));
    }
    /**
     * Pretty Print Search Flight with no source
     */
    @Test
    public void PrettyPrintSearchFlight_NoSource_Error() {
        AirlineName=(EditText) activity.findViewById(R.id.AirlineNameField);
        AirlineName.setText("DummyAirline");
        Src=(EditText) activity.findViewById(R.id.SourceField);
        Dest=(EditText) activity.findViewById(R.id.DestinationField);
        Dest.setText("JFK");
        prettyPrint.performClick();
        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();
        assertTrue(dialog.isShowing());
        ShadowAlertDialog sAlert = shadowOf(dialog);
        assertThat(sAlert.getMessage().toString(),equalTo("Source Code is not given but destination code is given."));
    }
    /**
     * Pretty Print Search Flight with invalid source
     */
    @Test
    public void PrettyPrintSearchFlight_InvalidSource_Error() {
        AirlineName=(EditText) activity.findViewById(R.id.AirlineNameField);
        AirlineName.setText("DummyAirline");
        Src=(EditText) activity.findViewById(R.id.SourceField);
        Src.setText("AAA");
        Dest=(EditText) activity.findViewById(R.id.DestinationField);
        Dest.setText("JFK");
        prettyPrint.performClick();
        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();
        assertTrue(dialog.isShowing());
        ShadowAlertDialog sAlert = shadowOf(dialog);
        assertThat(sAlert.getMessage().toString(),equalTo("Source Code not valid!"));
    }
    /**
     * Pretty Print Search Flight with invalid destination
     */
    @Test
    public void PrettyPrintSearchFlight_InvalidDestination_Error() {
        AirlineName=(EditText) activity.findViewById(R.id.AirlineNameField);
        AirlineName.setText("DummyAirline");
        Src=(EditText) activity.findViewById(R.id.SourceField);
        Src.setText("JFK");
        Dest=(EditText) activity.findViewById(R.id.DestinationField);
        Dest.setText("AAA");
        prettyPrint.performClick();
        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();
        assertTrue(dialog.isShowing());
        ShadowAlertDialog sAlert = shadowOf(dialog);
        assertThat(sAlert.getMessage().toString(),equalTo("Destination Code not valid!"));
    }
    /**
     * Help Menu Test
     */
    @Test
    public void helpTest(){
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