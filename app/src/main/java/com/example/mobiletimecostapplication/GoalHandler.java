package com.example.mobiletimecostapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class GoalHandler extends AppCompatActivity {

    private LinearLayout main;
    private List<EditText> editTexts = new ArrayList<EditText>();

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

        main = new LinearLayout(this);
        main.setOrientation(LinearLayout.VERTICAL);

        db = new DBHandler(this);
        goalNameInput = findViewById(R.id.goalNameInput);
        daysTillCompletion = findViewById(R.id.daysTillCompletion);

        EditText inputs[] = new EditText[]{goalNameInput, daysTillCompletion};

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        Log.println(Log.DEBUG, "username:", username + "");

        TCMAUserID = db.getUserID(username);
        //TCMAUserID = 20190001;

        goalList = db.getGoals(TCMAUserID);

        if(goalList != null){
            int ctr = 0;

            LinearLayout editTextLayout = new LinearLayout(this);
            editTextLayout.setOrientation(LinearLayout.VERTICAL);
            main.addView(editTextLayout);

            for(TCMAGoal goal : goalList){
                if(ctr==0){
                    goalNameInput.setText(goal.getGoalName());
                    daysTillCompletion.setText(new Integer(goal.getDaysTillCompletion()).toString());
                }else {
                    //create editText during run time
                    //EditText editText = new EditText(this);
                    //editText.setId(ctr);
                    //editTextLayout.addView(editText);
                    //editText.setText(goal.getGoalName());
                    //editTexts.add(editText);

                    //EditText editText2 = new EditText(this);
                    //editText2.setId(ctr);
                    //editTextLayout.addView(editText2);
                    //editText2.setText(new Integer(goal.getDaysTillCompletion()).toString());
                    //editTexts.add(editText2);
                }
                ctr++;
            }
        }

        newGoal = (Button)findViewById(R.id.newGoal);
        newGoal.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String intentP = username;
                        Intent intent = new Intent(GoalHandler.this, TimeCostCalcHandler.class);
                        intent.putExtra("username", intentP);
                        startActivity(intent);
                    }
                }
        );

    }
}
