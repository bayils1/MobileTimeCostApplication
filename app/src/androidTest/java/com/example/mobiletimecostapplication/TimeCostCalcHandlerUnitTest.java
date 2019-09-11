package com.example.mobiletimecostapplication;

import android.app.Application;
import android.content.Context;

import androidx.test.filters.SmallTest;
import androidx.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class TimeCostCalcHandlerUnitTest {

    private TimeCostCalcHandler timeCostCalcHandler;
    private Double weeklyIncome, weeklyExpenses, goalPrice;
    private int expectedResult;
    private String username;

    @Before
    public void setUp() throws Exception {
        weeklyIncome = 800.00;
        weeklyExpenses = 250.00;
        goalPrice = 2500.00;
        expectedResult = 31;
    }

    @After
    public void tearDown() throws Exception {
        timeCostCalcHandler = null;
    }

    @Test
    public void onCreate() {
    }

    @Test
    public void computeDaysTillComplete(){
        int result = timeCostCalcHandler.computeDaysTillComplete(goalPrice, weeklyIncome, weeklyExpenses);
        assertEquals(expectedResult, result);
    }
}