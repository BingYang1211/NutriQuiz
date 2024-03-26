package com.example.nutriquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LeaderboardActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LeaderboardAdapter adapter;
    private List<Score> scores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        recyclerView = findViewById(R.id.leaderboardRecyclerView);
        scores = new ArrayList<>();

        // Create an instance of DBHelper and retrieve scores
        DBHelper dbHelper = new DBHelper(this);
        scores = dbHelper.getAllScores();

        // Create and set the adapter for the RecyclerView
        adapter = new LeaderboardAdapter(scores);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void onClickBackBtn(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}






