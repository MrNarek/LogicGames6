package com.example.logicgames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class InfoColors extends AppCompatActivity {
    TextView clColor, clText;
    Button startGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_colors);

        clText = findViewById(R.id.clText);
        clColor = findViewById(R.id.clColor);
        startGame = findViewById(R.id.startGame);

        clText.setText("Красный");
        clText.setTextColor(Color.parseColor("#8b01f4"));
        clColor.setText("Зеленый");
        clColor.setTextColor(Color.parseColor("#fc3131"));

        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(InfoColors.this, Colours.class);
                startActivity(intent1);
            }
        });
    }
}