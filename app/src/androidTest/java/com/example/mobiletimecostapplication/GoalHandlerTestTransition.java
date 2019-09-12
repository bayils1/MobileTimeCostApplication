package com.example.mobiletimecostapplication;

import android.app.Activity;
import android.app.Instrumentation;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertNotNull;

public class GoalHandlerTestTransition {

    @Rule
    public ActivityTestRule<GoalHandler> activityRule = new ActivityTestRule<>(GoalHandler.class);
    private GoalHandler goalHandler= null;
    private String goalNameInput;
    private String daysTillCompletion;

    //test the shifting from one activity to another
    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(TimeCostCalcHandler.class.getName(),
            null, false);

    @Before
    public void setUp() throws Exception {
        goalHandler = activityRule.getActivity();
        goalNameInput = "Iphone 8";
        daysTillCompletion = "50";
    }

    @Test
    public void testGoalOverviewInput(){
        onView(withId(R.id.goalNameInput)).perform(typeText(goalNameInput));
        onView(withId(R.id.daysTillCompletion)).perform(typeText(daysTillCompletion));
        closeSoftKeyboard();

        assertNotNull(goalHandler.findViewById(R.id.newGoal));
        onView(withId(R.id.newGoal)).perform(click());

        Activity secondActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
        assertNotNull(secondActivity);
        secondActivity.finish();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void onCreate() {
    }
}