package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String currentInput = ""; // 現在の入力を保持する変数
    List<Double> numbers = new ArrayList<>(); // 入力された数値を保持するリスト
    List<String> operators = new ArrayList<>(); // 入力された演算子を保持するリスト

    TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultText = findViewById(R.id.resultText);
    }

    public void numClick(View v){
        Button button = (Button) v;
        String num = button.getText().toString();
        if (num.equals(".") && !currentInput.contains(".")) {
            currentInput += ".";
        } else if (!num.equals(".")) {
            currentInput += num;
        }
        resultText.setText(currentInput);
    }

    public void markClick(View v){
        Button button = (Button) v;
        String op = button.getText().toString();
        if (!currentInput.isEmpty()) {
            numbers.add(Double.parseDouble(currentInput));
            currentInput = "";
        }
        operators.add(op);
    }

    public void clearClick(View view) {
        resultText.setText("0");
        currentInput = "";
        numbers.clear();
        operators.clear();
    }

    public void equalClick(View v){
        if (!currentInput.isEmpty()) {
            numbers.add(Double.parseDouble(currentInput));
            currentInput = "";
        }

        double result = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            switch (operators.get(i - 1)) {
                case "+":
                    result += numbers.get(i);
                    break;
                case "-":
                    result -= numbers.get(i);
                    break;
                case "×":
                    result *= numbers.get(i);
                    break;
                case "÷":
                    if (numbers.get(i) != 0) {
                        result /= numbers.get(i);
                    } else {
                        resultText.setText("Error");
                        return;
                    }
                    break;
            }
        }

        resultText.setText(String.format("%.2f", result));
        numbers.clear();
        operators.clear();
    }
}
