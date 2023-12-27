package com.example.finaltimer;

import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.ToggleButton;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private Chronometer chronometer;
    private long PauseOffSet = 0;
    private boolean isPlaying = false;
    private ToggleButton toggleButton;
    private Button reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chronometer = findViewById(R.id.chronometer);
        toggleButton = findViewById(R.id.Toggle);
        reset = findViewById(R.id.reset);

        toggleButton.setText(null);
        toggleButton.setTextOn(null);
        toggleButton.setTextOff(null);

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b) {
                    chronometer.setBase(SystemClock.elapsedRealtime() - PauseOffSet);
                    chronometer.start();
                    isPlaying = true;
                }else{
                    chronometer.stop();
                    PauseOffSet = SystemClock.elapsedRealtime()- chronometer.getBase();
                    isPlaying = false;
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPlaying){
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    PauseOffSet = 0;
                    chronometer.start();
                    isPlaying = true;

                }
            }
        });
    }
}