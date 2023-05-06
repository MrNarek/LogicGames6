package com.example.logicgames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GuestMode extends AppCompatActivity {
    Button colours, infoColors;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_mode);
        colours = findViewById(R.id.colours);
        infoColors = findViewById(R.id.infoColors);


        colours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(GuestMode.this, Colours.class);
                startActivity(intent1);
            }
        });

        infoColors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(GuestMode.this, InfoColors.class);
                startActivity(intent1);
            }
        });
    }
}