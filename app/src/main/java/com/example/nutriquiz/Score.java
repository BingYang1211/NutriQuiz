package com.example.nutriquiz;

public class Score {
    private String email;
    private int score;
    private String displayName;

    public Score(String email, int score) {
        this.email = email;
        this.score = score;
        this.displayName = email; // Use email as the default display name
    }

    public String getEmail() {
        return email;
    }

    public int getScore() {
        return score;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}


