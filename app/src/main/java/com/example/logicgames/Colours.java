package com.example.logicgames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class Colours extends AppCompatActivity {
    TextView timer, points, clText, clColor, lives;
    Button btnYes, btnNo;
    boolean isTimerTicking = true;
    int pts = 0;
    int lvs = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colours);
        timer = findViewById(R.id.timer);
        clText = findViewById(R.id.clText);
        clColor = findViewById(R.id.clColor);
        btnNo = findViewById(R.id.btnNo);
        btnYes = findViewById(R.id.bynYes);
        points = findViewById(R.id.points);
        lives = findViewById(R.id.lives);


        new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long l) {
                timer.setText("0:" + l / 1000);
                if ((l / 1000) < 10) timer.setText("0:0" + l / 1000);
            }

            @Override
            public void onFinish() {
                Intent intent1 = new Intent(Colours.this, GuestMode.class);
                startActivity(intent1);
                finish();
                isTimerTicking = false;
            }
        }.start();

        String[] clTexts = {"Красный", "Зеленый", "Синий", "Желтый", "Черный", "Фиолетовый"};
        String[] clColors = {"#fc3131", "#99FF99", "#66ccff", "#FFFF00", "#000000", "#8b01f4"};
        int rnd = new Random().nextInt(clTexts.length);
        clText.setText(clTexts[rnd]);
        rnd = new Random().nextInt(clTexts.length);
        clText.setTextColor(Color.parseColor(clColors[rnd]));
        rnd = new Random().nextInt(clTexts.length);
        clColor.setText(clTexts[rnd]);
        rnd = new Random().nextInt(clTexts.length);
        clColor.setTextColor(Color.parseColor(clColors[rnd]));
        Random random = new Random();
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clText.getText().toString().equals(clTexts[0]) && clColor.getCurrentTextColor() == Color.parseColor("#fc3131")) {
                    pts += 1;
                    points.setText(" " + (pts));
                    int rnd = new Random().nextInt(clTexts.length);
                    clText.setText(clTexts[rnd]);
                    rnd = new Random().nextInt(clTexts.length);
                    clText.setTextColor(Color.parseColor(clColors[rnd]));
                    rnd = new Random().nextInt(clTexts.length);
                    clColor.setText(clTexts[rnd]);
                    rnd = new Random().nextInt(clTexts.length);
                    clColor.setTextColor(Color.parseColor(clColors[rnd]));
                } else if (clText.getText().toString().equals(clTexts[1]) && clColor.getCurrentTextColor() == Color.parseColor("#99ff99")) {
                    pts += 1;
                    points.setText(" " + (pts));
                    int rnd = new Random().nextInt(clTexts.length);
                    clText.setText(clTexts[rnd]);
                    rnd = new Random().nextInt(clTexts.length);
                    clText.setTextColor(Color.parseColor(clColors[rnd]));
                    rnd = new Random().nextInt(clTexts.length);
                    clColor.setText(clTexts[rnd]);
                    rnd = new Random().nextInt(clTexts.length);
                    clColor.setTextColor(Color.parseColor(clColors[rnd]));
                } else if (clText.getText().toString().equals(clTexts[2]) && clColor.getCurrentTextColor() == Color.parseColor("#66ccff")) {
                    pts += 1;
                    points.setText(" " + (pts));
                    int rnd = new Random().nextInt(clTexts.length);
                    clText.setText(clTexts[rnd]);
                    rnd = new Random().nextInt(clTexts.length);
                    clText.setTextColor(Color.parseColor(clColors[rnd]));
                    rnd = new Random().nextInt(clTexts.length);
                    clColor.setText(clTexts[rnd]);
                    rnd = new Random().nextInt(clTexts.length);
                    clColor.setTextColor(Color.parseColor(clColors[rnd]));
                } else if (clText.getText().toString().equals(clTexts[3]) && clColor.getCurrentTextColor() == Color.YELLOW) {
                    pts += 1;
                    points.setText(" " + (pts));
                    int rnd = new Random().nextInt(clTexts.length);
                    clText.setText(clTexts[rnd]);
                    rnd = new Random().nextInt(clTexts.length);
                    clText.setTextColor(Color.parseColor(clColors[rnd]));
                    rnd = new Random().nextInt(clTexts.length);
                    clColor.setText(clTexts[rnd]);
                    rnd = new Random().nextInt(clTexts.length);
                    clColor.setTextColor(Color.parseColor(clColors[rnd]));
                } else if (clText.getText().toString().equals(clTexts[4]) && clColor.getCurrentTextColor() == Color.BLACK) {
                    pts += 1;
                    points.setText(" " + (pts));
                    int rnd = new Random().nextInt(clTexts.length);
                    clText.setText(clTexts[rnd]);
                    rnd = new Random().nextInt(clTexts.length);
                    clText.setTextColor(Color.parseColor(clColors[rnd]));
                    rnd = new Random().nextInt(clTexts.length);
                    clColor.setText(clTexts[rnd]);
                    rnd = new Random().nextInt(clTexts.length);
                    clColor.setTextColor(Color.parseColor(clColors[rnd]));
                } else if (clText.getText().toString().equals(clTexts[5]) && clColor.getCurrentTextColor() == Color.parseColor("#8b01f4")) {
                    pts += 1;
                    points.setText(" " + (pts));
                    int rnd = new Random().nextInt(clTexts.length);
                    clText.setText(clTexts[rnd]);
                    rnd = new Random().nextInt(clTexts.length);
                    clText.setTextColor(Color.parseColor(clColors[rnd]));
                    rnd = new Random().nextInt(clTexts.length);
                    clColor.setText(clTexts[rnd]);
                    rnd = new Random().nextInt(clTexts.length);
                    clColor.setTextColor(Color.parseColor(clColors[rnd]));
                } else {
                    if (lvs == 0) {
                        Intent intent1 = new Intent(Colours.this, GuestMode.class);
                        startActivity(intent1);
                        finish();
                    } else {
                        lvs -= 1;
                        lives.setText(" " + lvs);
                        int rnd = new Random().nextInt(clTexts.length);
                        clText.setText(clTexts[rnd]);
                        rnd = new Random().nextInt(clTexts.length);
                        clText.setTextColor(Color.parseColor(clColors[rnd]));
                        rnd = new Random().nextInt(clTexts.length);
                        clColor.setText(clTexts[rnd]);
                        rnd = new Random().nextInt(clTexts.length);
                        clColor.setTextColor(Color.parseColor(clColors[rnd]));
                    }
                }
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clText.getText().toString().equals(clTexts[0]) && clColor.getCurrentTextColor() != Color.parseColor("#fc3131")) {
                    pts += 1;
                    points.setText(" " + (pts));
                    int rnd = new Random().nextInt(clTexts.length);
                    clText.setText(clTexts[rnd]);
                    rnd = new Random().nextInt(clTexts.length);
                    clText.setTextColor(Color.parseColor(clColors[rnd]));
                    rnd = new Random().nextInt(clTexts.length);
                    clColor.setText(clTexts[rnd]);
                    rnd = new Random().nextInt(clTexts.length);
                    clColor.setTextColor(Color.parseColor(clColors[rnd]));
                } else if (clText.getText().toString().equals(clTexts[1]) && clColor.getCurrentTextColor() != Color.parseColor("#99ff99")) {
                    pts += 1;
                    points.setText(" " + (pts));
                    int rnd = new Random().nextInt(clTexts.length);
                    clText.setText(clTexts[rnd]);
                    rnd = new Random().nextInt(clTexts.length);
                    clText.setTextColor(Color.parseColor(clColors[rnd]));
                    rnd = new Random().nextInt(clTexts.length);
                    clColor.setText(clTexts[rnd]);
                    rnd = new Random().nextInt(clTexts.length);
                    clColor.setTextColor(Color.parseColor(clColors[rnd]));
                } else if (clText.getText().toString().equals(clTexts[2]) && clColor.getCurrentTextColor() != Color.parseColor("#66ccff")) {
                    pts += 1;
                    points.setText(" " + (pts));
                    int rnd = new Random().nextInt(clTexts.length);
                    clText.setText(clTexts[rnd]);
                    rnd = new Random().nextInt(clTexts.length);
                    clText.setTextColor(Color.parseColor(clColors[rnd]));
                    rnd = new Random().nextInt(clTexts.length);
                    clColor.setText(clTexts[rnd]);
                    rnd = new Random().nextInt(clTexts.length);
                    clColor.setTextColor(Color.parseColor(clColors[rnd]));
                } else if (clText.getText().toString().equals(clTexts[3]) && clColor.getCurrentTextColor() != Color.YELLOW) {
                    pts += 1;
                    points.setText(" " + (pts));
                    int rnd = new Random().nextInt(clTexts.length);
                    clText.setText(clTexts[rnd]);
                    rnd = new Random().nextInt(clTexts.length);
                    clText.setTextColor(Color.parseColor(clColors[rnd]));
                    rnd = new Random().nextInt(clTexts.length);
                    clColor.setText(clTexts[rnd]);
                    rnd = new Random().nextInt(clTexts.length);
                    clColor.setTextColor(Color.parseColor(clColors[rnd]));
                } else if (clText.getText().toString().equals(clTexts[4]) && clColor.getCurrentTextColor() != Color.BLACK) {
                    pts += 1;
                    points.setText(" " + (pts));
                    int rnd = new Random().nextInt(clTexts.length);
                    clText.setText(clTexts[rnd]);
                    rnd = new Random().nextInt(clTexts.length);
                    clText.setTextColor(Color.parseColor(clColors[rnd]));
                    rnd = new Random().nextInt(clTexts.length);
                    clColor.setText(clTexts[rnd]);
                    rnd = new Random().nextInt(clTexts.length);
                    clColor.setTextColor(Color.parseColor(clColors[rnd]));
                } else if (clText.getText().toString().equals(clTexts[5]) && clColor.getCurrentTextColor() != Color.parseColor("#8b01f4")) {
                    pts += 1;
                    points.setText(" " + (pts));
                    int rnd = new Random().nextInt(clTexts.length);
                    clText.setText(clTexts[rnd]);
                    rnd = new Random().nextInt(clTexts.length);
                    clText.setTextColor(Color.parseColor(clColors[rnd]));
                    rnd = new Random().nextInt(clTexts.length);
                    clColor.setText(clTexts[rnd]);
                    rnd = new Random().nextInt(clTexts.length);
                    clColor.setTextColor(Color.parseColor(clColors[rnd]));
                } else {
                    if (lvs == 0) {
                        Intent intent1 = new Intent(Colours.this, GuestMode.class);
                        startActivity(intent1);
                        finish();
                    } else {
                        lvs -= 1;
                        lives.setText(" " + lvs);
                        int rnd = new Random().nextInt(clTexts.length);
                        clText.setText(clTexts[rnd]);
                        rnd = new Random().nextInt(clTexts.length);
                        clText.setTextColor(Color.parseColor(clColors[rnd]));
                        rnd = new Random().nextInt(clTexts.length);
                        clColor.setText(clTexts[rnd]);
                        rnd = new Random().nextInt(clTexts.length);
                        clColor.setTextColor(Color.parseColor(clColors[rnd]));
                    }
                }
            }
        });
    }
}
