package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView displayTextView;
    private StringBuilder currentNumber;
    private StringBuilder equationBuilder;
    private double result;
    private String operator;
    private boolean hasOperator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayTextView = findViewById(R.id.display_text_view);
        currentNumber = new StringBuilder();
        equationBuilder = new StringBuilder();
        operator = "";
        hasOperator = false;

        Button button0 = findViewById(R.id.button_0);
        Button button1 = findViewById(R.id.button_1);
        Button button2 = findViewById(R.id.button_2);
        Button button3 = findViewById(R.id.button_3);
        Button button4 = findViewById(R.id.button_4);
        Button button5 = findViewById(R.id.button_5);
        Button button6 = findViewById(R.id.button_6);
        Button button7 = findViewById(R.id.button_7);
        Button button8 = findViewById(R.id.button_8);
        Button button9 = findViewById(R.id.button_9);
        Button buttonDot = findViewById(R.id.button_dot);
        Button buttonPlus = findViewById(R.id.button_plus);
        Button buttonMinus = findViewById(R.id.button_minus);
        Button buttonMultiply = findViewById(R.id.button_multiply);
        Button buttonDivide = findViewById(R.id.button_divide);
        Button buttonEquals = findViewById(R.id.button_equals);
        Button buttonClear = findViewById(R.id.button_clear);

        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        buttonDot.setOnClickListener(this);
        buttonPlus.setOnClickListener(this);
        buttonMinus.setOnClickListener(this);
        buttonMultiply.setOnClickListener(this);
        buttonDivide.setOnClickListener(this);
        buttonEquals.setOnClickListener(this);
        buttonClear.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();

        switch (viewId) {
            case R.id.button_0:
            case R.id.button_1:
            case R.id.button_2:
            case R.id.button_3:
            case R.id.button_4:
            case R.id.button_5:
            case R.id.button_6:
            case R.id.button_7:
            case R.id.button_8:
            case R.id.button_9:
            case R.id.button_dot:
                handleDigitInput(((Button) view).getText().toString());
                break;
            case R.id.button_plus:
            case R.id.button_minus:
            case R.id.button_multiply:
            case R.id.button_divide:
                handleOperatorInput(((Button) view).getText().toString());
                break;
            case R.id.button_equals:
                calculateResult();
                break;
            case R.id.button_clear:
                clearDisplay();
                break;
        }
    }

    private void handleDigitInput(String digit) {
        currentNumber.append(digit);
        equationBuilder.append(digit);
        displayTextView.setText(equationBuilder.toString());
    }

    private void handleOperatorInput(String newOperator) {
        if (currentNumber.length() > 0) {
            equationBuilder.append(newOperator);
            displayTextView.setText(equationBuilder.toString());
            calculateResult();
        } else if (equationBuilder.length() > 0) {
            equationBuilder.setLength(equationBuilder.length() - 1);
            equationBuilder.append(newOperator);
            displayTextView.setText(equationBuilder.toString());
        }
        operator = newOperator;
        hasOperator = true;
    }

    private void calculateResult() {
        if (currentNumber.length() == 0) {
            return;
        }

        double number = Double.parseDouble(currentNumber.toString());
        currentNumber.setLength(0);

        if (hasOperator) {
            switch (operator) {
                case "+":
                    result += number;
                    break;
                case "-":
                    result -= number;
                    break;
                case "*":
                    result *= number;
                    break;
                case "/":
                    if (number != 0) {
                        result /= number;
                    }
                    break;
            }
        } else {
            result = number;
        }

        DecimalFormat decimalFormat = new DecimalFormat("#.########");
        String formattedResult = decimalFormat.format(result);
        displayTextView.setText(formattedResult);

        hasOperator = false;
    }

    private void clearDisplay() {
        currentNumber.setLength(0);
        equationBuilder.setLength(0);
        result = 0;
        operator = "";
        hasOperator = false;
        displayTextView.setText("0");
    }
}
