package com.example.mobiletimecostapplication;

import android.content.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class DBHandlerUnitTest_backup {

    private boolean validUser = true;
    private String username, password;
    private int userId;

    @Mock
    Context mMockContext;

    @Before
    public void setUp() throws Exception {
        username = "techie";
        password = "techie";
        userId = 5;
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void validUser() {

        DBHandler dbHandler = new DBHandler(mMockContext);
        boolean result = dbHandler.validUser(username);
        assertThat(result, is(validUser));
        //assertTrue(result);
    }

    @Test
    public void getUserID(){
        DBHandler dbHandler = new DBHandler(mMockContext);
        int result = dbHandler.getUserID(username);
        assertEquals(result,userId);
    }
}