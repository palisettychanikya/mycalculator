package com.example.mycalculator;

public class HistoryItem {
    private String calculation;
    private String result;

    public HistoryItem(String calculation, String result) {
        this.calculation = calculation;
        this.result = result;
    }

    public String getCalculation() {
        return calculation;
    }

    public String getResult() {
        return result;
    }
}
