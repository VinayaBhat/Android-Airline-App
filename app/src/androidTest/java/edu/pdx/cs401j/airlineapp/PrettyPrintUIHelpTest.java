package edu.pdx.cs401j.airlineapp;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;

import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static junit.framework.TestCase.assertNotNull;

public class PrettyPrintUIHelpTest{
        @Rule
        public ActivityTestRule<PrettyPrintSearchFlight> main2activity=new ActivityTestRule<PrettyPrintSearchFlight>(PrettyPrintSearchFlight.class){

            @Override
            protected Intent getActivityIntent() {
                Intent intent = new Intent();
                ArrayList<String> flights=new ArrayList<>();
                flights.add("Alaska;123;PDX;1/1/00 1:30 AM;LAX;1/1/00 2:30 AM");
                intent.putStringArrayListExtra("airline",flights);
                return intent;
            }
        };

        private PrettyPrintSearchFlight activity=null;

        @Before
        public void setUp(){
            activity=main2activity.getActivity();
        }

    /**
     * Help Menu Test
     */
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



}
