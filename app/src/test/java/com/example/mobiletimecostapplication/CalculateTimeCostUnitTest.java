package com.example.mobiletimecostapplication;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

public class CalculateTimeCostUnitTest {

    CalculateTimeCost timeCost;
    private Double weeklyIncome, weeklyExpenses, goalPrice;
    private int expectedResult, result;

    @Before
    public void setUp() throws Exception {
        weeklyIncome = 800.00;
        weeklyExpenses = 250.00;
        goalPrice = 2500.00;
        expectedResult = 31;
        timeCost = new CalculateTimeCost(goalPrice, weeklyIncome, weeklyExpenses);
        result = timeCost.computeDaysTillComplete();
    }

    @After
    public void tearDown() throws Exception {
        timeCost = null;
    }

    @Test
    public void computeDaysTillComplete() {
        assertEquals(expectedResult, result);
    }
}