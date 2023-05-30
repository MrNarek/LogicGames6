package com.example.logicgames;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {
    EditText signupName, signupEmail, confirmPassword, signupPassword;
    TextView loginRedirectText;
    Button signupButton;
    FirebaseDatabase database;
    public static String name;
    DatabaseReference reference;
    boolean passwordVisible;
    @SuppressLint("ClickableViewAccessibility")
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
                validatePassword();
                if (validateEmail() && validatePassword()) {
                    database = FirebaseDatabase.getInstance();
                    reference = database.getReference("users");
                    reference.setValue("User");
                    name = signupName.getText().toString();
                    String email = signupEmail.getText().toString();
                    String password = signupPassword.getText().toString();

                    final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener((task) -> {
                        if (task.isSuccessful()) {
                            if (firebaseAuth.getCurrentUser().isEmailVerified()) {

                            } else {
                                firebaseAuth.getCurrentUser().sendEmailVerification().
                                        addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(Register.this, "User registered successfully, please verify your email", Toast.LENGTH_SHORT).show();
                                                    signupEmail.setText("");
                                                    signupPassword.setText("");
                                                } else {
                                                    Toast.makeText(Register.this, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                            }
                        } else
                            Toast.makeText(Register.this, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                    });
                    HelperClass helperClass = new HelperClass(name, email, password);
                    reference.child(name).setValue(helperClass);

                    Toast.makeText(Register.this, "You have signup successfully!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signupPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right = 2;
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= signupPassword.getRight() - signupPassword.getCompoundDrawables()[Right].getBounds().width()) {
                        int selection = signupPassword.getSelectionEnd();
                        if (passwordVisible) {
                            signupPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.visoff, 0);
                            signupPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible = false;
                        } else {
                            signupPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.vison, 0);
                            signupPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible = true;
                        }
                        signupPassword.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });

        confirmPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right = 2;
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= confirmPassword.getRight() - confirmPassword.getCompoundDrawables()[Right].getBounds().width()) {
                        int selection = confirmPassword.getSelectionEnd();
                        if (passwordVisible) {
                            confirmPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.visoff, 0);
                            confirmPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible = false;
                        } else {
                            confirmPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.vison, 0);
                            confirmPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible = true;
                        }
                        confirmPassword.setSelection(selection);
                        return true;
                    }
                }
                return false;
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
                password = signupPassword.getText().toString(),
                confirm = confirmPassword.getText().toString();

        if (password.length() <= 8) {
            signupPassword.setError("Password length must be at least 8");
            return false;
        } else if (!password.matches("(.*[A-Z].*)")) {
            signupPassword.setError("Password must contain at least one uppercase character");
            return false;
        } else if (!password.matches("(.*[0-9].*)")) {
            signupPassword.setError("Password must contain at least one digit");
            return false;
        } else if (!password.equals(confirm)) {
            confirmPassword.setError(getString(R.string.passwordDoesntMatch));
            return false;
        } else if (name.isEmpty()) {
            signupName.setError("Name cannot be empty");
            return false;
        } else {
            return true;
        }
    }
}
