package com.example.logicgames;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;

import java.util.Objects;


public class Profile extends Fragment {
    TextView profileName, profileEmail, profilePassword;
    TextView titleName;
    TextView colorsRec, profileMathRec;
    private FirebaseAuth mAuth;
    FirebaseUser user;
    String nameUser, emailUser, passwordUser;
    private DatabaseReference myRef;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View root = inflater.inflate(R.layout.fragment_profile, container, false);

         profileName = root.findViewById(R.id.profileName);
         profileEmail = root.findViewById(R.id.profileEmail);
         profilePassword = root.findViewById(R.id.profilePassword);
         colorsRec = root.findViewById(R.id.colorsRecord);
         profileMathRec = root.findViewById(R.id.mathematicsRecord);


         showUserData();
        return root;

    }

    public void showUserData() {
        user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = database.getReference("users");
        String userName = user.getDisplayName();


        usersRef.child(Objects.requireNonNull(userName)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
              for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                  String name = userSnapshot.child(userName).child("name").getValue(String.class);
                  String email = userSnapshot.child(userName).child("email").getValue(String.class);
                  String password = userSnapshot.child(userName).child("password").getValue(String.class);
                  //long mathRec = userSnapshot.child("Math Record").getValue(long.class);
                  //profileMathRec.setText("" + mathRec);
                  profileName.setText(name);
                  profileEmail.setText(email);
                  profilePassword.setText(password);
              }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}