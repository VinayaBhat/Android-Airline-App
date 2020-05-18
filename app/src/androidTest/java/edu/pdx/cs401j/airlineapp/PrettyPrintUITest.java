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
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static junit.framework.TestCase.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class PrettyPrintUITest {
    @Rule
    public ActivityTestRule<SearchResult> main2activity=new ActivityTestRule<SearchResult>(SearchResult.class){

        @Override
        protected Intent getActivityIntent() {
            Intent intent = new Intent();
            ArrayList<String> flights=new ArrayList<>();
            flights.add("Alaska;123PDX;1/1/00 1:30 AM;LAX;1/1/00 2:30 AM");
            intent.putStringArrayListExtra("airline",flights);
            return intent;
        }
    };

    private SearchResult activity=null;

    @Before
    public void setUp(){
        activity=main2activity.getActivity();
    }

    @Test
    public void ErrorInFlightInfo(){
        onView(withText("Flight number is not set properly")).check(matches(isDisplayed()));
    }



}
