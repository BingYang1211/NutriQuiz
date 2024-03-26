package com.example.nutriquiz;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class MusicService extends Service {
    private static final String TAG = "MusicService";
    public static final String ACTION_SET_VOLUME = "com.example.nutriquiz.SET_VOLUME";
    public static final String EXTRA_VOLUME = "com.example.nutriquiz.EXTRA_VOLUME";

    private static MediaPlayer mediaPlayer;
    private static float volume = 1.0f; // Default volume level

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.bgm);
        mediaPlayer.setLooping(true);
        mediaPlayer.setVolume(volume, volume);
        mediaPlayer.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handleIntent(intent);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static void updateVolume(Context context, float newVolume) {
        Intent intent = new Intent(context, MusicService.class);
        intent.setAction(ACTION_SET_VOLUME);
        intent.putExtra(EXTRA_VOLUME, newVolume);
        context.startService(intent);
    }

    public static float getVolume() {
        return volume;
    }

    public void handleIntent(Intent intent) {
        if (intent == null || intent.getAction() == null) {
            return;
        }

        String action = intent.getAction();

        if (action.equals(ACTION_SET_VOLUME)) {
            float volume = intent.getFloatExtra(EXTRA_VOLUME, 1.0f);
            setVolume(volume);
        }
    }

    private static void setVolume(float volume) {
        MusicService.volume = volume;
        mediaPlayer.setVolume(volume, volume);
    }
}
