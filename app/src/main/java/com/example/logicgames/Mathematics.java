package com.example.logicgames;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;


public class Mathematics extends AppCompatActivity {
    private TextView expression1, expression2, scoreView;
    private Button firstButton, equalButton, secondButton;
    private int score = 0;

    // Method to evaluate a math expression and return the result
    private double evaluateExpression(String expressionStr) {
        try {
            String[] expressionArr = expressionStr.split(" ");
            double num1 = Integer.parseInt(expressionArr[0]);
            double num2 = Integer.parseInt(expressionArr[2]);
            String operator = expressionArr[1];

            switch (operator) {
                case "+":
                    return num1 + num2;
                case "-":
                    return num1 - num2;
                case "*":
                    return num1 * num2;
                case "/":
                    if (num2 != 0) {
                        return num1 / num2;
                    } else {
                        Toast.makeText(this, "Division by zero not allowed", Toast.LENGTH_SHORT).show();
                        return 0;
                    }
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Method to generate a random math operator (+, -, *, /)
    private String getRandomOperator() {
        int operatorIndex = (int) (Math.random() * 4);
        switch (operatorIndex) {
            case 0:
                return "+";
            case 1:
                return "-";
            case 2:
                return "*";
            case 3:
                return "/";
            default:
                return "+";
        }
    }

    private void generateExpressions() {
        try {
            // Generate two random numbers between 1 and 20
            int num1 = (int) (Math.random() * 20) + 1;
            int num2 = (int) (Math.random() * 20) + 1;
            int num3 = (int) (Math.random() * 20) + 1;
            int num4 = (int) (Math.random() * 20) + 1;

            // Generate two expressions by combining the numbers with a random math operator
            String expressionStr1 = num1 + " " +  getRandomOperator() + " " + num2;
            String expressionStr2 = num3 + " " +  getRandomOperator() + " " + num4;

            // Set the expressions in the UI
            expression1.setText(expressionStr1);
            expression2.setText(expressionStr2);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void checkAnswer(int a) {
        try {
            String expressionStr1 = expression1.getText().toString();
            String expressionStr2 = expression2.getText().toString();

            double result1 = evaluateExpression(expressionStr1);
            double result2 = evaluateExpression(expressionStr2);

            if (a == 1 && result1 > result2) {
                // User's answer is correct
                score++;
                scoreView.setText(String.valueOf(score));
            } else if (a == 3 && result2 > result1) {
                score++;
                scoreView.setText(String.valueOf(score));
            } else if (a == 2 && result1 == result2) {
                score++;
                scoreView.setText(String.valueOf(score));
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mathematics);

        //Initialize UI elements
        expression1 = findViewById(R.id.expression1);
        expression2 = findViewById(R.id.expression2);
        scoreView = findViewById(R.id.scoreView);
        firstButton = findViewById(R.id.firstButton);
        equalButton = findViewById(R.id.equalButton);
        secondButton = findViewById(R.id.secondButton);


        // Set click listeners for buttons
            firstButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkAnswer(1);
                }
            });
            secondButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkAnswer(3);
                }
            });
            equalButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkAnswer(2);
                }
            });

            generateExpressions();
        }
    }
