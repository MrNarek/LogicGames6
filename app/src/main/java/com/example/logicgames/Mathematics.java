package com.example.logicgames;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Mathematics extends AppCompatActivity {
    private TextView expression1, expression2, scoreView;
    private Button firstButton, equalButton, secondButton;
    private int score = 0;

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
        if (firstButton != null) {
            firstButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkAnswer(false);
                }
            });
        } else if (secondButton!= null) {
            secondButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkAnswer(false);
                }
            });
        } else if (equalButton != null) {
            equalButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkAnswer(false);
                }
            });
        }



        // Generate initial expressions
        generateExpressions();
    }

    private void generateExpressions() {
        // Generate two random numbers between 1 and 20
        int num1 = (int) (Math.random() * 20) + 1;
        int num2 = (int) (Math.random() * 20) + 1;
        int num3 = (int) (Math.random() * 20) + 1;
        int num4 = (int) (Math.random() * 20) + 1;

        // Generate two expressions by combining the numbers with a random math operator
        String expressionStr1 = num1 + getRandomOperator() + num2;
        String expressionStr2 = num3 + getRandomOperator() + num4;

        // Set the expressions in the UI
        expression1.setText(expressionStr1);
        expression2.setText(expressionStr2);
    }

    private void checkAnswer(boolean isFirstGreater) {
        String expressionStr1 = expression1.getText().toString();
        String expressionStr2 = expression2.getText().toString();

        int result1 = evaluateExpression(expressionStr1);
        int result2 = evaluateExpression(expressionStr2);

        if ((isFirstGreater && result1 > result2) || (!isFirstGreater && result2 > result1)) {
            // User's answer is correct
            score++;
            scoreView.setText("Score: " + score);
        }
        generateExpressions();
    }

    // Method to evaluate a math expression and return the result
    private int evaluateExpression(String expressionStr) {
        int result = 0;
        String[] expressionArr = expressionStr.split("\\s*\\+\\s*|\\s*\\-\\s*|\\s*\\*\\s*|\\s*/\\s*");
        int num1 = Integer.parseInt(expressionArr[0]);
        int num2 = Integer.parseInt(expressionArr[2]);
        String operator = expressionArr[1];

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
                result = num1 / num2;
                break;
        }

        return result;
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
}