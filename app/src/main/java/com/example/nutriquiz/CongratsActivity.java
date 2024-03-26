package com.example.nutriquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CongratsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congrats);

        TextView scoreTxt = findViewById(R.id.scoreTxt);

        // Retrieve the score from the intent extras
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int score = extras.getInt("SCORE_EXTRA", 0);
            scoreTxt.setText("Your score: " + score);
        }
    }

    public void onClickBackBtn(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onClickLeaderboardBtn(View view) {
        Intent intent = new Intent(this, LeaderboardActivity.class);
        startActivity(intent);
    }
}
