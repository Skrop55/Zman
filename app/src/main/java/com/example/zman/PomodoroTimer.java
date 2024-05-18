package com.example.zman;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class PomodoroTimer extends AppCompatActivity implements View.OnClickListener {
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pomodoro_timer);

        back = findViewById(R.id.back);
        back.setOnClickListener(this);

        TextView textView = findViewById(R.id.textView9);
        long timerDuration = TimeUnit.MINUTES.toMillis(1);
        long ticksInterval = 10;
        new CountDownTimer(timerDuration, ticksInterval) {
            long millis = 1000;


            @Override
            public void onTick(long l) {
                millis = millis - ticksInterval;
                if(millis == 0)
                    millis = 1000;
                String timerText = String.format(Locale.getDefault(),"%02d:%02d:%03d",
                        TimeUnit.MILLISECONDS.toMinutes(l),
                        TimeUnit.MILLISECONDS.toSeconds(l) - TimeUnit.MILLISECONDS.toMinutes(l),
                        millis); //defining the timer
                textView.setText(timerText);
            }

            @Override
            public void onFinish() {
                textView.setText("finished");
            }
        }.start();
    }

    @Override
    public void onClick(View view) {
        if(view == back) {
            finish();
        }

    }
}