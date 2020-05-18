package edu.pdx.cs401j.airlineapp;

import android.app.Activity;
import android.app.Instrumentation;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)

public class Main2ActivityTest {
    @Rule
    public ActivityTestRule<Main2Activity> main2activity=new ActivityTestRule<>(Main2Activity.class);
    private Main2Activity activity=null;

    @Before
    public void setUp(){
        activity=main2activity.getActivity();
    }

    @Test
    public void NoInput(){
        assertNotNull(activity.findViewById(R.id.AddFlight));
        onView(withId(R.id.AddFlight)).perform(click());
        onView(withText("Airline name is not set")).check(matches(isDisplayed()));
    }
    @Test
    public void NoFlightNumber(){
        assertNotNull(activity.findViewById(R.id.AddFlight));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.AddFlight)).perform(closeSoftKeyboard()).perform(click());
        onView(withText("Flight number is not set properly")).check(matches(isDisplayed()));
    }
    @Test
    public void NoSourceCode(){
        assertNotNull(activity.findViewById(R.id.AddFlight));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.FlightNumber)).perform(clearText(),typeText("123"));
        onView(withId(R.id.AddFlight)).perform(closeSoftKeyboard()).perform(click());
        onView(withText("Source airport code is invalid")).check(matches(isDisplayed()));
    }
    @Test
    public void InValidSourceCode(){
        assertNotNull(activity.findViewById(R.id.AddFlight));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.FlightNumber)).perform(clearText(),typeText("123"));
        onView(withId(R.id.Source)).perform(clearText(),typeText("ABC"));
        onView(withId(R.id.AddFlight)).perform(closeSoftKeyboard()).perform(click());
        onView(withText("Source airport code is invalid")).check(matches(isDisplayed()));
    }

    @Test
    public void NoDepartureDate(){
        assertNotNull(activity.findViewById(R.id.AddFlight));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.FlightNumber)).perform(clearText(),typeText("123"));
        onView(withId(R.id.Source)).perform(clearText(),typeText("PDX"));
        onView(withId(R.id.AddFlight)).perform(closeSoftKeyboard()).perform(click());
        onView(withText("Departure Date not set properly")).check(matches(isDisplayed()));
    }
    @Test
    public void InvalidDepartureDate(){
        assertNotNull(activity.findViewById(R.id.AddFlight));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.FlightNumber)).perform(clearText(),typeText("123"));
        onView(withId(R.id.Source)).perform(clearText(),typeText("PDX"));
        onView(withId(R.id.DepDate)).perform(clearText(),typeText("xx/1/2000"));
        onView(withId(R.id.AddFlight)).perform(closeSoftKeyboard()).perform(click());
        onView(withText("Departure Date not set properly")).check(matches(isDisplayed()));
    }

    @Test
    public void NoDepartureTime(){
        assertNotNull(activity.findViewById(R.id.AddFlight));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.FlightNumber)).perform(clearText(),typeText("123"));
        onView(withId(R.id.Source)).perform(clearText(),typeText("PDX"));
        onView(withId(R.id.DepDate)).perform(clearText(),typeText("1/1/2000"));
        onView(withId(R.id.AddFlight)).perform(closeSoftKeyboard()).perform(click());
        onView(withText("Departure Time not set properly")).check(matches(isDisplayed()));
    }
    @Test
    public void InvalidDepartureTime(){
        assertNotNull(activity.findViewById(R.id.AddFlight));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.FlightNumber)).perform(clearText(),typeText("123"));
        onView(withId(R.id.Source)).perform(clearText(),typeText("PDX"));
        onView(withId(R.id.DepDate)).perform(clearText(),typeText("1/1/2000"));
        onView(withId(R.id.DepTime)).perform(clearText(),typeText("1:30 XX"));
        onView(withId(R.id.AddFlight)).perform(closeSoftKeyboard()).perform(click());
        onView(withText("Departure Time not set properly")).check(matches(isDisplayed()));
    }
    @Test
    public void NoDestinationCode(){
        assertNotNull(activity.findViewById(R.id.AddFlight));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.FlightNumber)).perform(clearText(),typeText("123"));
        onView(withId(R.id.Source)).perform(clearText(),typeText("PDX"));
        onView(withId(R.id.DepDate)).perform(clearText(),typeText("1/1/2000"));
        onView(withId(R.id.DepTime)).perform(clearText(),typeText("1:30 AM"));
        onView(withId(R.id.AddFlight)).perform(closeSoftKeyboard()).perform(click());
        onView(withText("Destination airport code not valid")).check(matches(isDisplayed()));
    }
    @Test
    public void InValidDestinationCode(){
        assertNotNull(activity.findViewById(R.id.AddFlight));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.FlightNumber)).perform(clearText(),typeText("123"));
        onView(withId(R.id.Source)).perform(clearText(),typeText("PDX"));
        onView(withId(R.id.DepDate)).perform(clearText(),typeText("1/1/2000"));
        onView(withId(R.id.DepTime)).perform(clearText(),typeText("1:30 AM"));
        onView(withId(R.id.Destination)).perform(clearText(),typeText("ABC"));
        onView(withId(R.id.AddFlight)).perform(closeSoftKeyboard()).perform(click());
        onView(withText("Destination airport code not valid")).check(matches(isDisplayed()));
    }

    @Test
    public void NoArrivalDate(){
        assertNotNull(activity.findViewById(R.id.AddFlight));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.FlightNumber)).perform(clearText(),typeText("123"));
        onView(withId(R.id.Source)).perform(clearText(),typeText("PDX"));
        onView(withId(R.id.DepDate)).perform(clearText(),typeText("1/1/2000"));
        onView(withId(R.id.DepTime)).perform(clearText(),typeText("1:30 AM"));
        onView(withId(R.id.Destination)).perform(clearText(),typeText("JFK"));
        onView(withId(R.id.AddFlight)).perform(closeSoftKeyboard()).perform(click());
        onView(withText("Arrival Date not set properly")).check(matches(isDisplayed()));
    }
    @Test
    public void InvalidArrivalDate(){
        assertNotNull(activity.findViewById(R.id.AddFlight));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.FlightNumber)).perform(clearText(),typeText("123"));
        onView(withId(R.id.Source)).perform(clearText(),typeText("PDX"));
        onView(withId(R.id.DepDate)).perform(clearText(),typeText("1/1/2000"));
        onView(withId(R.id.DepTime)).perform(clearText(),typeText("1:30 AM"));
        onView(withId(R.id.Destination)).perform(closeSoftKeyboard()).perform(clearText(),typeText("JFK"));
        onView(withId(R.id.ArrivalDate)).perform(closeSoftKeyboard()).perform(clearText(),typeText("1/2/XXXX"));
        onView(withId(R.id.AddFlight)).perform(closeSoftKeyboard()).perform(click());
        onView(withText("Arrival Date not set properly")).check(matches(isDisplayed()));
    }

    @Test
    public void NoArrivalTime(){
        assertNotNull(activity.findViewById(R.id.AddFlight));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.FlightNumber)).perform(clearText(),typeText("123"));
        onView(withId(R.id.Source)).perform(clearText(),typeText("PDX"));
        onView(withId(R.id.DepDate)).perform(clearText(),typeText("1/1/2000"));
        onView(withId(R.id.DepTime)).perform(clearText(),typeText("1:30 AM"));
        onView(withId(R.id.Destination)).perform(closeSoftKeyboard()).perform(clearText(),typeText("JFK"));
        onView(withId(R.id.ArrivalDate)).perform(closeSoftKeyboard()).perform(clearText(),typeText("1/2/2000"));
        onView(withId(R.id.AddFlight)).perform(closeSoftKeyboard()).perform(click());
        onView(withText("Arrival Time not set properly")).check(matches(isDisplayed()));
    }
    @Test
    public void InvalidArrivalTime(){
        assertNotNull(activity.findViewById(R.id.AddFlight));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.FlightNumber)).perform(clearText(),typeText("123"));
        onView(withId(R.id.Source)).perform(clearText(),typeText("PDX"));
        onView(withId(R.id.DepDate)).perform(clearText(),typeText("1/1/2000"));
        onView(withId(R.id.DepTime)).perform(clearText(),typeText("1:30 AM"));
        onView(withId(R.id.Destination)).perform(closeSoftKeyboard()).perform(clearText(),typeText("JFK"));
        onView(withId(R.id.ArrivalDate)).perform(closeSoftKeyboard()).perform(clearText(),typeText("1/2/2000"));
        onView(withId(R.id.ArrivalTime)).perform(closeSoftKeyboard()).perform(clearText(),typeText("2:XX"));
        onView(withId(R.id.AddFlight)).perform(closeSoftKeyboard()).perform(click());
        onView(withText("Arrival Time not set properly")).check(matches(isDisplayed()));
    }

    @Test
    public void AddingNewFile(){
        assertNotNull(activity.findViewById(R.id.AddFlight));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.FlightNumber)).perform(clearText(),typeText("123"));
        onView(withId(R.id.Source)).perform(clearText(),typeText("PDX"));
        onView(withId(R.id.DepDate)).perform(clearText(),typeText("1/1/2000"));
        onView(withId(R.id.DepTime)).perform(clearText(),typeText("1:30 AM"));
        onView(withId(R.id.Destination)).perform(closeSoftKeyboard()).perform(clearText(),typeText("JFK"));
        onView(withId(R.id.ArrivalDate)).perform(closeSoftKeyboard()).perform(clearText(),typeText("1/2/2000"));
        onView(withId(R.id.ArrivalTime)).perform(closeSoftKeyboard()).perform(clearText(),typeText("2:30 AM"));
        onView(withId(R.id.AddFlight)).perform(closeSoftKeyboard()).perform(click());
        onView(withText("Flight has been added..")).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
                check(matches(isDisplayed()));
    }

    @Test
    public void AddingNewToExistingFile(){
        assertNotNull(activity.findViewById(R.id.AddFlight));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.FlightNumber)).perform(clearText(),typeText("123"));
        onView(withId(R.id.Source)).perform(clearText(),typeText("PDX"));
        onView(withId(R.id.DepDate)).perform(clearText(),typeText("1/1/2000"));
        onView(withId(R.id.DepTime)).perform(clearText(),typeText("1:30 AM"));
        onView(withId(R.id.Destination)).perform(closeSoftKeyboard()).perform(clearText(),typeText("LAX"));
        onView(withId(R.id.ArrivalDate)).perform(closeSoftKeyboard()).perform(clearText(),typeText("1/2/2000"));
        onView(withId(R.id.ArrivalTime)).perform(closeSoftKeyboard()).perform(clearText(),typeText("2:30 AM"));
        onView(withId(R.id.AddFlight)).perform(closeSoftKeyboard()).perform(click());
        onView(withText("Flight has been added..")).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
                check(matches(isDisplayed()));
    }
    @Test
    public void AddingNewFlightToNewFile(){
        assertNotNull(activity.findViewById(R.id.AddFlight));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"+Math.random()));
        onView(withId(R.id.FlightNumber)).perform(clearText(),typeText("123"));
        onView(withId(R.id.Source)).perform(clearText(),typeText("PDX"));
        onView(withId(R.id.DepDate)).perform(clearText(),typeText("1/1/2000"));
        onView(withId(R.id.DepTime)).perform(clearText(),typeText("1:30 AM"));
        onView(withId(R.id.Destination)).perform(closeSoftKeyboard()).perform(clearText(),typeText("LAX"));
        onView(withId(R.id.ArrivalDate)).perform(closeSoftKeyboard()).perform(clearText(),typeText("1/2/2000"));
        onView(withId(R.id.ArrivalTime)).perform(closeSoftKeyboard()).perform(clearText(),typeText("2:30 AM"));
        onView(withId(R.id.AddFlight)).perform(closeSoftKeyboard()).perform(click());
        onView(withText("Flight has been added..")).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
                check(matches(isDisplayed()));
    }


    @Test
    public void ArrivalTimeBeforeDepartureTime(){
        assertNotNull(activity.findViewById(R.id.AddFlight));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.FlightNumber)).perform(clearText(),typeText("123"));
        onView(withId(R.id.Source)).perform(clearText(),typeText("PDX"));
        onView(withId(R.id.DepDate)).perform(clearText(),typeText("1/2/2000"));
        onView(withId(R.id.DepTime)).perform(clearText(),typeText("1:30 AM"));
        onView(withId(R.id.Destination)).perform(closeSoftKeyboard()).perform(clearText(),typeText("LAX"));
        onView(withId(R.id.ArrivalDate)).perform(closeSoftKeyboard()).perform(clearText(),typeText("1/1/2000"));
        onView(withId(R.id.ArrivalTime)).perform(closeSoftKeyboard()).perform(clearText(),typeText("2:30 AM"));
        onView(withId(R.id.AddFlight)).perform(closeSoftKeyboard()).perform(click());
        onView(withText("Arrival date or time is not before departure date or time")).check(matches(isDisplayed()));
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

    @After
    public void TearDown(){
        activity=null;
    }
}
