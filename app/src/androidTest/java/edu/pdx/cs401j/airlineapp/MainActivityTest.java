package edu.pdx.cs401j.airlineapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Instrumentation;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static junit.framework.TestCase.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class  MainActivityTest{

    @Rule
    public ActivityTestRule<MainActivity> mainactivity=new ActivityTestRule<>(MainActivity.class);
    private MainActivity activity=null;

    @Before
    public void setUp(){
        activity=mainactivity.getActivity();
    }

    /**
     * Checking if Add Flight button takes to next page
     */
    @Test
    public void AddFlight_Success_NextPage(){
        Instrumentation.ActivityMonitor monitor=getInstrumentation().addMonitor(AddFlightPage.class.getName(),null,false);
        assertNotNull(activity.findViewById(R.id.AddNewFlight));
        onView(withId(R.id.AddNewFlight)).perform(click());
        Activity main2activity=getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
        assertNotNull(main2activity);
        main2activity.finish();
    }

    /**
     * Search Flight with no Airline name
     */
    @Test
    public void SearchFlight_NoAirline_Error(){
        assertNotNull(activity.findViewById(R.id.SearchFlight));
        onView(withId(R.id.SearchFlight)).perform(click());
        onView(withText("Airline Name not provided")).check(matches(isDisplayed()));
    }
    /**
     * Search Flight with invalid source
     */
    @Test
    public void SearchFlight_InvalidSrc_Error(){
        assertNotNull(activity.findViewById(R.id.SearchFlight));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.SourceField)).perform(clearText(),typeText("ABC"));
        onView(withId(R.id.SearchFlight)).perform(click());
        onView(withText("Source Code not valid!")).check(matches(isDisplayed()));
    }
    /**
     * Search Flight with no destination code
     */
    @Test
    public void SearchFlight_NoDestinationCode_Error(){
        assertNotNull(activity.findViewById(R.id.SearchFlight));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.SourceField)).perform(clearText(),typeText("PDX"));
        onView(withId(R.id.SearchFlight)).perform(click());
        onView(withText("Source Code is given but destination code is not given.")).check(matches(isDisplayed()));
    }
    /**
     * Search Flight with no source code
     */
    @Test
    public void SearchFlight_NoSourceCode_Error(){
        assertNotNull(activity.findViewById(R.id.SearchFlight));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.DestinationField)).perform(clearText(),typeText("JFK"));
        onView(withId(R.id.SearchFlight)).perform(click());
        onView(withText("Source Code is not given but destination code is given.")).check(matches(isDisplayed()));
    }
    /**
     * Search Flight with invalid destination code
     */
    @Test
    public void SearchFlight_InvalidDestinationCode_Error(){
        assertNotNull(activity.findViewById(R.id.SearchFlight));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.DestinationField)).perform(clearText(),typeText("ABC"));
        onView(withId(R.id.SearchFlight)).perform(click());
        onView(withText("Destination Code not valid!")).check(matches(isDisplayed()));
    }
    /**
     * Search Flight with invalid source code
     */
    @Test
    public void SearchFlight_InvalidSourceCode_Error(){
        assertNotNull(activity.findViewById(R.id.SearchFlight));
        Instrumentation.ActivityMonitor monitor=getInstrumentation().addMonitor(AlertDialog.class.getName(),null,false);
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.SourceField)).perform(clearText(),typeText("ABC"));
        onView(withId(R.id.DestinationField)).perform(clearText(),typeText("JFK"));
        onView(withId(R.id.SearchFlight)).perform(click());
        onView(withText("Source Code not valid!")).check(matches(isDisplayed()));
    }
    /**
     * Search Flight with invalid destination code
     */
    @Test
    public void SearchFlight_InvalidDestCode_Error(){
        assertNotNull(activity.findViewById(R.id.SearchFlight));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.SourceField)).perform(clearText(),typeText("PDX"));
        onView(withId(R.id.DestinationField)).perform(clearText(),typeText("ABC"));
        onView(withId(R.id.SearchFlight)).perform(click());
        onView(withText("Destination Code not valid!")).check(matches(isDisplayed()));
    }
    /**
     * Pretty print Search flight with no airline name
     */
    @Test
    public void PrettyPrint_AirlineNameNotGiven_Error(){
        assertNotNull(activity.findViewById(R.id.PP));
        Instrumentation.ActivityMonitor monitor=getInstrumentation().addMonitor(AlertDialog.class.getName(),null,false);
        onView(withId(R.id.PP)).perform(closeSoftKeyboard()).perform(click());
        onView(withText("Airline Name not provided")).check(matches(isDisplayed()));
    }
    /**
     * Pretty print Search flight with invalid source
     */
    @Test
    public void PrettyPrint_SourceInValidDestinationCodeNotGiven_Error(){
        assertNotNull(activity.findViewById(R.id.PP));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.SourceField)).perform(clearText(),typeText("ABC"));
        onView(withId(R.id.PP)).perform(closeSoftKeyboard()).perform(click());
        onView(withText("Source Code not valid!")).check(matches(isDisplayed()));
    }
    /**
     * Pretty print Search flight with no destination code
     */
    @Test
    public void PrettyPrint_SourceGivenDestinationNotGiven_Error(){
        assertNotNull(activity.findViewById(R.id.PP));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.SourceField)).perform(clearText(),typeText("PDX"));
        onView(withId(R.id.PP)).perform(closeSoftKeyboard()).perform(click());
        onView(withText("Source Code is given but destination code is not given.")).check(matches(isDisplayed()));
    }
    /**
     * Pretty print Search flight with no airline name
     */
    @Test
    public void PrettyPrint_SourceNotGivenDestinationGiven_Error(){
        assertNotNull(activity.findViewById(R.id.PP));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.DestinationField)).perform(clearText(),typeText("JFK"));
        onView(withId(R.id.PP)).perform(closeSoftKeyboard()).perform(click());
        onView(withText("Source Code is not given but destination code is given.")).check(matches(isDisplayed()));
    }
    /**
     * Pretty print Search flight with invalid destination code
     */
    @Test
    public void PrettyPrint_InValidDestinationCodeSourceCodeNotGiven_Error(){
        assertNotNull(activity.findViewById(R.id.PP));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.DestinationField)).perform(clearText(),typeText("ABC"));
        onView(withId(R.id.PP)).perform(closeSoftKeyboard()).perform(click());
        onView(withText("Destination Code not valid!")).check(matches(isDisplayed()));
    }
    /**
     * Pretty print Search flight with invalid source
     */
    @Test
    public void PrettyPrint_SourceCodeInvalid_Error(){
        assertNotNull(activity.findViewById(R.id.PP));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.SourceField)).perform(clearText(),typeText("ABC"));
        onView(withId(R.id.DestinationField)).perform(clearText(),typeText("JFK"));
        onView(withId(R.id.PP)).perform(closeSoftKeyboard()).perform(click());
        onView(withText("Source Code not valid!")).check(matches(isDisplayed()));
    }
    /**
     * Pretty print Search flight with invalid destination code
     */
    @Test
    public void PrettyPrint_DestinationCodeInvalid_Error(){
        assertNotNull(activity.findViewById(R.id.PP));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.SourceField)).perform(clearText(),typeText("PDX"));
        onView(withId(R.id.DestinationField)).perform(clearText(),typeText("ABC"));
        onView(withId(R.id.PP)).perform(closeSoftKeyboard()).perform(click());
        onView(withText("Destination Code not valid!")).check(matches(isDisplayed()));
    }
    /**
     * Pretty print Search flight with no destination code
     */
    @Test
    public void PrettyPrint_SourceCodeGivenDestinationNotGiven_Error(){
        assertNotNull(activity.findViewById(R.id.PP));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.SourceField)).perform(clearText(),typeText("PDX"));
        onView(withId(R.id.PP)).perform(closeSoftKeyboard()).perform(click());
        onView(withText("Source Code is given but destination code is not given.")).check(matches(isDisplayed()));
    }
    /**
     * Pretty print Search flight with no source code
     */
    @Test
    public void PrettyPrint_SourceCodeNotGivenDestinationGiven_Error(){
        assertNotNull(activity.findViewById(R.id.PP));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.DestinationField)).perform(clearText(),typeText("JFK"));
        onView(withId(R.id.PP)).perform(closeSoftKeyboard()).perform(click());
        onView(withText("Source Code is not given but destination code is given.")).check(matches(isDisplayed()));
    }
    /**
     * Pretty print Search flight with invalid destination code
     */
    @Test
    public void PrettyPrint_InValidDestinationCodeSourceCodeNotGiven_Given(){
        assertNotNull(activity.findViewById(R.id.PP));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.DestinationField)).perform(clearText(),typeText("ABC"));
        onView(withId(R.id.PP)).perform(closeSoftKeyboard()).perform(click());        onView(withText("Destination Code not valid!")).check(matches(isDisplayed()));
    }
    /**
     * Help Menu Test
     */
    @Test
    public void HelpMenu(){
        Instrumentation.ActivityMonitor monitor=getInstrumentation().addMonitor(README.class.getName(),null,false);
        assertNotNull(activity.findViewById(R.id.help));
        onView(withId(R.id.help)).perform(click());
        onView(withText("README")).perform(click());
        Activity main2activity=getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
        assertNotNull(main2activity);
        main2activity.finish();
    }
    /**
     *  Search flight with dummy airline name
     */
    @Test
    public void SearchFlight_DummyAirline(){
        assertNotNull(activity.findViewById(R.id.SearchFlight));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Dummy"));
        onView(withId(R.id.SearchFlight)).perform(click());
        onView(withText("Airline not found in App. Please check Airline Name entered")).check(matches(isDisplayed()));
    }
    /**
     *  Search flight with valid airline name
     */
    @Test
    public void SearchFlight_ValidAirline(){
        Instrumentation.ActivityMonitor monitor2=getInstrumentation().addMonitor(SearchFlight.class.getName(),null,false);
        Instrumentation.ActivityMonitor monitor=getInstrumentation().addMonitor(AddFlightPage.class.getName(),null,false);
        assertNotNull(activity.findViewById(R.id.AddNewFlight));
        onView(withId(R.id.AddNewFlight)).perform(click());
        Activity main2activity=getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
        assertNotNull(main2activity);
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.FlightNumber)).perform(clearText(),typeText("123"));
        onView(withId(R.id.Source)).perform(clearText(),typeText("PDX"));
        onView(withId(R.id.DepDate)).perform(clearText(),typeText("1/1/2000"));
        onView(withId(R.id.DepTime)).perform(clearText(),typeText("1:30 AM"));
        onView(withId(R.id.Destination)).perform(closeSoftKeyboard()).perform(clearText(),typeText("LAX"));
        onView(withId(R.id.ArrivalDate)).perform(closeSoftKeyboard()).perform(clearText(),typeText("1/1/2000"));
        onView(withId(R.id.ArrivalTime)).perform(closeSoftKeyboard()).perform(clearText(),typeText("2:30 AM"));
        onView(withId(R.id.AddFlight)).perform(closeSoftKeyboard()).perform(click());
        main2activity.finish();
        assertNotNull(activity.findViewById(R.id.SearchFlight));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.SearchFlight)).perform(click());
        Activity searchresult=getInstrumentation().waitForMonitorWithTimeout(monitor2,5000);
        assertNotNull(searchresult);
        searchresult.finish();
    }
    /**
     *  Search flight with  airline name,src and destination
     */
    @Test
    public void AddFlight_AirlineWithSrcandDest_Success(){
        Instrumentation.ActivityMonitor monitor2=getInstrumentation().addMonitor(SearchFlight.class.getName(),null,false);
        Instrumentation.ActivityMonitor monitor=getInstrumentation().addMonitor(AddFlightPage.class.getName(),null,false);
        assertNotNull(activity.findViewById(R.id.AddNewFlight));
        onView(withId(R.id.AddNewFlight)).perform(click());
        Activity main2activity=getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
        assertNotNull(main2activity);
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.FlightNumber)).perform(clearText(),typeText("123"));
        onView(withId(R.id.Source)).perform(clearText(),typeText("PDX"));
        onView(withId(R.id.DepDate)).perform(clearText(),typeText("1/1/2000"));
        onView(withId(R.id.DepTime)).perform(clearText(),typeText("1:30 AM"));
        onView(withId(R.id.Destination)).perform(closeSoftKeyboard()).perform(clearText(),typeText("LAX"));
        onView(withId(R.id.ArrivalDate)).perform(closeSoftKeyboard()).perform(clearText(),typeText("1/1/2000"));
        onView(withId(R.id.ArrivalTime)).perform(closeSoftKeyboard()).perform(clearText(),typeText("2:30 AM"));
        onView(withId(R.id.AddFlight)).perform(closeSoftKeyboard()).perform(click());
        main2activity.finish();
        assertNotNull(activity.findViewById(R.id.SearchFlight));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.SourceField)).perform(clearText(),typeText("PDX"));
        onView(withId(R.id.DestinationField)).perform(clearText(),typeText("LAX"));
        onView(withId(R.id.SearchFlight)).perform(click());
        Activity searchresult=getInstrumentation().waitForMonitorWithTimeout(monitor2,5000);
        assertNotNull(searchresult);
        searchresult.finish();
    }
    /**
     *  Pretty Print Search flight with airline name
     */
    @Test
    public void PrettyPrint_Airline_Success(){
        Instrumentation.ActivityMonitor monitor2=getInstrumentation().addMonitor(PrettyPrintSearchFlight.class.getName(),null,false);
        Instrumentation.ActivityMonitor monitor=getInstrumentation().addMonitor(AddFlightPage.class.getName(),null,false);
        assertNotNull(activity.findViewById(R.id.AddNewFlight));
        onView(withId(R.id.AddNewFlight)).perform(click());
        Activity main2activity=getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
        assertNotNull(main2activity);
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.FlightNumber)).perform(clearText(),typeText("123"));
        onView(withId(R.id.Source)).perform(clearText(),typeText("PDX"));
        onView(withId(R.id.DepDate)).perform(clearText(),typeText("1/1/2000"));
        onView(withId(R.id.DepTime)).perform(clearText(),typeText("1:30 AM"));
        onView(withId(R.id.Destination)).perform(closeSoftKeyboard()).perform(clearText(),typeText("LAX"));
        onView(withId(R.id.ArrivalDate)).perform(closeSoftKeyboard()).perform(clearText(),typeText("1/1/2000"));
        onView(withId(R.id.ArrivalTime)).perform(closeSoftKeyboard()).perform(clearText(),typeText("2:30 AM"));
        onView(withId(R.id.AddFlight)).perform(closeSoftKeyboard()).perform(click());
        main2activity.finish();
        assertNotNull(activity.findViewById(R.id.PP));
        onView(withId(R.id.AirlineNameField)).perform(closeSoftKeyboard()).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.PP)).perform(closeSoftKeyboard()).perform(click());
        Activity searchresult=getInstrumentation().waitForMonitorWithTimeout(monitor2,5000);
        assertNotNull(searchresult);
        searchresult.finish();
    }
    /**
     * Pretty Print Search flight with  airline name, src and destination
     */
    @Test
    public void PrettyPrint_AirlineWithSrcandDest_Success(){
        Instrumentation.ActivityMonitor monitor2=getInstrumentation().addMonitor(PrettyPrintSearchFlight.class.getName(),null,false);
        Instrumentation.ActivityMonitor monitor=getInstrumentation().addMonitor(AddFlightPage.class.getName(),null,false);
        assertNotNull(activity.findViewById(R.id.AddNewFlight));
        onView(withId(R.id.AddNewFlight)).perform(click());
        Activity main2activity=getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
        assertNotNull(main2activity);
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.FlightNumber)).perform(clearText(),typeText("123"));
        onView(withId(R.id.Source)).perform(clearText(),typeText("PDX"));
        onView(withId(R.id.DepDate)).perform(clearText(),typeText("1/1/2000"));
        onView(withId(R.id.DepTime)).perform(clearText(),typeText("1:30 AM"));
        onView(withId(R.id.Destination)).perform(closeSoftKeyboard()).perform(clearText(),typeText("LAX"));
        onView(withId(R.id.ArrivalDate)).perform(closeSoftKeyboard()).perform(clearText(),typeText("1/1/2000"));
        onView(withId(R.id.ArrivalTime)).perform(closeSoftKeyboard()).perform(clearText(),typeText("2:30 AM"));
        onView(withId(R.id.AddFlight)).perform(closeSoftKeyboard()).perform(click());
        main2activity.finish();
        assertNotNull(activity.findViewById(R.id.PP));
        onView(withId(R.id.AirlineNameField)).perform(closeSoftKeyboard()).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.SourceField)).perform(closeSoftKeyboard()).perform(clearText(),typeText("PDX"));
        onView(withId(R.id.DestinationField)).perform(closeSoftKeyboard()).perform(clearText(),typeText("LAX"));
        onView(withId(R.id.PP)).perform(closeSoftKeyboard()).perform(click());
        Activity searchresult=getInstrumentation().waitForMonitorWithTimeout(monitor2,5000);
        assertNotNull(searchresult);
        searchresult.finish();
    }
    /**
     *  Search flight with  airline name and next page
     */
    @Test
    public void SearchFlight_SrcDestAirlineNotPresent_Success(){
        Instrumentation.ActivityMonitor monitor2=getInstrumentation().addMonitor(SearchFlight.class.getName(),null,false);
        Instrumentation.ActivityMonitor monitor=getInstrumentation().addMonitor(AddFlightPage.class.getName(),null,false);
        assertNotNull(activity.findViewById(R.id.AddNewFlight));
        onView(withId(R.id.AddNewFlight)).perform(click());
        Activity main2activity=getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
        assertNotNull(main2activity);
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.FlightNumber)).perform(clearText(),typeText("123"));
        onView(withId(R.id.Source)).perform(clearText(),typeText("PDX"));
        onView(withId(R.id.DepDate)).perform(clearText(),typeText("1/1/2000"));
        onView(withId(R.id.DepTime)).perform(clearText(),typeText("1:30 AM"));
        onView(withId(R.id.Destination)).perform(closeSoftKeyboard()).perform(clearText(),typeText("LAX"));
        onView(withId(R.id.ArrivalDate)).perform(closeSoftKeyboard()).perform(clearText(),typeText("1/1/2000"));
        onView(withId(R.id.ArrivalTime)).perform(closeSoftKeyboard()).perform(clearText(),typeText("2:30 AM"));
        onView(withId(R.id.AddFlight)).perform(closeSoftKeyboard()).perform(click());
        main2activity.finish();
        assertNotNull(activity.findViewById(R.id.SearchFlight));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.SourceField)).perform(clearText(),typeText("JFK"));
        onView(withId(R.id.DestinationField)).perform(clearText(),typeText("SEA"));
        onView(withId(R.id.SearchFlight)).perform(click());
        Activity searchresult=getInstrumentation().waitForMonitorWithTimeout(monitor2,5000);
        assertNotNull(searchresult);
        searchresult.finish();
    }



    @After
    public void TearDown(){
        activity=null;
    }
}