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
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static junit.framework.TestCase.assertNotNull;

@RunWith(AndroidJUnit4.class)

public class ReadMeTest {
    @Rule
    public ActivityTestRule<README> main2activity=new ActivityTestRule<>(README.class);
    private README activity=null;

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

    @After
    public void TearDown(){
        activity=null;
    }

}
