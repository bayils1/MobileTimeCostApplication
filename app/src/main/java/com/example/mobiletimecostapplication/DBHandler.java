package com.example.mobiletimecostapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

public class DBHandler extends SQLiteOpenHelper {
    public static final String TABLE_TCMAUser= "TMCAUser";
    public static final String COLUMN_TCMAUserID = "TCMAUserID";
    public static final String COLUMN_FullName = "fullName";
    public static final String COLUMN_Username ="username";
    public static final String COLUMN_Password = "password";
    private static final String COLUMN_goalWeeklyTotal = "goalWeeklyTotal";
    private static final String COLUMN_annualIncome = "annualTotal";
    private static final String COLUMN_ExpensesCost = "expensesCost";

    private static final String DATABASE_NAME = "TCMA";
    private static final int DATABASE_VERSION = 1;
    private String columnValue[]= {"TCMAUserID","fullName","userName","password","goalWeeklyTotal","annualIncome"};

    private static final String CreateDatabaseSQL = "create table "+ TABLE_TCMAUser +
            "(" + COLUMN_TCMAUserID+" integer primary key autoincrement, " +
            COLUMN_FullName+" text, " +
            COLUMN_Username+" text not null unique," +
            COLUMN_Password+" text not null," +
            COLUMN_goalWeeklyTotal + " numeric," +
            COLUMN_annualIncome + "numeric, " +
            COLUMN_ExpensesCost + "numeric, " +
            "Constraint username_unique UNIQUE (" + COLUMN_Username + ")" +
            ");";

    public DBHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database){
        database.execSQL(CreateDatabaseSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_TCMAUser);
        onCreate(db);
    }

    public void addDummyUser(){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();

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
        return valid == 1;
    }

    public TCMAUser getUserObj(String username, String password){
        TCMAUser tempUser = new TCMAUser();

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from " + TABLE_TCMAUser +
                " where " + COLUMN_Username + "='" + username + "' And " + COLUMN_Password + "='" +  password + "'";

        //null is all columns
        Cursor c = db.rawQuery(query, null);

        while(c.moveToNext()) {
            for(int i = 0; i < columnValue.length; i++)
            {
                if(c.getString(i) != null)
                    tempUser.setTCMAUser(c.getString(i),columnValue[i]);
            }

        }


        db.close();
        return tempUser;
    }



}
