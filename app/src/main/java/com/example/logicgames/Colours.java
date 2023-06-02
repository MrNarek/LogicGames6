package com.example.logicgames;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class Colours extends AppCompatActivity {
    TextView timer, points, clText, clColor, lives;
    Button btnYes, btnNo;
    boolean isTimerTicking = true;
    int pts = 0;
    int lvs = 3;

    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    public void generateColor(TextView clText, TextView clColor, String[] clTexts, String[] clColors) {
        btnYes.setBackgroundColor(Color.parseColor("#59515E"));
        Handler handler = new Handler();
        Runnable x = new Runnable() {
            @Override
            public void run() {
                int rnd = new Random().nextInt(clTexts.length);
                clText.setText(clTexts[rnd]);
                rnd = new Random().nextInt(clTexts.length);
                clText.setTextColor(Color.parseColor(clColors[rnd]));
                rnd = new Random().nextInt(clTexts.length);
                clColor.setText(clTexts[rnd]);
                rnd = new Random().nextInt(clTexts.length);
                clColor.setTextColor(Color.parseColor(clColors[rnd]));
            }
        };
        handler.postDelayed(x, 70);
    }

        public void generateColorNo(TextView clText, TextView clColor, String[] clTexts, String[] clColors) {
            btnNo.setBackgroundColor(Color.parseColor("#59575E"));
            Handler handler = new Handler();
            Runnable x = new Runnable() {
                @Override
                public void run() {
                    int rnd = new Random().nextInt(clTexts.length);
                    clText.setText(clTexts[rnd]);
                    rnd = new Random().nextInt(clTexts.length);
                    clText.setTextColor(Color.parseColor(clColors[rnd]));
                    rnd = new Random().nextInt(clTexts.length);
                    clColor.setText(clTexts[rnd]);
                    rnd = new Random().nextInt(clTexts.length);
                    clColor.setTextColor(Color.parseColor(clColors[rnd]));
                }
            };
            handler.postDelayed(x, 100);
    }
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


        CountDownTimer start = new CountDownTimer(30000, 1000) {

            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long l) {
                timer.setText("0:" + l / 1000);
                if ((l / 1000) < 10) timer.setText("0:0" + l / 1000);
            }

            @Override
            public void onFinish() {
                Intent intent1 = new Intent(Colours.this, GuestMode.class);
                startActivity(intent1);
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference usersRef = database.getReference("users");
                DatabaseReference myRef = database.getReference("users").child("User").child("coloursRec");
                usersRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                            long mathRec = userSnapshot.child("coloursRec").getValue(long.class);
                            if (pts > mathRec) {
                                myRef.setValue(pts);
                            }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                Toast.makeText(Colours.this, "Результат: " + pts, Toast.LENGTH_LONG).show();
                finish();
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
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if (clText.getText().toString().equals(clTexts[0]) && clColor.getCurrentTextColor() == Color.parseColor("#fc3131")) {
                    pts += 1;
                    points.setText(" " + (pts));
                    btnYes.setBackgroundColor(Color.GREEN);
                    Handler handler = new Handler();
                    Runnable x = new Runnable() {
                        @Override
                        public void run() {
                            generateColor(clText, clColor, clTexts, clColors);
                        }
                    };
                    handler.postDelayed(x, 100);
                } else if (clText.getText().toString().equals(clTexts[1]) && clColor.getCurrentTextColor() == Color.parseColor("#99ff99")) {
                    pts += 1;
                    points.setText(" " + (pts));
                    btnYes.setBackgroundColor(Color.GREEN);
                    Handler handler = new Handler();
                    Runnable x = new Runnable() {
                        @Override
                        public void run() {
                            generateColor(clText, clColor, clTexts, clColors);
                        }
                    };
                    handler.postDelayed(x, 100);
                } else if (clText.getText().toString().equals(clTexts[2]) && clColor.getCurrentTextColor() == Color.parseColor("#66ccff")) {
                    pts += 1;
                    points.setText(" " + (pts));
                    btnYes.setBackgroundColor(Color.GREEN);
                    Handler handler = new Handler();
                    Runnable x = new Runnable() {
                        @Override
                        public void run() {
                            generateColor(clText, clColor, clTexts, clColors);
                        }
                    };
                    handler.postDelayed(x, 100);
                } else if (clText.getText().toString().equals(clTexts[3]) && clColor.getCurrentTextColor() == Color.YELLOW) {
                    pts += 1;
                    points.setText(" " + (pts));
                    btnYes.setBackgroundColor(Color.GREEN);
                    Handler handler = new Handler();
                    Runnable x = new Runnable() {
                        @Override
                        public void run() {
                            generateColor(clText, clColor, clTexts, clColors);
                        }
                    };
                    handler.postDelayed(x, 100);
                } else if (clText.getText().toString().equals(clTexts[4]) && clColor.getCurrentTextColor() == Color.BLACK) {
                    pts += 1;
                    points.setText(" " + (pts));
                    btnYes.setBackgroundColor(Color.GREEN);
                    Handler handler = new Handler();
                    Runnable x = new Runnable() {
                        @Override
                        public void run() {
                            generateColor(clText, clColor, clTexts, clColors);
                        }
                    };
                    handler.postDelayed(x, 100);
                } else if (clText.getText().toString().equals(clTexts[5]) && clColor.getCurrentTextColor() == Color.parseColor("#8b01f4")) {
                    pts += 1;
                    points.setText(" " + (pts));
                    btnYes.setBackgroundColor(Color.GREEN);
                    Handler handler = new Handler();
                    Runnable x = new Runnable() {
                        @Override
                        public void run() {
                            generateColor(clText, clColor, clTexts, clColors);
                        }
                    };
                    handler.postDelayed(x, 100);
                } else {
                    if (lvs == 0) {
                        Intent intent1 = new Intent(Colours.this, GuestMode.class);
                        startActivity(intent1);
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference usersRef = database.getReference("users");
                        DatabaseReference myRef = database.getReference("users").child("User").child("coloursRec");
                        usersRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                                    long mathRec = userSnapshot.child("coloursRec").getValue(long.class);
                                    if (pts > mathRec) {
                                        myRef.setValue(pts);
                                    }

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        Toast.makeText(Colours.this, "Результат: " + pts, Toast.LENGTH_LONG).show();
                        finish();
                    } else {
                        lvs -= 1;
                        lives.setText(" " + lvs);
                        btnYes.setBackgroundColor(Color.RED);
                        Handler handler = new Handler();
                        Runnable x = new Runnable() {
                            @Override
                            public void run() {
                                generateColor(clText, clColor, clTexts, clColors);
                            }
                        };
                        handler.postDelayed(x, 100);
                    }
                }
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if (clText.getText().toString().equals(clTexts[0]) && clColor.getCurrentTextColor() != Color.parseColor("#fc3131")) {
                    pts += 1;
                    points.setText(String.format(" %d", pts));
                    btnNo.setBackgroundColor(Color.GREEN);
                    Handler handler = new Handler();
                    Runnable x = new Runnable() {
                        @Override
                        public void run() {
                            generateColorNo(clText, clColor, clTexts, clColors);
                        }
                    };
                    handler.postDelayed(x, 100);
                } else if (clText.getText().toString().equals(clTexts[1]) && clColor.getCurrentTextColor() != Color.parseColor("#99ff99")) {
                    pts += 1;
                    points.setText(" " + (pts));
                    btnNo.setBackgroundColor(Color.GREEN);
                    Handler handler = new Handler();
                    Runnable x = new Runnable() {
                        @Override
                        public void run() {
                            generateColorNo(clText, clColor, clTexts, clColors);
                        }
                    };
                    handler.postDelayed(x, 100);
                } else if (clText.getText().toString().equals(clTexts[2]) && clColor.getCurrentTextColor() != Color.parseColor("#66ccff")) {
                    pts += 1;
                    points.setText(" " + (pts));
                    btnNo.setBackgroundColor(Color.GREEN);
                    Handler handler = new Handler();
                    Runnable x = new Runnable() {
                        @Override
                        public void run() {
                            generateColorNo(clText, clColor, clTexts, clColors);
                        }
                    };
                    handler.postDelayed(x, 100);
                } else if (clText.getText().toString().equals(clTexts[3]) && clColor.getCurrentTextColor() != Color.YELLOW) {
                    pts += 1;
                    points.setText(" " + (pts));
                    btnNo.setBackgroundColor(Color.GREEN);
                    Handler handler = new Handler();
                    Runnable x = new Runnable() {
                        @Override
                        public void run() {
                            generateColorNo(clText, clColor, clTexts, clColors);
                        }
                    };
                    handler.postDelayed(x, 100);
                } else if (clText.getText().toString().equals(clTexts[4]) && clColor.getCurrentTextColor() != Color.BLACK) {
                    pts += 1;
                    points.setText(" " + (pts));
                    btnNo.setBackgroundColor(Color.GREEN);
                    Handler handler = new Handler();
                    Runnable x = new Runnable() {
                        @Override
                        public void run() {
                            generateColorNo(clText, clColor, clTexts, clColors);
                        }
                    };
                    handler.postDelayed(x, 100);
                } else if (clText.getText().toString().equals(clTexts[5]) && clColor.getCurrentTextColor() != Color.parseColor("#8b01f4")) {
                    pts += 1;
                    points.setText(" " + (pts));
                    btnNo.setBackgroundColor(Color.GREEN);
                    Handler handler = new Handler();
                    Runnable x = new Runnable() {
                        @Override
                        public void run() {
                            generateColorNo(clText, clColor, clTexts, clColors);
                        }
                    };
                    handler.postDelayed(x, 100);
                } else {
                    if (lvs == 0) {
                        Intent intent1 = new Intent(Colours.this, GuestMode.class);
                        startActivity(intent1);
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference usersRef = database.getReference("users");
                        DatabaseReference myRef = database.getReference("users").child("User").child("coloursRec");
                        usersRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                                    long mathRec = userSnapshot.child("coloursRec").getValue(long.class);
                                    if (pts > mathRec) {
                                        myRef.setValue(pts);
                                    }

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        Toast.makeText(Colours.this, "Результат: " + pts, Toast.LENGTH_LONG).show();
                        finish();
                    } else {
                        lvs -= 1;
                        lives.setText(" " + lvs);
                        btnNo.setBackgroundColor(Color.RED);
                        Handler handler = new Handler();
                        Runnable x = new Runnable() {
                            @Override
                            public void run() {
                                generateColorNo(clText, clColor, clTexts, clColors);
                            }
                        };
                        handler.postDelayed(x, 100);
                    }
                }
            }
        });
    }
}
