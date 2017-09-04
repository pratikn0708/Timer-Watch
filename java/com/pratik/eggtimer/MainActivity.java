package com.pratik.eggtimer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView iv;
    TextView tv;
    Button b1;
    SeekBar timerseekBar;
    boolean counter = false;
    CountDownTimer countDownTimer;

    public void UpdateTimer(Integer secondsleft) {
        int min = (int) secondsleft / 60;
        int sec = secondsleft - min * 60;
        String second = Integer.toString(sec);
        if (sec <= 9) {
            second = "0" + second;
        }
        tv.setText(Integer.toString(min) + ":" + second);
    }

    public void resetTimer() {
        tv.setText("0:30");
        timerseekBar.setProgress(30);
        countDownTimer.cancel();
        b1.setText("Go!!");
        timerseekBar.setEnabled(true);
        counter = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (Button) findViewById(R.id.button);
        tv = (TextView) findViewById(R.id.textView);
        iv = (ImageView) findViewById(R.id.imageView);
        timerseekBar = (SeekBar) findViewById(R.id.seekBar);
        timerseekBar.setMax(300);
        timerseekBar.setProgress(30);
        b1.setOnClickListener(this);
        timerseekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                UpdateTimer(i);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        if (counter == false) {

            counter = true;
            timerseekBar.setEnabled(false);
            b1.setText("Stop");
            countDownTimer = new CountDownTimer(timerseekBar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long l) {
                    UpdateTimer((int) l / 1000);
                }

                @Override
                public void onFinish() {
                    resetTimer();
                    MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.air);
                    mp.start();

                }
            }.start();
        } else {
            resetTimer();
        }
    }
}
