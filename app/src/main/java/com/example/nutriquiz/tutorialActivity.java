package com.example.nutriquiz;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

public class tutorialActivity extends AppCompatActivity {
    private static final String TUTORIAL_PREF_KEY = "tutorial_completed";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (isFirstTime()) {
            showTutorialDialog();
        }
    }

    private boolean isFirstTime() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        return !preferences.getBoolean(TUTORIAL_PREF_KEY, false);
    }

    private void showTutorialDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View tutorialView = LayoutInflater.from(this).inflate(R.layout.activity_tutorial, null);
        builder.setView(tutorialView);
        builder.setCancelable(true);

        AlertDialog dialog = builder.create();
        dialog.show();

        // Set any necessary click listeners or callbacks for the tutorial buttons or elements
        Button closeButton = tutorialView.findViewById(R.id.tapForSound);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                markTutorialAsCompleted();
            }
        });
    }

    private void markTutorialAsCompleted() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        preferences.edit().putBoolean(TUTORIAL_PREF_KEY, true).apply();
    }
}