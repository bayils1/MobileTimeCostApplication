package com.example.mobiletimecostapplication;

import android.graphics.Region;
import android.util.Log;


import java.util.List;

class TCMAUser {
    private int TCMAUserID;
    private String fullName;
    private double goalWeeklyTotal;
    private double annualIncome;
    private boolean studentLoanFlag;
    private List<Goal> userGoalList;
    private String userName;


    private String password;
    private double weeklyIncome;


    //Region GettersSetters
    public int getTCMAUserID() {
        return TCMAUserID;
    }
    public void setTCMAUserID(int TCMAUserID) {
        this.TCMAUserID = TCMAUserID;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public double getGoalWeeklyTotal() {
        return goalWeeklyTotal;
    }
    public void setGoalWeeklyTotal(double goalWeeklyTotal) {
        this.goalWeeklyTotal = goalWeeklyTotal;
    }
    public double getAnnualIncome() {
        return annualIncome;
    }
    public void setAnnualIncome(double annualIncome) {
        this.annualIncome = annualIncome;
    }
    public boolean isStudentLoanFlag() {
        return studentLoanFlag;
    }
    public void setStudentLoanFlag(boolean studentLoanFlag) {
        this.studentLoanFlag = studentLoanFlag;
    }
    public List<Goal> getUserGoalList() {
        return userGoalList;
    }
    public void setUserGoalList(List<Goal> userGoalList) {
        this.userGoalList = userGoalList;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    //EndRegion

    public void setTCMAUser(String value, String definition)
    {
        Log.d("Value", "String is " + value);
        switch(definition)
        {
            case "TCMAUserID":
                this.TCMAUserID = Integer.parseInt(value);
                break;
            case "fullName":
                this.fullName = value;
                break;
            case "goalWeeklyTotal":
                try {
                    this.goalWeeklyTotal = Double.parseDouble(value);
                }
                catch(Exception doublefail){
                    Log.d("Failed", "Double could not parse.");}
                break;
            case "annualIncome":
                try {
                    this.annualIncome = Double.parseDouble(value);
                }
                catch(Exception doublefail){
                    Log.d("Failed", "Double could not parse.");}
               break;
            case "userName":
                this.userName = value;
                break;
            case "password":
                this.password = value;
                break;

        }
    }


}
