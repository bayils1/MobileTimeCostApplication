package com.example.mobiletimecostapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

public class DBHandler extends SQLiteOpenHelper {
    public static final String TABLE_TCMAUser= "TCMAUser";
    public static final String COLUMN_TCMAUserID = "TCMAUserID";
    public static final String COLUMN_FullName = "fullName";
    public static final String COLUMN_Username ="username";
    public static final String COLUMN_Password = "password";
    private static final String COLUMN_goalWeeklyTotal = "goalWeeklyTotal";
    private static final String COLUMN_annualIncome = "annualIncome";
    private static final String COLUMN_ExpensesCost = "expensesCost";
    public static final String TABLE_Goal= "Goal";
    public static final String COLUMN_GoalName = "goalName";
    public static final String COLUMN_GoalCost="GoalCost";
    public static final String COLUMN_GoalID = "goalID";


    private static final String DATABASE_NAME = "TCMA";
    private static final int DATABASE_VERSION = 1;
    private String columnTCMAUserValues[]= {"TCMAUserID","fullName","userName","password","goalWeeklyTotal","annualIncome"};

    private static final String CreateTCMAUserTable = "create table "+ TABLE_TCMAUser +
            "(" + COLUMN_TCMAUserID+" integer primary key autoincrement, " +
            COLUMN_FullName+" text, " +
            COLUMN_Username+" text not null unique," +
            COLUMN_Password+" text not null," +
            COLUMN_goalWeeklyTotal + " numeric," +
            COLUMN_annualIncome + " numeric, " +
            COLUMN_ExpensesCost + " numeric, " +
            "Constraint username_unique UNIQUE (" + COLUMN_Username + ")" +
            "); ";
    private static final String CreateGoalTable = "create table " + TABLE_Goal + "(" +
            COLUMN_GoalID +" integer primary key autoincrement," +
            COLUMN_GoalName+" text, " +
            COLUMN_TCMAUserID+" int not null," +
            COLUMN_GoalCost+" numeric not null);";

    public DBHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database){
        database.execSQL(CreateTCMAUserTable);
        database.execSQL(CreateGoalTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_TCMAUser);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_Goal);
        onCreate(db);
    }

    public void addDummyUser(){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();

        /*
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_TCMAUser);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_Goal);
        onCreate(db);

         */
        values.put(COLUMN_FullName, "Test User");
        values.put(COLUMN_Username, "test");
        values.put(COLUMN_Password, "test");

        db.insert(TABLE_TCMAUser, null, values);
        db.close();
    }

    public boolean validUser(String username){

        int valid = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        SQLiteStatement state = db.compileStatement("select count(*) from " + TABLE_TCMAUser +
                " where " + COLUMN_Username + "='" + username + "'");
        valid = Integer.parseInt(state.simpleQueryForString());
        state.close();
        db.close();
        Log.println(Log.DEBUG, "Valid Count", valid + "");
        return valid == 1;
    }

    public int getUserID(String username)
    {
        int id = -1;
        SQLiteDatabase db = this.getReadableDatabase();
        SQLiteStatement state = db.compileStatement("select "+ COLUMN_TCMAUserID  + " from " + TABLE_TCMAUser +
                " where " + COLUMN_Username + "='" + username + "'");
        try {
            id = Integer.parseInt(state.simpleQueryForString());
        }
        catch(Exception invalidID) {

        }
        state.close();
        db.close();
        return id;
    }


    public TCMAUser getUserObj(String username, String password){
        TCMAUser tempUser = new TCMAUser();

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from " + TABLE_TCMAUser +
                " where " + COLUMN_Username + "='" + username + "' And " + COLUMN_Password + "='" +  password + "'";

        //null is all columns
        Cursor c = db.rawQuery(query, null);

        while(c.moveToNext()) {
            for(int i = 0; i < columnTCMAUserValues.length; i++)
            {
                if(c.getString(i) != null)
                    tempUser.setTCMAUser(c.getString(i), columnTCMAUserValues[i]);
            }
        }
        db.close();
        return tempUser;
    }

    public boolean addTCMAUser(TCMAUser newUser){
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_FullName, newUser.getFullName());
            values.put(COLUMN_Username, newUser.getUserName());
            values.put(COLUMN_Password, newUser.getPassword());
            values.put(COLUMN_goalWeeklyTotal, newUser.getGoalWeeklyTotal());
            values.put(COLUMN_annualIncome, newUser.getAnnualIncome());
            values.put(COLUMN_ExpensesCost, newUser.getWeeklyExpenses());
            db.insert(TABLE_TCMAUser, null, values);
            db.close();
            return true;
        }
        catch(Exception userAddingFailed)
        {
            return false;
        }
    }




}
