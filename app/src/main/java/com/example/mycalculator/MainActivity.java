package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private double num1 = 0.0;
    private String operator = "";
    private boolean isOperatorClicked = false;
    private List<HistoryItem> historyItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText);
    }

    public void onButtonClicked(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();

        if (!isOperatorClicked) {
            editText.setText(editText.getText() + buttonText);
        } else {
            editText.setText(buttonText);
            isOperatorClicked = false;
        }
    }

    public void onOperatorClicked(View view) {
        Button button = (Button) view;
        String newOperator = button.getText().toString();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            if (operator.isEmpty()) {
                if (!isOperatorClicked) {
                    //calculate();
                    isOperatorClicked = true;
                }
            }
        }

        operator = newOperator;
        num1 = Double.parseDouble(editText.getText().toString());
        editText.setText(newOperator);
    }

    public void onEqualClicked(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            if (!operator.isEmpty()) {
                if (!isOperatorClicked) {
                    calculate();
                    isOperatorClicked = true;
                }
            }
        }
    }

    private void calculate() {
        double num2 = Double.parseDouble(editText.getText().toString());
        double result = 0.0;

        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    editText.setText("Error");
                    return;
                }
                break;
        }
        String calculation = num1 + " " + operator + " " + num2;
        String resultstr = String.valueOf(result);
        historyItems.add(new HistoryItem(calculation, resultstr));
        updateHistoryView();
        editText.setText(String.valueOf(result));
        operator = "";
    }

    private void updateHistoryView() {
        RecyclerView recyclerView = findViewById(R.id.historyRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        HistoryAdapter adapter = new HistoryAdapter(historyItems);
        recyclerView.setAdapter(adapter);
    }

    public void onClearClicked(View view) {
        editText.setText("");
        num1 = 0.0;
        operator = "";
        isOperatorClicked = false;
    }
}