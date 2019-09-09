package com.example.mobiletimecostapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class GoalHandler extends AppCompatActivity {

    Button newGoal;
    EditText goalNameInput;
    EditText daysTillCompletion;
    int TCMAUserID=0;
    String username="";
    DBHandler db;
    InputMethodManager imm;
    List<TCMAGoal> goalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_overview);

        db = new DBHandler(this);
        goalNameInput = findViewById(R.id.goalNameInput);
        daysTillCompletion = findViewById(R.id.daysTillCompletion);

        EditText inputs[] = new EditText[]{goalNameInput, daysTillCompletion};

        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        TCMAUserID = db.getUserID(username);
        //TCMAUserID = 20190001;

        goalList = db.getGoals(TCMAUserID);

        if(goalList != null){
            int ctr = 0;
            for(TCMAGoal goal : goalList){

                if(ctr==0){
                    goalNameInput.setText(goal.getGoalName());
                    daysTillCompletion.setText(goal.getDaysTillCompletion());
                }else {
                    //create editText during run time
                }
                ctr++;
            }
        }

        newGoal = (Button)findViewById(R.id.newGoal);
        newGoal.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String intentP = username.toString();
                        Intent intent = new Intent(GoalHandler.this, TimeCostCalcHandler.class);
                        intent.putExtra("username", intentP);
                        startActivity(intent);
                    }
                }
        );

    }
}
