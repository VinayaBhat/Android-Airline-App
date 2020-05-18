package edu.pdx.cs401j.airlineapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.checkerframework.checker.units.qual.A;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.fakes.RoboMenuItem;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowAlertDialog;
import org.robolectric.shadows.ShadowDialog;
import org.robolectric.shadows.ShadowIntent;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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

    @Test
    public void open_AddNewFlightPage(){
        AddFlight.performClick();
        Intent expectedIntent = new Intent(activity, Main2Activity.class);
        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        assertTrue(actualIntent.filterEquals(expectedIntent));
    }

    @Test
    public void NoInput_Search() {
        SearchFlight.performClick();
        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();
        assertTrue(dialog.isShowing());
        ShadowAlertDialog sAlert = shadowOf(dialog);
        assertThat(sAlert.getMessage().toString(),equalTo("Airline Name not provided"));
    }

    @Test
    public void AirlineName_NotPresent_Search() {
        AirlineName=(EditText) activity.findViewById(R.id.AirlineNameField);
        AirlineName.setText("DummyAirline");
        SearchFlight.performClick();
        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();
        assertTrue(dialog.isShowing());
        ShadowAlertDialog sAlert = shadowOf(dialog);
        assertThat(sAlert.getMessage().toString(),equalTo("Airline not found in App. Please check Airline Name entered"));
    }

    @Test
    public void AirlineNameSrc_DestNotPresent_Search() {
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

    @Test
    public void AirlineNameDest_SrcNotPresent_Search() {
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

    @Test
    public void AirlineName_InvalidSrc_Search() {
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

    @Test
    public void AirlineName_InvalidDest_Search() {
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

    @Test
    public void NoInput_PrettyPrint() {
        prettyPrint.performClick();
        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();
        assertTrue(dialog.isShowing());
        ShadowAlertDialog sAlert = shadowOf(dialog);
        assertThat(sAlert.getMessage().toString(),equalTo("Airline Name not provided"));
    }

    @Test
    public void AirlineName_NotPresent_PrettyPrint() {
        AirlineName=(EditText) activity.findViewById(R.id.AirlineNameField);
        AirlineName.setText("DummyAirline");
        prettyPrint.performClick();
        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();
        assertTrue(dialog.isShowing());
        ShadowAlertDialog sAlert = shadowOf(dialog);
        assertThat(sAlert.getMessage().toString(),equalTo("Airline not found in App. Please check Airline Name entered"));
    }

    @Test
    public void AirlineNameSrc_DestNotPresent_PrettyPrint() {
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

    @Test
    public void AirlineNameDest_SrcNotPresent_PrettyPrint() {
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

    @Test
    public void AirlineName_InvalidSrc_PrettyPrint() {
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

    @Test
    public void AirlineName_InvalidDest_PrettyPrint() {
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