package com.example.nutriquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class FiberActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private float originalVolume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiber);

        mediaPlayer = MediaPlayer.create(this, R.raw.fiber_function);
        originalVolume = 1.0f; // Store the original volume as 1.0 (maximum volume)
    }

    public void onClickBackBtn(View view) {
        Intent intent = new Intent(this, LearnActivity.class);
        startActivity(intent);
    }

    public void onClickNextBtn(View view) {
        Intent intent = new Intent(this, FiberActivity2.class);
        startActivity(intent);
    }

    public void onClickFiberFunction(View view) {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            mediaPlayer.seekTo(0);
        } else {
            // Increase volume by 30 percent
            mediaPlayer.setVolume(originalVolume * 10.0f, originalVolume * 10.0f);

            mediaPlayer.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Stop audio playback and release the MediaPlayer
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

}