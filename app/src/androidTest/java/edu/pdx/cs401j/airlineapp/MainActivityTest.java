package edu.pdx.cs401j.airlineapp;

import android.app.Activity;
import android.app.Instrumentation;
import android.view.View;

import androidx.test.internal.platform.app.ActivityInvoker;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule=new ActivityTestRule<MainActivity>(MainActivity.class);

    private MainActivity mactivity=null;
    Instrumentation.ActivityMonitor monitor=getInstrumentation().addMonitor(Main2Activity.class.getName(),null,false);
    @Before
    public void setUp() throws Exception {
        mactivity=mainActivityTestRule.getActivity();
    }

    @Test
    public void testingAddFlight() {
        View view=mactivity.findViewById(R.id.AddNewFlight);
        assertNotNull(view);
        onView(withId(R.id.AddNewFlight)).perform(click());
        Activity secondactivity=getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
        assertNotNull(secondactivity);
        secondactivity.finish();
    }


}