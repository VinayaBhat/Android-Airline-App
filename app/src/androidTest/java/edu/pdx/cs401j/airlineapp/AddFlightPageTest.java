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

public class AddFlightPageTest {
    @Rule
    public ActivityTestRule<AddFlightPage> main2activity=new ActivityTestRule<>(AddFlightPage.class);
    private AddFlightPage activity=null;

    @Before
    public void setUp(){
        activity=main2activity.getActivity();
    }

    /**
     * Clicking Add Flight with no params
     */
    @Test
    public void AddFlight_NoAirlineName_AlertError(){
        assertNotNull(activity.findViewById(R.id.AddFlight));
        onView(withId(R.id.AddFlight)).perform(click());
        onView(withText("Airline name is not set")).check(matches(isDisplayed()));
    }
    /**
     * Clicking Add Flight with no flight number
     */
    @Test
    public void AddFlight_NoFlightNumber_AlertError(){
        assertNotNull(activity.findViewById(R.id.AddFlight));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.AddFlight)).perform(closeSoftKeyboard()).perform(click());
        onView(withText("Flight number is not set properly")).check(matches(isDisplayed()));
    }
    /**
     * Clicking Add Flight with source code
     */
    @Test
    public void AddFlight_NoSourceAirport_AlertError(){
        assertNotNull(activity.findViewById(R.id.AddFlight));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.FlightNumber)).perform(clearText(),typeText("123"));
        onView(withId(R.id.AddFlight)).perform(closeSoftKeyboard()).perform(click());
        onView(withText("Source airport code is invalid")).check(matches(isDisplayed()));
    }
    /**
     * Clicking Add Flight with invalid source code
     */
    @Test
    public void AddFlight_InvalidSourceAirport_AlertError(){
        assertNotNull(activity.findViewById(R.id.AddFlight));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.FlightNumber)).perform(clearText(),typeText("123"));
        onView(withId(R.id.Source)).perform(clearText(),typeText("ABC"));
        onView(withId(R.id.AddFlight)).perform(closeSoftKeyboard()).perform(click());
        onView(withText("Source airport code is invalid")).check(matches(isDisplayed()));
    }
    /**
     * Clicking Add Flight with no departure date
     */
    @Test
    public void AddFlight_NoDepartureDate_AlertError(){
        assertNotNull(activity.findViewById(R.id.AddFlight));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.FlightNumber)).perform(clearText(),typeText("123"));
        onView(withId(R.id.Source)).perform(clearText(),typeText("PDX"));
        onView(withId(R.id.AddFlight)).perform(closeSoftKeyboard()).perform(click());
        onView(withText("Departure Date not set properly")).check(matches(isDisplayed()));
    }
    /**
     * Clicking Add Flight with invalid departure date
     */
    @Test
    public void AddFlight_InvalidDepartureDate_AlertError(){
        assertNotNull(activity.findViewById(R.id.AddFlight));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.FlightNumber)).perform(clearText(),typeText("123"));
        onView(withId(R.id.Source)).perform(clearText(),typeText("PDX"));
        onView(withId(R.id.DepDate)).perform(clearText(),typeText("xx/1/2000"));
        onView(withId(R.id.AddFlight)).perform(closeSoftKeyboard()).perform(click());
        onView(withText("Departure Date not set properly")).check(matches(isDisplayed()));
    }
    /**
     * Clicking Add Flight with no departure time
     */
    @Test
    public void AddFlight_NoDepartureTime_AlertError(){
        assertNotNull(activity.findViewById(R.id.AddFlight));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.FlightNumber)).perform(clearText(),typeText("123"));
        onView(withId(R.id.Source)).perform(clearText(),typeText("PDX"));
        onView(withId(R.id.DepDate)).perform(clearText(),typeText("1/1/2000"));
        onView(withId(R.id.AddFlight)).perform(closeSoftKeyboard()).perform(click());
        onView(withText("Departure Time not set properly")).check(matches(isDisplayed()));
    }
    /**
     * Clicking Add Flight with invalid departure time
     */
    @Test
    public void AddFlight_InvalidDepartureTime_AlertError(){
        assertNotNull(activity.findViewById(R.id.AddFlight));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.FlightNumber)).perform(clearText(),typeText("123"));
        onView(withId(R.id.Source)).perform(clearText(),typeText("PDX"));
        onView(withId(R.id.DepDate)).perform(clearText(),typeText("1/1/2000"));
        onView(withId(R.id.DepTime)).perform(clearText(),typeText("1:30 XX"));
        onView(withId(R.id.AddFlight)).perform(closeSoftKeyboard()).perform(click());
        onView(withText("Departure Time not set properly")).check(matches(isDisplayed()));
    }
    /**
     * Clicking Add Flight with no destination
     */
    @Test
    public void AddFlight_NoDestination_AlertError(){
        assertNotNull(activity.findViewById(R.id.AddFlight));
        onView(withId(R.id.AirlineNameField)).perform(clearText(),typeText("Alaska"));
        onView(withId(R.id.FlightNumber)).perform(clearText(),typeText("123"));
        onView(withId(R.id.Source)).perform(clearText(),typeText("PDX"));
        onView(withId(R.id.DepDate)).perform(clearText(),typeText("1/1/2000"));
        onView(withId(R.id.DepTime)).perform(clearText(),typeText("1:30 AM"));
        onView(withId(R.id.AddFlight)).perform(closeSoftKeyboard()).perform(click());
        onView(withText("Destination airport code not valid")).check(matches(isDisplayed()));
    }
    /**
     * Clicking Add Flight with invalid destination
     */
    @Test
    public void AddFlight_InvalidDestination_AlertError(){
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
    /**
     * Clicking Add Flight with no arrival date
     */
    @Test
    public void AddFlight_NoArrivalDate_AlertError(){
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
    /**
     * Clicking Add Flight with invalid arrival date
     */
    @Test
    public void AddFlight_InvalidArrivalDate_AlertError(){
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
    /**
     * Clicking Add Flight with no arrival time
     */
    @Test
    public void AddFlight_NoArrivalTime_AlertError(){
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
    /**
     * Clicking Add Flight with invalid arrival time
     */
    @Test
    public void AddFlight_InvalidArrivalTime_AlertError(){
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
    /**
     * Clicking Add Flight success
     */
    @Test
    public void AddFlight_NewFlightToNewFile_SuccessToast(){
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
    /**
     * Clicking Add Flight success to existing file
     */
    @Test
    public void AddFlight_NewFlightToExistingFile_SuccessToast(){
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
    /**
     * Clicking Add Flight success to new file
     */
    @Test
    public void AddFlight_AddingNewFlightToNewFile_Success(){
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

    /**
     * Clicking Add Flight with departure date after arrival date
     */
    @Test
    public void AddFlight_ArrivalDateBeforeDeparture_AlertError(){
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

    /**
     * Help Menu test
     */
    @Test
    public void AddFlight_HELPMenu(){
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
