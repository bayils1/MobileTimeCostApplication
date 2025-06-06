package com.example.mobiletimecostapplication;

import android.app.Activity;
import android.app.Instrumentation;

import androidx.test.espresso.Espresso;
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
import static org.junit.Assert.*;

public class LoginHandlerTest {

    @Rule
    public ActivityTestRule<LoginHandler> activityRule = new ActivityTestRule<>(LoginHandler.class);
    private LoginHandler loginHandler= null;
    private String username;
    private String password;

    @Before
    public void setUp() throws Exception {
        loginHandler = activityRule.getActivity();
        username ="Kristel";
        password = "Kristel";
    }

    @Test
    public void testCredentialsInput(){
        onView(withId(R.id.userNameInput)).perform(typeText(username));
        onView(withId(R.id.passwordInput)).perform(typeText(password));
        closeSoftKeyboard();
        onView(withId(R.id.logIn)).perform(click());
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void onCreate() {
    }
}