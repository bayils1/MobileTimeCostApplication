package com.example.mobiletimecostapplication;

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
import static org.junit.Assert.*;

public class GoalHandlerTest {

    @Rule
    public ActivityTestRule<GoalHandler> activityRule = new ActivityTestRule<>(GoalHandler.class);
    private GoalHandler goalHandler= null;
    private String goalNameInput;
    private String daysTillCompletion;

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
        onView(withId(R.id.newGoal)).perform(click());
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void onCreate() {
    }
}