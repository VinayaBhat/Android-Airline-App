package edu.pdx.cs401j.airlineapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import org.junit.Assert;
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
import org.robolectric.shadows.ShadowToast;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {Build.VERSION_CODES.O_MR1})
public class AddNewFlightTest {

    AddFlightPage activity;
    EditText AirlineNameField;
    EditText FlightNumber;
    EditText Source;
    EditText Destination;
    EditText DepDate;
    EditText DepTime;
    EditText ArrivalDate;
    EditText ArrivalTime;
    Button AddFlight;

    @Before
    public void SetUp(){
        activity = Robolectric.buildActivity(AddFlightPage.class).create()
                .start()
                .resume()
                .visible()
                .get();
        AirlineNameField=(EditText) activity.findViewById(R.id.AirlineNameField);
        FlightNumber=(EditText) activity.findViewById(R.id.FlightNumber);
        Source=(EditText) activity.findViewById(R.id.Source);
        Destination=(EditText) activity.findViewById(R.id.Destination);
        DepDate=(EditText) activity.findViewById(R.id.DepDate);
        DepTime=(EditText) activity.findViewById(R.id.DepTime);
        ArrivalDate=(EditText) activity.findViewById(R.id.ArrivalDate);
        ArrivalTime=(EditText) activity.findViewById(R.id.ArrivalTime);
        AddFlight=(Button)activity.findViewById(R.id.AddFlight);
    }

    /**
     * Add Flight with no Airline name
     */
    @Test
    public void AddFlight_AirlineNameNotSet_Error(){
        AddFlight.performClick();
        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();
        assertTrue(dialog.isShowing());
        ShadowAlertDialog sAlert = shadowOf(dialog);
        assertThat(sAlert.getMessage().toString(),equalTo("Airline name is not set"));
    }
    /**
     * Add Flight with no Flight Number
     */
    @Test
    public void AddFlight_FlightNumberNotSet_Error(){
        AirlineNameField.setText("Alaska");
        AddFlight.performClick();
        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();
        assertTrue(dialog.isShowing());
        ShadowAlertDialog sAlert = shadowOf(dialog);
        assertThat(sAlert.getMessage().toString(),equalTo("Flight number is not set properly"));
    }
    /**
     * Add Flight with no source
     */
    @Test
    public void AddFlight_SourceNotSet_Error(){
        AirlineNameField.setText("Alaska");
        FlightNumber.setText("123");
        AddFlight.performClick();
        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();
        assertTrue(dialog.isShowing());
        ShadowAlertDialog sAlert = shadowOf(dialog);
        assertThat(sAlert.getMessage().toString(),equalTo("Source airport code is invalid"));
    }

