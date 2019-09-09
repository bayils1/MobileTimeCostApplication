package com.example.mobiletimecostapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class TimeCostCalcHandler extends AppCompatActivity {

    Button calculate;
    int goalID=0;
    int TCMAUserID=0;
    EditText goalNameInput;
    EditText goalPriceInput;
    EditText weeklyIncomeInput;
    EditText weeklyExpensesInput;
    String username;
    int daysTillCompletion=0;
    DBHandler db;
    InputMethodManager imm;
    TCMAGoal goal;
    TCMAUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_cost_calculator);

        db = new DBHandler(this);

        goalNameInput = findViewById(R.id.goalNameInput);
        goalPriceInput = findViewById(R.id.goalPriceInput);
        weeklyIncomeInput = findViewById(R.id.weeklyIncomeInput);
        weeklyExpensesInput = findViewById(R.id.weeklyExpensesInput);

        EditText inputs[] = new EditText[]{goalNameInput, goalPriceInput, weeklyIncomeInput, weeklyExpensesInput};

        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        TCMAUserID = db.getUserID(username);
        //TCMAUserID = 20190001;
        user = db.getUserInfo(username);
        weeklyIncomeInput.setText(new Double(user.getWeeklyIncome()).toString());
        weeklyExpensesInput.setText(new Double(user.getWeeklyExpenses()).toString());


        calculate = (Button)findViewById(R.id.calculate);
        calculate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                }
        );

    }
}
