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
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
            "^" +
                    "(?=.*[0-9])" +
                    "(?=.*[a-z])" +
                    "(?=.*[A-Z])" +
                    "?=\\S+$" +
                    ".{6,}" + "$");


    EditText signupName, signupEmail, signupUserName, signupPassword;
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
        signupUserName = findViewById(R.id.signup_username);
        signupPassword = findViewById(R.id.signup_password);
        signupButton = findViewById(R.id.signup_button);
        loginRedirectText = findViewById(R.id.loginRedirectText);


        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");

                String name = signupName.getText().toString();
                String email = signupEmail.getText().toString();
                String username = signupUserName.getText().toString();
                String password = signupPassword.getText().toString();

                HelperClass helperClass = new HelperClass(name, email, username, password);
                reference.child(name).setValue(helperClass);

                Toast.makeText(Register.this, "You have signup successfully!", Toast.LENGTH_SHORT);
            }
        });
    }

    private boolean validateEmail() {
        String emailIput = signupEmail.getText().toString().trim();

        if (emailIput.isEmpty()) {
            signupEmail.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailIput).matches()) {
            signupEmail.setError("Please enter a valid email address");
            return false;
        } else {
            signupEmail.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String passwordInput = signupEmail.getText().toString().trim();

        if (passwordInput.isEmpty()) {
            signupPassword.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            signupPassword.setError("Password is too weak");
            return false;
        } else {
            signupPassword.setError(null);
            return true;
        }

    }
}