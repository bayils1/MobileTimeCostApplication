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
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertNotNull;


public class RegisterHandlerTestTransition {

    @Rule
    public ActivityTestRule<RegisterHandler> activityRule = new ActivityTestRule<>(RegisterHandler.class);
    private RegisterHandler registerHandler= null;
    private String fullName;
    private String expensesCost;
    private String weeklyIncome;
    private String studentLoan;
    private String username;
    private String password;

    //test the shifting from one activity to another
    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(GoalHandler.class.getName(),
            null, false);

    @Before
    public void setUp() throws Exception {
        registerHandler = activityRule.getActivity();
        fullName = "Bailey";
        expensesCost = "300.00";
        weeklyIncome = "800.00";
        studentLoan = "";
        username ="bailey01";
        password = "bailey01";
    }

    @Test
    public void testUserRegistrationInput(){

        onView(withId(R.id.fullNameInput)).perform(typeText(fullName));
        onView(withId(R.id.WeeklyExpensesInput)).perform(typeText(expensesCost));
        onView(withId(R.id.weeklyIncomeInput)).perform(typeText(weeklyIncome));
        onView(withId(R.id.studentLoanInput)).check(matches(withText(studentLoan)));
        onView(withId(R.id.userNameInput)).perform(typeText(username));
        closeSoftKeyboard();
        onView(withId(R.id.passwordInput)).perform(typeText(password));
        closeSoftKeyboard();

        assertNotNull(registerHandler.findViewById(R.id.Register));
        onView(withId(R.id.Register)).perform(click());

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