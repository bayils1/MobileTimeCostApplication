package com.example.mobiletimecostapplication;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.sql.Time;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class TimeCostCalcHandlerTest {

    @Rule
    public ActivityTestRule<TimeCostCalcHandler> activityRule = new ActivityTestRule<>(TimeCostCalcHandler.class);
    private TimeCostCalcHandler timeCostCalcHandler= null;
    private String goalNameInput;
    private String goalPriceInput;
    private String weeklyIncome;
    private String weeklyExpenses;

    @Before
    public void setUp() throws Exception {
        timeCostCalcHandler = activityRule.getActivity();
        goalNameInput = "Iphone 8";
        goalPriceInput = "2500";
        weeklyIncome = "800.00";
        weeklyExpenses = "300.00";
    }

    @Test
    public void testTimeCostCalcInput(){
        onView(withId(R.id.goalNameInput)).perform(typeText(goalNameInput));
        onView(withId(R.id.goalPriceInput)).perform(typeText(goalPriceInput));
        onView(withId(R.id.weeklyIncomeInput)).perform(replaceText(weeklyIncome));
        onView(withId(R.id.weeklyExpensesInput)).perform(replaceText(weeklyExpenses));
        closeSoftKeyboard();
        onView(withId(R.id.calculate)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.saveGoal)).perform(click());
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void onCreate() {
    }
}