package com.example.logicgames;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class Register extends AppCompatActivity {
//    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
//            "^" +
//                    "(?=.*[0-9])" +
//                    "(?=.*[a-z])" +
//                    "(?=.*[A-Z])" +
//                    "?=\\S+$" +
//                    ".{6,}" + "$");


    EditText signupName, signupEmail, confirmPassword, signupPassword;
    TextView loginRedirectText;
    Button signupButton;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        signupName = findViewById(R.id.signup_name);
        signupEmail = findViewById(R.id.signup_email);
        signupPassword = findViewById(R.id.signup_password);
        signupButton = findViewById(R.id.signup_button);
        loginRedirectText = findViewById(R.id.loginRedirectText);
        confirmPassword = findViewById(R.id.signup_confirm_password);


        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateEmail();

                if (validateEmail()) {
                    database = FirebaseDatabase.getInstance();
                    reference = database.getReference("users");

                    String name = signupName.getText().toString();
                    String email = signupEmail.getText().toString();
                    String password = signupPassword.getText().toString();

                    HelperClass helperClass = new HelperClass(name, email, password);
                    reference.child(name).setValue(helperClass);

                    Toast.makeText(Register.this, "You have signup successfully!", Toast.LENGTH_SHORT);
                }
            }
        });
    }

    private boolean validateEmail() {
        String emailInput = signupEmail.getText().toString();

        if (emailInput.isEmpty()) {
            signupEmail.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            signupEmail.setError("Please enter a valid email address");
            return false;
        } else {
            signupEmail.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String name = signupName.getText().toString(),
                password = signupPassword.getText().toString();

        if (password.length() <= 8) {
            signupPassword.setError("Password length must be at least 8");
            return false;
        } else if (!password.matches("(.*[A-Z].*)")) {
            signupPassword.setError("Password must contain at least one uppercase character");
            return false;
        } else if (!password.matches("(.*[0-9].*)")) {
            signupPassword.setError("Password must contain at least one digit");
            return false;
        }
        else {
            return true;
        }
    }
}
