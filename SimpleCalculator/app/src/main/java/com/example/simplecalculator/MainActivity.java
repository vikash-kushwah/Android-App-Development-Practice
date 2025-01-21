package com.example.simplecalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView resultTextView;
    private double operand = 0.0;
    private String operation = "";
    private boolean isNewOperation = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.resultTextView);

        setNumberClickListeners();
        setOperationClickListeners();
    }

    private void setNumberClickListeners() {
        int[] numberButtonIds = {
                R.id.zeroButton, R.id.oneButton, R.id.twoButton, R.id.threeButton,
                R.id.fourButton, R.id.fiveButton, R.id.sixButton, R.id.sevenButton,
                R.id.eightButton, R.id.nineButton
        };

        for (int id : numberButtonIds) {
            findViewById(id).setOnClickListener(this::onNumberClick);
        }

        findViewById(R.id.decimalButton).setOnClickListener(view -> {
            if (isNewOperation) {
                resultTextView.setText("0.");
                isNewOperation = false;
            } else if (!resultTextView.getText().toString().contains(".")) {
                resultTextView.append(".");
            }
        });
    }

    private void setOperationClickListeners() {
        findViewById(R.id.plusButton).setOnClickListener(view -> onOperationClick("+"));
        findViewById(R.id.minusButton).setOnClickListener(view -> onOperationClick("-"));
        findViewById(R.id.multiplyButton).setOnClickListener(view -> onOperationClick("*"));
        findViewById(R.id.divideButton).setOnClickListener(view -> onOperationClick("/"));

        findViewById(R.id.equalsButton).setOnClickListener(view -> onEqualsClick());
        findViewById(R.id.clearButton).setOnClickListener(view -> clear());
        findViewById(R.id.backButton).setOnClickListener(view -> backspace());
        findViewById(R.id.ACButton).setOnClickListener(view -> resetAll());
    }

    private void onNumberClick(View view) {
        Button button = (Button) view;
        if (isNewOperation) {
            resultTextView.setText(button.getText().toString());
            isNewOperation = false;
        } else {
            resultTextView.append(button.getText().toString());
        }
    }

    private void onOperationClick(String op) {
        operand = Double.parseDouble(resultTextView.getText().toString());
        operation = op;
        isNewOperation = true;
    }

    private void onEqualsClick() {
        double secondOperand = Double.parseDouble(resultTextView.getText().toString());
        double result = 0.0;

        switch (operation) {
            case "+":
                result = operand + secondOperand;
                break;
            case "-":
                result = operand - secondOperand;
                break;
            case "*":
                result = operand * secondOperand;
                break;
            case "/":
                if (secondOperand != 0) {
                    result = operand / secondOperand;
                } else {
                    resultTextView.setText("Error");
                    isNewOperation = true;
                    return;
                }
                break;
        }

        resultTextView.setText(new DecimalFormat("#.##").format(result));
        isNewOperation = true;
    }

    private void clear() {
        resultTextView.setText("0");
        isNewOperation = true;
    }

    private void backspace() {
        String text = resultTextView.getText().toString();
        if (text.length() > 1) {
            resultTextView.setText(text.substring(0, text.length() - 1));
        } else {
            resultTextView.setText("0");
            isNewOperation = true;
        }
    }

    private void resetAll() {
        operand = 0.0;
        operation = "";
        resultTextView.setText("0");
        isNewOperation = true;
    }
}
