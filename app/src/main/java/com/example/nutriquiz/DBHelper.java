package com.example.nutriquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "userdb";
    private static final String TABLE_NAME = "users";
    private static final String COL_EMAIL = "email";
    private static final String COL_PASSWORD = "password";
    private static final String SCORE_TABLE_NAME = "scores";
    private static final String COL_SCORE = "score";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTableQuery = "CREATE TABLE " + TABLE_NAME + " ("
                + COL_EMAIL + " TEXT PRIMARY KEY,"
                + COL_PASSWORD + " TEXT)";
        db.execSQL(createUserTableQuery);

        String createScoreTableQuery = "CREATE TABLE IF NOT EXISTS " + SCORE_TABLE_NAME + " ("
                + COL_EMAIL + " TEXT,"
                + COL_SCORE + " INTEGER,"
                + " FOREIGN KEY (" + COL_EMAIL + ") REFERENCES " + TABLE_NAME + "(" + COL_EMAIL + "))";
        db.execSQL(createScoreTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SCORE_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertUser(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_EMAIL, email);
        values.put(COL_PASSWORD, password);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public boolean emailExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_EMAIL + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exists;
    }

    public String getPassword(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COL_PASSWORD + " FROM " + TABLE_NAME + " WHERE " + COL_EMAIL + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email});
        String password = null;
        if (cursor.moveToFirst()) {
            int passwordIndex = cursor.getColumnIndex(COL_PASSWORD);
            if (passwordIndex != -1) {
                password = cursor.getString(passwordIndex);
            }
        }
        cursor.close();
        db.close();
        return password;
    }

    public void saveScore(String email, int score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_EMAIL, email);
        values.put(COL_SCORE, score);
        db.insert(SCORE_TABLE_NAME, null, values);
        db.close();
    }
    public List<Score> getAllScores() {
        List<Score> scores = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + SCORE_TABLE_NAME + "." + COL_EMAIL + ", " + COL_SCORE + " FROM " + SCORE_TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            int emailIndex = cursor.getColumnIndex(COL_EMAIL);
            int scoreIndex = cursor.getColumnIndex(COL_SCORE);
            while (!cursor.isAfterLast()) {
                String email = cursor.getString(emailIndex);
                int score = cursor.getInt(scoreIndex);
                scores.add(new Score(email, score));
                cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();
        return scores;
    }

}