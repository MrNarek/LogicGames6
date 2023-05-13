package com.example.logicgames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GuestMode extends AppCompatActivity {
    Button colours, mathematics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_mode);
        colours = findViewById(R.id.colours);
        mathematics = findViewById(R.id.mathematics);



        colours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(GuestMode.this, InfoColors.class);
                startActivity(intent1);
            }
        });

        mathematics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(GuestMode.this, Mathematics.class);
                startActivity(intent1);
            }
        });

    }
}