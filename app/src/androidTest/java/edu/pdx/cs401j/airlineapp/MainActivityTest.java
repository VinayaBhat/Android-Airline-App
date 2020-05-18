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

    @Test
    public void AddAirlineButtonClickTest(){
        Instrumentation.ActivityMonitor monitor=getInstrumentation().addMonitor(Main2Activity.class.getName(),null,false);
        assertNotNull(activity.findViewById(R.id.AddNewFlight));
        onView(withId(R.id.AddNewFlight)).perform(click());
        Activity main2activity=getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
        assertNotNull(main2activity);
        main2activity.finish();
    }

    @Test
    public void SearchFlightNull(){
        assertNotNull(activity.findViewById(R.id.SearchFlight));
        onView(withId(R.id.SearchFlight)).perform(click());
        onView(withText("Airline Name not provided")).check(matches(isDisplayed()));
    }

    @Test
    public void SearchFlightSourceInValidDestinationCodeNotGiven(){
        assertNotNull(activity.findViewById(R.id.SearchFlight));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.SourceField)).perform(clearText(),typeText("ABC"));
        onView(withId(R.id.SearchFlight)).perform(click());
        onView(withText("Source Code not valid!")).check(matches(isDisplayed()));
    }

    @Test
    public void SearchFlightSourceGivenDestinationNotGiven(){
        assertNotNull(activity.findViewById(R.id.SearchFlight));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.SourceField)).perform(clearText(),typeText("PDX"));
        onView(withId(R.id.SearchFlight)).perform(click());
        onView(withText("Source Code is given but destination code is not given.")).check(matches(isDisplayed()));
    }

    @Test
    public void SearchFlightSourceNotGivenDestinationGiven(){
        assertNotNull(activity.findViewById(R.id.SearchFlight));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.DestinationField)).perform(clearText(),typeText("JFK"));
        onView(withId(R.id.SearchFlight)).perform(click());
        onView(withText("Source Code is not given but destination code is given.")).check(matches(isDisplayed()));
    }

    @Test
    public void SearchFlightInValidDestinationCodeSourceCodeNotGiven(){
        assertNotNull(activity.findViewById(R.id.SearchFlight));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.DestinationField)).perform(clearText(),typeText("ABC"));
        onView(withId(R.id.SearchFlight)).perform(click());
        onView(withText("Destination Code not valid!")).check(matches(isDisplayed()));
    }

    @Test
    public void SearchFlightSourceCodeInvalid(){
        assertNotNull(activity.findViewById(R.id.SearchFlight));
        Instrumentation.ActivityMonitor monitor=getInstrumentation().addMonitor(AlertDialog.class.getName(),null,false);
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.SourceField)).perform(clearText(),typeText("ABC"));
        onView(withId(R.id.DestinationField)).perform(clearText(),typeText("JFK"));
        onView(withId(R.id.SearchFlight)).perform(click());
        onView(withText("Source Code not valid!")).check(matches(isDisplayed()));
    }

    @Test
    public void SearchFlightDestinationCodeInvalid(){
        assertNotNull(activity.findViewById(R.id.SearchFlight));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.SourceField)).perform(clearText(),typeText("PDX"));
        onView(withId(R.id.DestinationField)).perform(clearText(),typeText("ABC"));
        onView(withId(R.id.SearchFlight)).perform(click());
        onView(withText("Destination Code not valid!")).check(matches(isDisplayed()));
    }

    @Test
    public void PrettyPrintAirlineNameNotGiven(){
        assertNotNull(activity.findViewById(R.id.PP));
        Instrumentation.ActivityMonitor monitor=getInstrumentation().addMonitor(AlertDialog.class.getName(),null,false);
        onView(withId(R.id.PP)).perform(closeSoftKeyboard()).perform(click());
        onView(withText("Airline Name not provided")).check(matches(isDisplayed()));
    }

    @Test
    public void PrettyPrintSourceInValidDestinationCodeNotGiven(){
        assertNotNull(activity.findViewById(R.id.PP));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.SourceField)).perform(clearText(),typeText("ABC"));
        onView(withId(R.id.PP)).perform(closeSoftKeyboard()).perform(click());
        onView(withText("Source Code not valid!")).check(matches(isDisplayed()));
    }

    @Test
    public void PrettyPrintSourceGivenDestinationNotGiven(){
        assertNotNull(activity.findViewById(R.id.PP));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.SourceField)).perform(clearText(),typeText("PDX"));
        onView(withId(R.id.PP)).perform(closeSoftKeyboard()).perform(click());
        onView(withText("Source Code is given but destination code is not given.")).check(matches(isDisplayed()));
    }

    @Test
    public void PrettyPrintSourceNotGivenDestinationGiven(){
        assertNotNull(activity.findViewById(R.id.PP));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.DestinationField)).perform(clearText(),typeText("JFK"));
        onView(withId(R.id.PP)).perform(closeSoftKeyboard()).perform(click());
        onView(withText("Source Code is not given but destination code is given.")).check(matches(isDisplayed()));
    }

    @Test
    public void PrettyPrintInValidDestinationCodeSourceCodeNotGiven(){
        assertNotNull(activity.findViewById(R.id.PP));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.DestinationField)).perform(clearText(),typeText("ABC"));
        onView(withId(R.id.PP)).perform(closeSoftKeyboard()).perform(click());
        onView(withText("Destination Code not valid!")).check(matches(isDisplayed()));
    }

    @Test
    public void PrettyPrintSourceCodeInvalid(){
        assertNotNull(activity.findViewById(R.id.PP));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.SourceField)).perform(clearText(),typeText("ABC"));
        onView(withId(R.id.DestinationField)).perform(clearText(),typeText("JFK"));
        onView(withId(R.id.PP)).perform(closeSoftKeyboard()).perform(click());
        onView(withText("Source Code not valid!")).check(matches(isDisplayed()));
    }

    @Test
    public void PrettyPrintDestinationCodeInvalid(){
        assertNotNull(activity.findViewById(R.id.PP));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.SourceField)).perform(clearText(),typeText("PDX"));
        onView(withId(R.id.DestinationField)).perform(clearText(),typeText("ABC"));
        onView(withId(R.id.PP)).perform(closeSoftKeyboard()).perform(click());
        onView(withText("Destination Code not valid!")).check(matches(isDisplayed()));
    }
    @Test
    public void PPSourceGivenDestinationNotGiven(){
        assertNotNull(activity.findViewById(R.id.PP));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.SourceField)).perform(clearText(),typeText("PDX"));
        onView(withId(R.id.PP)).perform(closeSoftKeyboard()).perform(click());        onView(withText("Source Code is given but destination code is not given.")).check(matches(isDisplayed()));
    }

    @Test
    public void PPSourceNotGivenDestinationGiven(){
        assertNotNull(activity.findViewById(R.id.PP));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.DestinationField)).perform(clearText(),typeText("JFK"));
        onView(withId(R.id.PP)).perform(closeSoftKeyboard()).perform(click());        onView(withText("Source Code is not given but destination code is given.")).check(matches(isDisplayed()));
    }

    @Test
    public void PPInValidDestinationCodeSourceCodeNotGiven(){
        assertNotNull(activity.findViewById(R.id.PP));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.DestinationField)).perform(clearText(),typeText("ABC"));
        onView(withId(R.id.PP)).perform(closeSoftKeyboard()).perform(click());        onView(withText("Destination Code not valid!")).check(matches(isDisplayed()));
    }

    @Test
    public void PPSourceCodeInvalid(){
        assertNotNull(activity.findViewById(R.id.PP));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.SourceField)).perform(clearText(),typeText("ABC"));
        onView(withId(R.id.DestinationField)).perform(clearText(),typeText("JFK"));
        onView(withId(R.id.PP)).perform(closeSoftKeyboard()).perform(click());        onView(withText("Source Code not valid!")).check(matches(isDisplayed()));
    }

    @Test
    public void PPDestinationCodeInvalid(){
        assertNotNull(activity.findViewById(R.id.PP));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.SourceField)).perform(clearText(),typeText("PDX"));
        onView(withId(R.id.DestinationField)).perform(clearText(),typeText("ABC"));
        onView(withId(R.id.PP)).perform(closeSoftKeyboard()).perform(click());        onView(withText("Destination Code not valid!")).check(matches(isDisplayed()));
    }

    @Test
    public void helpTest(){
        Instrumentation.ActivityMonitor monitor=getInstrumentation().addMonitor(README.class.getName(),null,false);
        assertNotNull(activity.findViewById(R.id.help));
        onView(withId(R.id.help)).perform(click());
        onView(withText("README")).perform(click());
        Activity main2activity=getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
        assertNotNull(main2activity);
        main2activity.finish();
    }

    @Test
    public void DummyAirline(){
        assertNotNull(activity.findViewById(R.id.SearchFlight));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Dummy"));
        onView(withId(R.id.SearchFlight)).perform(click());
        onView(withText("Airline not found in App. Please check Airline Name entered")).check(matches(isDisplayed()));
    }

    @Test
    public void TestAirline(){
        Instrumentation.ActivityMonitor monitor2=getInstrumentation().addMonitor(Main3Activity.class.getName(),null,false);
        Instrumentation.ActivityMonitor monitor=getInstrumentation().addMonitor(Main2Activity.class.getName(),null,false);
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

    @Test
    public void TestAirlineWithSrcandDest(){
        Instrumentation.ActivityMonitor monitor2=getInstrumentation().addMonitor(Main3Activity.class.getName(),null,false);
        Instrumentation.ActivityMonitor monitor=getInstrumentation().addMonitor(Main2Activity.class.getName(),null,false);
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

    @Test
    public void PPTestAirline(){
        Instrumentation.ActivityMonitor monitor2=getInstrumentation().addMonitor(SearchResult.class.getName(),null,false);
        Instrumentation.ActivityMonitor monitor=getInstrumentation().addMonitor(Main2Activity.class.getName(),null,false);
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

    @Test
    public void PPTestAirlineWithSrcandDest(){
        Instrumentation.ActivityMonitor monitor2=getInstrumentation().addMonitor(SearchResult.class.getName(),null,false);
        Instrumentation.ActivityMonitor monitor=getInstrumentation().addMonitor(Main2Activity.class.getName(),null,false);
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

    @Test
    public void InvalidSrcAndDest(){
        Instrumentation.ActivityMonitor monitor2=getInstrumentation().addMonitor(Main3Activity.class.getName(),null,false);
        Instrumentation.ActivityMonitor monitor=getInstrumentation().addMonitor(Main2Activity.class.getName(),null,false);
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