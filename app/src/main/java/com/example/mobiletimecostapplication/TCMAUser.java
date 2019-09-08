package com.example.mobiletimecostapplication;

import android.util.Log;


import java.util.List;

class TCMAUser {
    private int TCMAUserID;
    private String fullName;
    private double goalWeeklyTotal;
    private double annualIncome;
    private boolean studentLoanFlag;
    private String userName;
    private String password;
    private double weeklyExpenses;
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

    public double getWeeklyExpenses() {
        return weeklyExpenses;
    }

    public void setWeeklyExpenses(double weeklyExpenses) {
        this.weeklyExpenses = weeklyExpenses;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public double getWeeklyIncome() {
        return weeklyIncome;
    }

    public void setWeeklyIncome(double weeklyIncome) {
        this.weeklyIncome = weeklyIncome;
    }

    //EndRegion

    public void setTCMAUser(String value, String definition) {
        Log.d("Value", "String is " + value);
        switch (definition) {
            case "TCMAUserID":
                this.TCMAUserID = Integer.parseInt(value);
                break;
            case "fullName":
                this.fullName = value;
                break;
            case "goalWeeklyTotal":
                try {
                    this.goalWeeklyTotal = Double.parseDouble(value);
                } catch (Exception doublefail) {
                    Log.d("Failed", "Double could not parse.");
                }
                break;
            case "annualIncome":
                try {
                    this.annualIncome = Double.parseDouble(value);
                } catch (Exception doublefail) {
                    Log.d("Failed", "Double could not parse.");
                }
                break;
            case "userName":
                this.userName = value;
                break;
            case "password":
                this.password = value;
                break;
            case "weeklyIncome":
                try {
                    this.weeklyIncome= Double.parseDouble(value);
                } catch (Exception doublefail) {
                    Log.d("Failed", "Double could not parse.");
                }
                break;
        }
    }

    public void calculateAnnualIncome() {
        this.annualIncome = weeklyIncome * 52;
    }

    public double getWeeklyIncomeAfterTax() {

       double taxRates[] = {0.33, 0.30, 0.175, 0.105};
       double taxIncrements[] = {14001, 34001, 22001};
       int SL_REPAYMENTTHRESHOLD = 19760;
       double studentLoanRate = 0.12;
       double taxOnIncome = 0;

       this.calculateAnnualIncome();
        if (annualIncome > taxIncrements[0])
        {
            double tempIncome = annualIncome;
            int i = 0;
            for(int j = 2; j > 0 ;j--)
            {
                taxOnIncome += annualIncome * taxRates[i];
                tempIncome -= taxIncrements[j];
                i++;
            }
            taxOnIncome += tempIncome* taxRates[i];
        }
        else
        {
            taxOnIncome = annualIncome * taxRates[3];
        }
        return  studentLoanFlag && annualIncome >= SL_REPAYMENTTHRESHOLD?
                Math.round((annualIncome - taxOnIncome) *( 1 - studentLoanRate) /52 * 100 )/100.0 :
                Math.round((annualIncome - taxOnIncome) /52 * 100) /100.0;
    }
}




