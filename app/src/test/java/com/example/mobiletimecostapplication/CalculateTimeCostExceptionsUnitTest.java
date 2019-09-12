package com.example.mobiletimecostapplication;

import org.hamcrest.core.IsInstanceOf;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class CalculateTimeCostExceptionsUnitTest {

    CalculateTimeCost timeCost;
    private Double weeklyIncome, weeklyExpenses, goalPrice;
    private int expectedResult, result;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
        timeCost = null;
    }

    @Test
    public void throwsExceptionWhenNegativeNumbersAreGiven(){

        try{
            weeklyIncome = -800.00;
            weeklyExpenses = -250.00;
            goalPrice = 2500.00;
            expectedResult = 31;
            timeCost = new CalculateTimeCost(goalPrice, weeklyIncome, weeklyExpenses);
            result = timeCost.computeDaysTillComplete();
            //fail("Should throw an exception if one or more of given numbers are negative");
            //assertEquals(expectedResult, result);
        }catch (Exception e){
            assertThat(e, IsInstanceOf.instanceOf(IllegalArgumentException.class));
            assertEquals(e.getMessage(), "negatives not allowed: [-800.00, -250.00]");
        }
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Test
    public void jUnitRuleWhenNegativeNumbersAreGiven(){

            weeklyIncome = -800.00;
            weeklyExpenses = -250.00;
            goalPrice = 2500.00;
            expectedResult = 31;
            //arrange
            thrown.expect(IllegalArgumentException.class);
            thrown.expectMessage(equalTo("negatives not allowed: [-800.00, -250.00]"));
            // act
            timeCost = new CalculateTimeCost(goalPrice, weeklyIncome, weeklyExpenses);
            result = timeCost.computeDaysTillComplete();
    }

    @Test (expected = IllegalArgumentException.class)
    public void annotationWhenNegativeNumbersAreGiven(){

        weeklyIncome = -800.00;
        weeklyExpenses = -250.00;
        goalPrice = 2500.00;
        expectedResult = 31;
        // act
        timeCost = new CalculateTimeCost(goalPrice, weeklyIncome, weeklyExpenses);
        result = timeCost.computeDaysTillComplete();
    }
}