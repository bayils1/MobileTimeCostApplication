package com.example.mobiletimecostapplication;

import android.content.Context;

import androidx.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static androidx.test.InstrumentationRegistry.getTargetContext;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class DBHandlerUnitTest {

    private boolean validUser = true;
    private String username, password;
    private int userId;
    private DBHandler dbHandler;

    @Before
    public void setUp() throws Exception {
        username = "techie";
        password = "techie";
        userId = 2;
        //getTargetContext().deleteDatabase(DBHandler.DATABASE_NAME);
        dbHandler = new DBHandler(getTargetContext());
    }

    @After
    public void tearDown() throws Exception {
        dbHandler.close();
    }

    @Test
    public void validUser() {

        boolean result = dbHandler.validUser(username);
        assertThat(result, is(validUser));
        assertTrue(result);
        assertEquals(validUser, result);
    }

    @Test
    public void getUserID(){
        int result = dbHandler.getUserID(username);
        assertEquals(userId,result);
    }

    @Test
    public void getGoals(){
        List<TCMAGoal> goalList = dbHandler.getGoals(userId);
        assertThat(goalList.size(), is(5)); //5 is the length of the goalList
    }

    @Test
    public void getUserObj(){
        TCMAUser user = dbHandler.getUserObj(username, password);
        assertEquals(username, user.getUserName());
    }

    @Test
    public void getUserInfo(){
        TCMAUser user = dbHandler.getUserInfo(username);
        assertEquals(username, user.getUserName());
    }
}