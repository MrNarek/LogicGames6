package com.example.logicgames;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Login extends AppCompatActivity {

    public static EditText loginName, loginPassword;
    Button loginButton;
    TextView signupRedirectText;
    boolean passwordVisible;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginName = findViewById(R.id.login_username);
        loginPassword = findViewById(R.id.login_password);
        signupRedirectText = findViewById(R.id.loginRedirectText);
        loginButton = findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateEmail() | !validatePassword()) {

                } else {
                    checkUser();
                }
            }
        });
        signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });



        loginPassword.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right = 2;
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= loginPassword.getRight() - loginPassword.getCompoundDrawables()[Right].getBounds().width()) {
                        int selection = loginPassword.getSelectionEnd();
                        if (passwordVisible) {
                            loginPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.visoff, 0);
                            loginPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible = false;
                        } else {
                            loginPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.vison, 0);
                            loginPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible = true;
                        }
                        loginPassword.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });
    }



    public Boolean validatePassword() {
        String val = loginPassword.getText().toString();
        if (val.isEmpty()) {
            loginPassword.setError("Password cannot be empty");
            return false;
        } else {
            loginPassword.setError(null);
            return true;
        }
    }


    private boolean validateName() {
        String emailInput = loginName.getText().toString();

        if (emailInput.isEmpty()) {
            loginName.setError("Field can't be empty");
            return false;
        } else {
            loginName.setError(null);
            return true;
        }
    }

    public void checkUser() {

        String userName = loginName.getText().toString().trim();
        String userPassword = loginPassword.getText().toString().trim();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");


            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (Objects.equals(snapshot.child("User").child("email").getValue(String.class), userName)) {
                        loginName.setError(null);
                        String passwordFromDB = snapshot.child("User").child("password").getValue(String.class);

                        if (Objects.equals(passwordFromDB, userPassword)) {

                            loginName.setError(null);
                            Intent intent = new Intent(Login.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            loginPassword.setError("Invalid Credentials");
                            loginPassword.requestFocus();
                        }
                    } else {
                        loginName.setError("User does not exist");
                        loginName.requestFocus();
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

    }
    private boolean validateEmail() {
        String emailInput = loginName.getText().toString();

        if (emailInput.isEmpty()) {
            loginName.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            loginName.setError("Please enter a valid email address");
            return false;
        } else {
            loginName.setError(null);
            return true;
        }
    }
}