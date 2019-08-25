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
    public static final String TABLE_TMCAUser= "TMCAUser";
    public static final String COLUMN_TCMAUserID = "TCMAUserID";
    public static final String COLUMN_FullName = "fullName";
    public static final String COLUMN_Username ="username";
    public static final String COLUMN_Password = "password";
    private static final String COLUMN_goalWeeklyTotal = "goalWeeklyTotal";
    private static final String COLUMN_annualIncome = "goalWeeklyTotal";

    private static final String DATABASE_NAME = "TCMA";
    private static final int DATABASE_VERSION = 1;

    private static final String CreateDatabaseSQL = "create table "+ TABLE_TMCAUser +
            "(" + COLUMN_TCMAUserID+" integer primary key autoincrement, " +
            COLUMN_FullName+" text, " +
            COLUMN_Username+" text not null unique," +
            COLUMN_Password+" text not null," +
            COLUMN_goalWeeklyTotal + " numeric," +
            COLUMN_annualIncome + "numeric, " +
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
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_TMCAUser);

        onCreate(db);
    }

    public void addDummyUser(){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_Username, "test2");
        values.put(COLUMN_Password, "test3");
        db.insert(TABLE_TMCAUser, null, values);
        db.close();
    }

    public int getUser(String username, String password){
        int count = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        SQLiteStatement state = db.compileStatement("select count(*) from " + TABLE_TMCAUser +
                " where username='" + username + "' And " + COLUMN_Password + "='" +  password + "'");
        count = Integer.parseInt(state.simpleQueryForString());
        db.close();
        return count;
    }




}
