package com.example.calculator;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String expr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView input = findViewById(R.id.input);
        TextView result = findViewById(R.id.result);

        int[] numberButtonIds = {R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4,
                R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9,
                R.id.buttonPlus, R.id.buttonMinus,  R.id.buttonMul, R.id.buttonDiv, R.id.buttonDot};

        for (int id : numberButtonIds) {
            Button button = findViewById(id);
            button.setOnClickListener( v -> {
                    expr += button.getText().toString();
                    input.setText(expr);
            });
        }

        Button buttonEqs = findViewById(R.id.buttonEq);
        buttonEqs.setOnClickListener( v -> {
                result.setText(evaluateExpression(input.getText().toString()));
        });

        Button buttonClear = findViewById(R.id.buttonClr);
        buttonClear.setOnClickListener( v -> {
                input.setText("");
                result.setText("");
                expr = "";
        });
    }

    private String evaluateExpression(String expression) {
        try {
            ArrayList<String> tokens = new ArrayList<>();
            StringBuilder current = new StringBuilder();

            for (int i = 0; i < expression.length(); i++) {
                char c = expression.charAt(i);

                if (c == '+' || c == '-' || c == '*' || c == '/') {
                    tokens.add(current.toString());
                    tokens.add(String.valueOf(c));
                    current = new StringBuilder();
                } else {
                    current.append(c);
                }
            }

            tokens.add(current.toString());
            double result = Double.parseDouble(tokens.get(0));

            for (int i = 1; i < tokens.size() - 1; i += 2) {
                char operator = tokens.get(i).charAt(0);
                double operand = Double.parseDouble(tokens.get(i+1));

                switch (operator) {
                    case '+':
                        result += operand;
                        break;
                    case '-':
                        result -= operand;
                        break;
                    case '*':
                        result *= operand;
                        break;
                    case '/':
                        result /= operand;
                        break;
                    default:
                        return "Error";
                }
            }

            int intResult = (int) result;
            if (result == intResult) {
                return String.valueOf(intResult);
            } else {
                return String.valueOf(result);
            }
        } catch (Exception e) {
            return "Error";
        }
    }
}