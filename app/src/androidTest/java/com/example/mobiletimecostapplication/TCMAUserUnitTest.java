package com.example.mobiletimecostapplication;

import androidx.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class TCMAUserUnitTest {

    private TCMAUser tcmaUser;
    private double annualIncome, expectedResult;

    @Before
    public void setUp() throws Exception {
        annualIncome = 95000.00;
        expectedResult = 1373.26;
    }

    @After
    public void tearDown() throws Exception {
        tcmaUser = null;
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void calculateAnnualIncome() {
        // arrange
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(equalTo("Attempt to invoke virtual method: ['void com.example.mobiletimecostapplication.TCMAUser.calculateAnnualIncome()' on a null object reference]"));
        // act
        tcmaUser.calculateAnnualIncome();
    }

    @Test
    public void getWeeklyIncomeAfterTax() {
    }
}