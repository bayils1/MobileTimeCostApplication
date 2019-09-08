package com.example.mobiletimecostapplication;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class TimeCostCalcHandler extends AppCompatActivity {

    Button calculate;
    int goalID;
    int TCMAUserID;
    EditText goalNameInput;
    EditText goalPriceInput;
    EditText weeklyIncomeInput;
    EditText weeklyExpensesInput;
    String username;
    int daysTillCompletion;
    DBHandler db;
    InputMethodManager imm;
    TCMAGoal goal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_cost_calculator);

        db = new DBHandler(this);
        calculate = (Button)findViewById(R.id.calculate);
        calculate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        goalNameInput = findViewById(R.id.goalNameInput);
                        goalPriceInput = findViewById(R.id.goalPriceInput);
                        weeklyIncomeInput = findViewById(R.id.weeklyIncomeInput);
                        weeklyExpensesInput = findViewById(R.id.weeklyExpensesInput);

                        EditText inputs[] = new EditText[]{goalNameInput, goalPriceInput, weeklyIncomeInput, weeklyExpensesInput};

                        Bundle bundle = getIntent().getExtras();
                        String text= bundle.getString("username");

                        TCMAUserID = db.getUserID(username);






                    }
                }
        );

    }
}
