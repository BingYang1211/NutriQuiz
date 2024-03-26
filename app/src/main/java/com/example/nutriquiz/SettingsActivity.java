package com.example.nutriquiz;
import android.os.Bundle;

import android.view.View;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {
    private SeekBar volumeSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        volumeSeekBar = findViewById(R.id.volumeSeekBar);
        volumeSeekBar.setProgress((int) (MusicService.getVolume() * 100));

        volumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float volume = progress / 100f;
                MusicService.updateVolume(SettingsActivity.this, volume);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    public void onClickBackBtn(View view) {
        finish();
    }
}

