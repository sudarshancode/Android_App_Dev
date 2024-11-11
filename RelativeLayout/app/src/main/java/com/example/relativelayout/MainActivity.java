package com.example.relativelayout;

import static androidx.core.util.TimeUtils.formatDuration;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    ImageButton playButton,pauseButton,stopButton;
    MediaPlayer mediaPlayer;
    SeekBar soundBar;
    TextView timeduration;
    int time;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        setStatusBarColor(R.color.red);

        playButton=(ImageButton) findViewById(R.id.playBtnId);
        pauseButton=(ImageButton) findViewById(R.id.pauseBtnId);
        stopButton=(ImageButton) findViewById(R.id.stopBtnId);
        soundBar=(SeekBar)findViewById(R.id.soundBarId);
        mediaPlayer=MediaPlayer.create(MainActivity.this,R.raw.omahiomahi);
        timeduration=(TextView) findViewById(R.id.timeId);

        time=mediaPlayer.getDuration();

        timeduration.setText("Duration: " + formatDuration(time));

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mediaPlayer.isPlaying()){
                    mediaPlayer.start();
                }
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                }else{
                    mediaPlayer.start();
                }
            }
        });
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
            }
        });

        soundBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float volume=progress/100f;
                mediaPlayer.setVolume(volume,volume);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    private void setStatusBarColor(int color) {
        Window window=getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this,color));
    }
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
    private String formatDuration(int duration) {
        int minutes = (time / 1000) / 60;
        int seconds = (time/ 1000) % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}