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

    Main2Activity activity;
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
        activity = Robolectric.buildActivity(Main2Activity.class).create()
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

    @Test
    public void Empty_AddFlight(){
        AddFlight.performClick();
        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();
        assertTrue(dialog.isShowing());
        ShadowAlertDialog sAlert = shadowOf(dialog);
        assertThat(sAlert.getMessage().toString(),equalTo("Airline name is not set"));
    }

    @Test
    public void NoFlightNumber_AddFlight(){
        AirlineNameField.setText("Alaska");
        AddFlight.performClick();
        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();
        assertTrue(dialog.isShowing());
        ShadowAlertDialog sAlert = shadowOf(dialog);
        assertThat(sAlert.getMessage().toString(),equalTo("Flight number is not set properly"));
    }

    @Test
    public void SourceNotSet_AddFlight(){
        AirlineNameField.setText("Alaska");
        FlightNumber.setText("123");
        AddFlight.performClick();
        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();
        assertTrue(dialog.isShowing());
        ShadowAlertDialog sAlert = shadowOf(dialog);
        assertThat(sAlert.getMessage().toString(),equalTo("Source airport code is invalid"));
    }

    @Test
    public void SourceInvalid_AddFlight(){
        AirlineNameField.setText("Alaska");
        FlightNumber.setText("123");
        Source.setText("AAA");
        AddFlight.performClick();
        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();
        assertTrue(dialog.isShowing());
        ShadowAlertDialog sAlert = shadowOf(dialog);
        assertThat(sAlert.getMessage().toString(),equalTo("Source airport code is invalid"));
    }

    @Test
    public void DepDateNotSet_AddFlight(){
        AirlineNameField.setText("Alaska");
        FlightNumber.setText("123");
        Source.setText("PDX");
        AddFlight.performClick();
        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();
        assertTrue(dialog.isShowing());
        ShadowAlertDialog sAlert = shadowOf(dialog);
        assertThat(sAlert.getMessage().toString(),equalTo("Departure Date not set properly"));
    }
    @Test
    public void DepDateInvalid_AddFlight(){
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

    @Test
    public void DepTimeNotSet_AddFlight(){
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
    @Test
    public void DepTimeInvalid_AddFlight(){
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

    @Test
    public void DestinationNotSet_AddFlight(){
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

    @Test
    public void DestinationInvalid_AddFlight(){
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
    @Test
    public void ArrivalDateNotSet_AddFlight(){
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
    @Test
    public void ArrivalDateInvalid_AddFlight(){
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

    @Test
    public void ArrivalTimeNotSet_AddFlight(){
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

    @Test
    public void ArrivalInvalid_AddFlight(){
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

    @Test
    public void ArrivalBeforeDepartureError_AddFlight(){
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
