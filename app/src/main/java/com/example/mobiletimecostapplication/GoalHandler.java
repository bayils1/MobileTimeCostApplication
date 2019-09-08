package com.example.mobiletimecostapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class GoalHandler extends AppCompatActivity {

    Button newGoal;
    EditText goalNameInput;
    EditText daysTillCompletion;
    int TCMAUserID;
    String username;
    DBHandler db;
    InputMethodManager imm;
    List<TCMAGoal> goalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_overview);

        db = new DBHandler(this);
        newGoal = (Button)findViewById(R.id.newGoal);
        newGoal.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        goalNameInput = findViewById(R.id.goalNameInput);
                        daysTillCompletion = findViewById(R.id.daysTillCompletion);

                        EditText inputs[] = new EditText[]{goalNameInput, daysTillCompletion};

                        Bundle bundle = getIntent().getExtras();
                        String text= bundle.getString("username");

                        TCMAUserID = db.getUserID(username);

                        goalList= db.getGoals(TCMAUserID);

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
                }
        );

    }
}
