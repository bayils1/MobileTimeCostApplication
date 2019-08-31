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
    private double weeklyExpenses;
    private double weeklyIncome;
    private double taxRates[] = {0.33, 0.30, 0.175, 0.105};
    private int SL_REPAYMENTTHRESHOLD = 19760;
    private double studentLoanRate = 0.12;

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

        this.calculateAnnualIncome();
        double spendableIncome = 0.0;
        double lowTaxed = 0;
        double midTaxed = 0;
        double highTaxed = 0;

        double incomeTemp = annualIncome;
        while(incomeTemp > 0)
        {
            if (incomeTemp >= 14000)
            {
                spendableIncome = ((incomeTemp * (1 - taxRates[3]))- weeklyExpenses) /52.0;
                spendableIncome -= 14000;
            }
            else if(incomeTemp > 48000)
            {
                spendableIncome += ((incomeTemp * (1 - taxRates[2]))- weeklyExpenses) /52.0;
                spendableIncome -= 36000;
            }
            else if(incomeTemp > 70000)
            {

            }

        }

        return spendableIncome;
    }
}




