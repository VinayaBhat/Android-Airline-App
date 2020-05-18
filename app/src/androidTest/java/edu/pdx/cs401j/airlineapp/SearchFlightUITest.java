package edu.pdx.cs401j.airlineapp;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static junit.framework.TestCase.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class SearchFlightUITest {
    @Rule
    public ActivityTestRule<Main3Activity> main2activity=new ActivityTestRule<Main3Activity>(Main3Activity.class){

        @Override
        protected Intent getActivityIntent() {
            Intent intent = new Intent();
            ArrayList<String> flights=new ArrayList<>();
            flights.add("Alaska;123;PDX;1/1/00 1:30 AM;LAX;1/1/00 2:30 AM");
            intent.putStringArrayListExtra("airline",flights);
            return intent;
        }
    };

    private Main3Activity activity=null;

    @Before
    public void setUp(){
        activity=main2activity.getActivity();
    }

    @Test
    public void PrettyPrintTest(){
        onView(withId(R.id.PrettyPrint)).perform(click());
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


}
