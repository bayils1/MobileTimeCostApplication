package com.example.mobiletimecostapplication;

import android.util.Log;

public class CalculateTimeCost {

    private Double goalPrice, weeklyIncome, weeklyExpenses, compDays;
    private int daysTillCompletion;
    private String[] daysList;

    public CalculateTimeCost(Double goalPrice, Double weeklyIncome, Double weeklyExpenses) {
        this.goalPrice = goalPrice;
        this.weeklyIncome = weeklyIncome;
        this.weeklyExpenses = weeklyExpenses;
        daysTillCompletion = 0;
        compDays = 0.00;
        daysList = null;
    }

    public Double getGoalPrice() {
        return goalPrice;
    }

    public void setGoalPrice(Double goalPrice) {
        this.goalPrice = goalPrice;
    }

    public Double getWeeklyIncome() {
        return weeklyIncome;
    }

    public void setWeeklyIncome(Double weeklyIncome) {
        this.weeklyIncome = weeklyIncome;
    }

    public Double getWeeklyExpenses() {
        return weeklyExpenses;
    }

    public void setWeeklyExpenses(Double weeklyExpenses) {
        this.weeklyExpenses = weeklyExpenses;
    }

    public int computeDaysTillComplete(){

        compDays = (goalPrice / (weeklyIncome - weeklyExpenses)) * 7;

        daysList = (new Double(compDays).toString()).split("\\.");
        daysTillCompletion = Integer.parseInt(daysList[0]);
        return daysTillCompletion;

    }

}
