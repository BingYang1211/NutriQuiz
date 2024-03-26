package com.example.nutriquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize the DBHelper object
        dbHelper = new DBHelper(this);

        Button loginButton = findViewById(R.id.btnLogin);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLoginBtn();
            }
        });
    }

    public void onClickLoginBtn() {
        EditText emailEditText = findViewById(R.id.emailTxt);
        EditText passwordEditText = findViewById(R.id.passwordTxt);
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Check if the email and password match the values stored in the database
        if (validateCredentials(email, password)) {
            // Login successful, proceed to the next activity
            Intent intent = new Intent(LoginActivity.this, QuizActivity.class);
            startActivity(intent);
            finish();
        } else {
            // Invalid credentials, display an error message
            // Show a Toast or update a TextView to indicate the error
            Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validateCredentials(String email, String password) {
        String storedPassword = dbHelper.getPassword(email);
        return storedPassword != null && storedPassword.equals(password);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbHelper.close();
    }

    public void onClickBackBtn(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onClickRegisterBtn(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
