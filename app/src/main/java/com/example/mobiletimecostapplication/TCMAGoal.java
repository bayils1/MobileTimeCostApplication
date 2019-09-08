package com.example.mobiletimecostapplication;

import android.util.Log;

import java.util.List;

public class TCMAGoal {

    private int goalID;
    private String goalName;
    private int TCMAUserID;
    private double goalCost;
    private List<TCMAGoal> userGoalsList;

    public int getGoalID() {
        return goalID;
    }

    public void setGoalID(int goalID) {
        this.goalID = goalID;
    }

    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    public int getTCMAUserID() {
        return TCMAUserID;
    }

    public void setTCMAUserID(int TCMAUserID) {
        this.TCMAUserID = TCMAUserID;
    }

    public double getGoalCost() {
        return goalCost;
    }

    public void setGoalCost(double goalCost) {
        this.goalCost = goalCost;
    }

    public List<TCMAGoal> getUserGoalHandlerList() {
        return userGoalsList;
    }

    public void setUserGoalsList(List<TCMAGoal> userGoalsList) {
        this.userGoalsList = userGoalsList;
    }

    public TCMAGoal(int goalID, String goalName, int TCMAUserID, double goalCost) {
        this.goalID = goalID;
        this.goalName = goalName;
        this.TCMAUserID = TCMAUserID;
        this.goalCost = goalCost;
    }

    public void setTCMAGoal(String value, String definition) {
        Log.d("Value", "String is " + value);
        switch (definition) {
            case "goalID":
                this.goalID = Integer.parseInt(value);
                break;
            case "goalName":
                this.goalName = value;
                break;
            case "TCMAUserID":
                this.TCMAUserID = Integer.parseInt(value);
                break;
            case "goalCost":
                try {
                    this.goalCost = Double.parseDouble(value);
                } catch (Exception doublefail) {
                    Log.d("Failed", "Double could not parse.");
                }
                break;
        }
    }
}
