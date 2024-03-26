package com.example.nutriquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize the DBHelper object
        dbHelper = new DBHelper(this);

        Button registerButton = findViewById(R.id.btnRegister);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRegisterBtn();
            }
        });
    }

    public void onClickRegisterBtn() {
        EditText emailEditText = findViewById(R.id.emailRegisterTxt);
        EditText passwordEditText = findViewById(R.id.passwordRegisterTxt);
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Validate input fields
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        } else {
            // Check if the email already exists in the database
            if (dbHelper.emailExists(email)) {
                Toast.makeText(RegisterActivity.this, "Email already exists", Toast.LENGTH_SHORT).show();
            } else {
                // Insert the new user into the database
                dbHelper.insertUser(email, password);
                Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();

                // Save an initial score for the user
                dbHelper.saveScore(email, 0); // Assuming initial score is 0

                // Clear the input fields
                emailEditText.setText("");
                passwordEditText.setText("");

                // Navigate back to LoginActivity
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbHelper.close();
    }

    public void onClickBackBtn(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
