package com.example.mobiletimecostapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TimeCostCalcHandler extends AppCompatActivity {

    Button calculate;
    Button saveGoal;
    int goalID=0;
    int TCMAUserID=0;
    EditText goalNameInput;
    EditText goalPriceInput;
    EditText weeklyIncomeInput;
    EditText weeklyExpensesInput;
    TextView daysTillCompletionOut;
    String username="";
    int daysTillCompletion=0;
    DBHandler db;
    InputMethodManager imm;
    TCMAGoal goal;
    TCMAUser user;
    String[] daysList;
    CalculateTimeCost calcTimeCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_cost_calculator);

        db = new DBHandler(this);

        goalNameInput = findViewById(R.id.goalNameInput);
        goalPriceInput = findViewById(R.id.goalPriceInput);
        weeklyIncomeInput = findViewById(R.id.weeklyIncomeInput);
        weeklyExpensesInput = findViewById(R.id.weeklyExpensesInput);
        daysTillCompletionOut = findViewById(R.id.daysTillCompletionOut);

        EditText inputs[] = new EditText[]{goalNameInput, goalPriceInput, weeklyIncomeInput, weeklyExpensesInput};

        Intent intent = getIntent();
        if(intent != null) {
            username = intent.getStringExtra("username");
            Log.println(Log.DEBUG, "username:", username + "");
        }

        user = db.getUserInfo(username);
        Log.println(Log.DEBUG, "User.GetFullName: ", user.getFullName() + "");
        //Toast.makeText(TimeCostCalcHandler.this, "User=" + user.getFullName(), Toast.LENGTH_LONG).show();
        weeklyIncomeInput.setText(new Double(user.getGoalWeeklyTotal()).toString());
        weeklyExpensesInput.setText(new Double(user.getWeeklyExpenses()).toString());
        TCMAUserID = user.getTCMAUserID();


        calculate = (Button)findViewById(R.id.calculate);
        calculate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        calcTimeCost = new CalculateTimeCost(Double.parseDouble(goalPriceInput.getText().toString()),
                                Double.parseDouble(weeklyIncomeInput.getText().toString()),
                                Double.parseDouble(weeklyExpensesInput.getText().toString()));
                        daysTillCompletion = calcTimeCost.computeDaysTillComplete();

                        daysTillCompletionOut.setText(new Integer (daysTillCompletion).toString());
                        //Log.println(Log.DEBUG, "daysTillCompletionOut:", daysList[0].toString() + "");
                    }
                }
        );

        saveGoal = (Button)findViewById(R.id.saveGoal);
        saveGoal.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        goal = new TCMAGoal();
                        goal.setGoalName(goalNameInput.getText().toString());
                        goal.setTCMAUserID(TCMAUserID);
                        goal.setGoalCost(Double.parseDouble(goalPriceInput.getText().toString()));
                        goal.setDaysTillCompletion(Integer.parseInt(daysList[0].toString()));

                        if(db.addTABLE_Goal(goal)) {
                            String intentP = username;
                            Intent intent = new Intent(TimeCostCalcHandler.this, GoalHandler.class);
                            intent.putExtra("username", intentP);
                            startActivity(intent);
                        }
                    }
                }
        );

    }

    /*public int computeDaysTillComplete(Double goalPrice, Double weeklyIncome, Double weeklyExpenses){

        daysTillCompletion = 0;
        Double compDays = 0.0;
        daysList = null;

        compDays = (goalPrice / (weeklyIncome - weeklyExpenses)) * 7;

        daysList = (new Double(compDays).toString()).split("\\.");
        daysTillCompletion = Integer.parseInt(daysList[0]);

        return daysTillCompletion;

    }*/
}
