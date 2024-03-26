package com.example.nutriquiz;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.ViewHolder> {
    private List<Score> scores;

    public LeaderboardAdapter(List<Score> scores) {
        this.scores = scores;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_leaderboard, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Score score = scores.get(position);
        holder.emailTextView.setText(score.getEmail());
        holder.scoreTextView.setText(String.valueOf(score.getScore()));
    }

    @Override
    public int getItemCount() {
        return scores.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView emailTextView;
        public TextView scoreTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            emailTextView = itemView.findViewById(R.id.emailTextView);
            scoreTextView = itemView.findViewById(R.id.scoreTextView);
        }
    }
}

