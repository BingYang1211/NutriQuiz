package com.example.nutriquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LearnActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);
    }

    public void onClickBackBtn(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    //To Vitamin Module
    public void onClickVitaminBtn(View view) {
        Intent intent = new Intent(this, VitaminActivity.class);
        startActivity(intent);
    }

    //To Protein Module
    public void onClickProteinBtn(View view) {
        Intent intent = new Intent(this, ProteinActivity.class);
        startActivity(intent);
    }

    //To Carbohydrate Module
    public void onClickCarbohydrateBtn(View view){
        Intent intent = new Intent(this, CarbohydrateActivity.class);
        startActivity(intent);
    }

    //To Fat Module
    public void onClickFatBtn(View view){
        Intent intent = new Intent(this, FatActivity.class);
        startActivity(intent);
    }

    //To Water Module
    public void onClickWaterBtn(View view){
        Intent intent = new Intent(this, WaterActivity.class);
        startActivity(intent);
    }

    //To Mineral Module
    public void onClickMineralBtn(View view){
        Intent intent = new Intent(this, MineralActivity.class);
        startActivity(intent);
    }

    //To Fiber Module
    public void onClickFiberBtn(View view){
        Intent intent = new Intent(this, FiberActivity.class);
        startActivity(intent);
    }

    public void onClickPyramidBtn(View view){
        Intent intent = new Intent(this, FoodPyramidActivity.class);
        startActivity(intent);
    }

}




