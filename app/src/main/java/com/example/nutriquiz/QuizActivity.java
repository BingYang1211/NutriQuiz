package com.example.nutriquiz;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {
    private String[] questions = {
            "Question 1: Does protein help with growth?",
            "Question 2: Does mineral keep the digestive system healthy?",
            "Question 3: Does fiber help to build strong bones and teeth?",
            "Question 4: Does vitamin help the body heal wounds?",
            "Question 5: Does fat help the body absorb vitamins?",
            "Question 6: Does carbohydrate help the body gain energy?",
            "Question 7: Does water help the body maintain temperature?"
    };

    private String[] correctAnswers = {
            "Yes",
            "No",
            "No",
            "Yes",
            "Yes",
            "Yes",
            "Yes"
    };

    private int questionIndex = 0; // Index of the current question
    private int score = 0; // User's score
    private String email; // User's email

    private static final long QUIZ_TIME_MS = 2 * 60 * 1000; // Quiz duration in milliseconds
    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Retrieve the email from the intent extras
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            email = extras.getString("EMAIL_EXTRA");
        }

        TextView questionTxt = findViewById(R.id.questionTxt);
        Button answerOption1Btn = findViewById(R.id.answerOption1Btn);
        Button answerOption2Btn = findViewById(R.id.answerOption2Btn);

        // Set the first question in the TextView
        questionTxt.setText(questions[questionIndex]);

        answerOption1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAnswerOptionSelected("Yes");
            }
        });

        answerOption2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAnswerOptionSelected("No");
            }
        });

        // Start the timer
        startQuizTimer();
    }

    private void onAnswerOptionSelected(String selectedAnswer) {
        // Check if the selected answer is correct
        if (selectedAnswer.equals(correctAnswers[questionIndex])) {
            // User's answer is correct
            Toast.makeText(this, "Correct answer!", Toast.LENGTH_SHORT).show();

            // Increment the score
            score++;
        } else {
            // User's answer is incorrect
            Toast.makeText(this, "Incorrect answer!", Toast.LENGTH_SHORT).show();
        }

        // Move to the next question
        questionIndex++;

        // Check if all questions have been answered
        if (questionIndex < questions.length) {
            // Update the TextView with the next question
            TextView questionTxt = findViewById(R.id.questionTxt);
            questionTxt.setText(questions[questionIndex]);
        } else {
            // All questions have been answered
            finishQuiz();
        }
    }

    private void startQuizTimer() {
        timer = new CountDownTimer(QUIZ_TIME_MS, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Update the timer display
                TextView timerTxt = findViewById(R.id.timerTxt);
                timerTxt.setText("Time Remaining: " + millisUntilFinished / 1000 + " seconds");
            }

            @Override
            public void onFinish() {
                // Quiz timer finished
                finishQuiz();
            }
        };

        timer.start();
    }

    private void finishQuiz() {
        // Stop the timer
        if (timer != null) {
            timer.cancel();
        }

        // Save the score to the database
        DBHelper dbHelper = new DBHelper(this);
        dbHelper.saveScore(email, score);

        // Start the ResultActivity and pass the user's score as an extra
        Intent intent = new Intent(QuizActivity.this, CongratsActivity.class);
        intent.putExtra("SCORE_EXTRA", score);
        startActivity(intent);

        // Finish the activity
        finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Stop the timer to prevent memory leaks
        if (timer != null) {
            timer.cancel();
        }
    }
}
