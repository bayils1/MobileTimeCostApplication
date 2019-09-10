package com.example.mobiletimecostapplication;

import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matcher;
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


public class RegisterHandlerTest {

    @Rule
    public ActivityTestRule<RegisterHandler> activityRule = new ActivityTestRule<>(RegisterHandler.class);
    private RegisterHandler registerHandler= null;
    private String fullName;
    private String expensesCost;
    private String weeklyIncome;
    private String studentLoan;
    private String username;
    private String password;

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
        onView(withId(R.id.Register)).perform(click());
    }


    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void onCreate() {
    }
}