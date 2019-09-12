package com.example.mobiletimecostapplication;

import android.app.Activity;
import android.app.Instrumentation;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertNotNull;

public class LoginHandlerTestTransition {

    @Rule
    public ActivityTestRule<LoginHandler> activityRule = new ActivityTestRule<>(LoginHandler.class);
    private LoginHandler loginHandler= null;
    private String username;
    private String password;

    //test the shifting from one activity to another
    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(RegisterHandler.class.getName(),
            null, false);


    @Before
    public void setUp() throws Exception {
        loginHandler = activityRule.getActivity();
        username ="Kristel";
        password = "Kristel";
    }

    @Test
    public void testCredentialsInput(){

        assertNotNull(loginHandler.findViewById(R.id.logIn));
        onView(withId(R.id.logIn)).perform(click());

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