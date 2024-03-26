package com.example.nutriquiz;

import java.util.List;

public class QuizQuestion {
    private String question;
    private List<String> answerOptions;
    private int correctAnswerIndex;

    public QuizQuestion(String question, List<String> answerOptions, int correctAnswerIndex) {
        this.question = question;
        this.answerOptions = answerOptions;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswerOptions() {
        return answerOptions;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}