    @Test
    public void AddFlight_InvalidSource_Error(){
        AirlineNameField.setText("Alaska");
        FlightNumber.setText("123");
        Source.setText("AAA");
        AddFlight.performClick();
        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();
        assertTrue(dialog.isShowing());
        ShadowAlertDialog sAlert = shadowOf(dialog);
        assertThat(sAlert.getMessage().toString(),equalTo("Source airport code is invalid"));
    }
    /**
     * Add Flight with invalid source
     */
    @Test
    public void AddFlight_DepDateNotSet_Error(){
        AirlineNameField.setText("Alaska");
        FlightNumber.setText("123");
        Source.setText("PDX");
        AddFlight.performClick();
        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();
        assertTrue(dialog.isShowing());
        ShadowAlertDialog sAlert = shadowOf(dialog);
        assertThat(sAlert.getMessage().toString(),equalTo("Departure Date not set properly"));
    }
    /**
     * Add Flight with no dept date
     */
    @Test
    public void AddFlight_DepDateInvalid_Error(){
        AirlineNameField.setText("Alaska");
        FlightNumber.setText("123");
        Source.setText("PDX");
        DepDate.setText("1/x/1111");
        AddFlight.performClick();
        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();
        assertTrue(dialog.isShowing());
        ShadowAlertDialog sAlert = shadowOf(dialog);
        assertThat(sAlert.getMessage().toString(),equalTo("Departure Date not set properly"));
    }
    /**
     * Add Flight with no dept time
     */
    @Test
    public void AddFlight_DepTimeNotSet_Error(){
        AirlineNameField.setText("Alaska");
        FlightNumber.setText("123");
        Source.setText("PDX");
        DepDate.setText("1/2/2000");
        AddFlight.performClick();
        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();
        assertTrue(dialog.isShowing());
        ShadowAlertDialog sAlert = shadowOf(dialog);
        assertThat(sAlert.getMessage().toString(),equalTo("Departure Time not set properly"));
    }
    /**
     * Add Flight with invalid dept time
     */
    @Test
    public void AddFlight_DepTimeInvalid_Error(){
        AirlineNameField.setText("Alaska");
        FlightNumber.setText("123");
        Source.setText("PDX");
        DepDate.setText("1/2/2000");
        DepTime.setText("10:XX am");
        AddFlight.performClick();
        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();
        assertTrue(dialog.isShowing());
        ShadowAlertDialog sAlert = shadowOf(dialog);
        assertThat(sAlert.getMessage().toString(),equalTo("Departure Time not set properly"));
    }
    /**
     * Add Flight with no destination
     */
    @Test
    public void AddFlight_DestinationNotSet_Error(){
        AirlineNameField.setText("Alaska");
        FlightNumber.setText("123");
        Source.setText("PDX");
        DepDate.setText("1/2/2000");
        DepTime.setText("10:30 am");
        AddFlight.performClick();
        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();
        assertTrue(dialog.isShowing());
        ShadowAlertDialog sAlert = shadowOf(dialog);
        assertThat(sAlert.getMessage().toString(),equalTo("Destination airport code not valid"));
    }
    /**
     * Add Flight with invalid destiantion
     */
    @Test
    public void AddFlight_Destinationinvalid_Error(){
        AirlineNameField.setText("Alaska");
        FlightNumber.setText("123");
        Source.setText("PDX");
        DepDate.setText("1/2/2000");
        DepTime.setText("10:30 am");
        Destination.setText("AAA");
        AddFlight.performClick();
        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();
        assertTrue(dialog.isShowing());
        ShadowAlertDialog sAlert = shadowOf(dialog);
        assertThat(sAlert.getMessage().toString(),equalTo("Destination airport code not valid"));
    }
    /**
     * Add Flight with no arrival date
     */
    @Test
    public void AddFlight_ArrivalDateNotSet_Error(){
        AirlineNameField.setText("Alaska");
        FlightNumber.setText("123");
        Source.setText("PDX");
        DepDate.setText("1/2/2000");
        DepTime.setText("10:30 am");
        Destination.setText("JFK");
        AddFlight.performClick();
        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();
        assertTrue(dialog.isShowing());
        ShadowAlertDialog sAlert = shadowOf(dialog);
        assertThat(sAlert.getMessage().toString(),equalTo("Arrival Date not set properly"));
    }
    /**
     * Add Flight with invalid arrival date
     */
    @Test
    public void AddFlight_ArrivalDateInvalid_Error(){
        AirlineNameField.setText("Alaska");
        FlightNumber.setText("123");
        Source.setText("PDX");
        DepDate.setText("1/2/2000");
        DepTime.setText("10:30 am");
        Destination.setText("JFK");
        ArrivalDate.setText("1/2/XXXX");
        AddFlight.performClick();
        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();
        assertTrue(dialog.isShowing());
        ShadowAlertDialog sAlert = shadowOf(dialog);
        assertThat(sAlert.getMessage().toString(),equalTo("Arrival Date not set properly"));
    }
    /**
     * Add Flight with arrival time not set
     */
    @Test
    public void AddFlight_ArrivalTimeNotSet_Error(){
        AirlineNameField.setText("Alaska");
        FlightNumber.setText("123");
        Source.setText("PDX");
        DepDate.setText("1/2/2000");
        DepTime.setText("10:30 am");
        Destination.setText("JFK");
        ArrivalDate.setText("1/2/2000");
        AddFlight.performClick();
        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();
        assertTrue(dialog.isShowing());
        ShadowAlertDialog sAlert = shadowOf(dialog);
        assertThat(sAlert.getMessage().toString(),equalTo("Arrival Time not set properly"));
    }
    /**
     * Add Flight with invalid arrival time
     */
    @Test
    public void AddFlight_ArrivalTimeInvalid_Error(){
        AirlineNameField.setText("Alaska");
        FlightNumber.setText("123");
        Source.setText("PDX");
        DepDate.setText("1/2/2000");
        DepTime.setText("10:30 am");
        Destination.setText("JFK");
        ArrivalDate.setText("1/2/2000");
        ArrivalTime.setText("10:XX pm");
        AddFlight.performClick();
        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();
        assertTrue(dialog.isShowing());
        ShadowAlertDialog sAlert = shadowOf(dialog);
        assertThat(sAlert.getMessage().toString(),equalTo("Arrival Time not set properly"));
    }
    /**
     * Add Flight with arrival date before departure date
     */
    @Test
    public void AddFlight_ArrivalBeforeDepartureError_Error(){
        AirlineNameField.setText("Alaska");
        FlightNumber.setText("123");
        Source.setText("PDX");
        DepDate.setText("1/2/2000");
        DepTime.setText("10:30 am");
        Destination.setText("JFK");
        ArrivalDate.setText("1/1/2000");
        ArrivalTime.setText("10:20 am");
        AddFlight.performClick();
        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();
        assertTrue(dialog.isShowing());
        ShadowAlertDialog sAlert = shadowOf(dialog);
        assertThat(sAlert.getMessage().toString(),equalTo("Arrival date or time is not before departure date or time"));
    }
    /**
     * Add Flight success
     */
    @Test
    public void Success_AddFlight(){
        AirlineNameField.setText("Alaska");
        FlightNumber.setText("123");
        Source.setText("PDX");
        DepDate.setText("1/2/2000");
        DepTime.setText("10:30 am");
        Destination.setText("JFK");
        ArrivalDate.setText("1/2/2000");
        ArrivalTime.setText("10:30 pm");
        AddFlight.performClick();
        Assert.assertEquals("Flight has been added..", ShadowToast.getTextOfLatestToast().toString());

    }
    /**
     * Add Flight already present airline success
     */
    @Test
    public void FlightAlreadyPresent_AddFlight(){
        AirlineNameField.setText("Alaska");
        FlightNumber.setText("123");
        Source.setText("PDX");
        DepDate.setText("1/2/2000");
        DepTime.setText("10:30 am");
        Destination.setText("LAX");
        ArrivalDate.setText("1/2/2000");
        ArrivalTime.setText("10:30 pm");
        AddFlight.performClick();
        Assert.assertEquals("Flight has been added..", ShadowToast.getTextOfLatestToast().toString());
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
