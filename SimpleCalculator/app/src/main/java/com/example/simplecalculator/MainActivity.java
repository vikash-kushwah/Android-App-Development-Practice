package com.example.simplecalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;
import java.math.RoundingMode;

public class MainActivity extends AppCompatActivity {

    private TextView resultTextView;
    private TextView operationTextView; // Add this field
    private double operand = 0.0;
    private String operation = "";
    private boolean isNewOperation = true;
    private DecimalFormat decimalFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.resultTextView);
        operationTextView = findViewById(R.id.operationTextView); // Initialize the new TextView
        decimalFormat = new DecimalFormat("#.########");
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);

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

    private void updateDisplay(String value) {
        if (value.length() > 15) {
            value = value.substring(0, 15);
        }
        resultTextView.setText(value);
    }

    private void onNumberClick(View view) {
        Button button = (Button) view;
        String currentText = resultTextView.getText().toString();
        String digit = button.getText().toString();

        if (isNewOperation) {
            updateDisplay(digit);
            isNewOperation = false;
        } else if (currentText.equals("0")) {
            updateDisplay(digit);
        } else {
            updateDisplay(currentText + digit);
        }
    }

    private void onOperationClick(String op) {
        try {
            operand = Double.parseDouble(resultTextView.getText().toString());
            operation = op;
            isNewOperation = true;
            // Update the operation display
            operationTextView.setText(decimalFormat.format(operand) + " " + operation);
        } catch (NumberFormatException e) {
            clear();
        }
    }

    private void onEqualsClick() {
        if (operation.isEmpty()) return;

        try {
            double secondOperand = Double.parseDouble(resultTextView.getText().toString());
            double result = 0.0;

            // Update operation display to show complete expression
            String expression = decimalFormat.format(operand) + " " + operation + " " +
                              decimalFormat.format(secondOperand) + " =";
            operationTextView.setText(expression);

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
                    if (secondOperand == 0) {
                        updateDisplay("Error");
                        operationTextView.setText("");
                        isNewOperation = true;
                        return;
                    }
                    result = operand / secondOperand;
                    break;
            }

            updateDisplay(decimalFormat.format(result));
            operation = "";
            operand = result;
            isNewOperation = true;
        } catch (NumberFormatException e) {
            clear();
        }
    }

    private void clear() {
        resultTextView.setText("0");
        operationTextView.setText("");
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
        operationTextView.setText("");
        isNewOperation = true;
    }
}
