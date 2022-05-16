package com.example.trainnotebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.trainnotebook.train.StartTrain;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button_start_train).setOnClickListener(view -> {
            Intent exercises = new Intent(this, StartTrain.class);
            startActivity(exercises);
        });

        findViewById(R.id.button_exercises).setOnClickListener(view -> {
            Intent exercises = new Intent(this, Exercises.class);
            startActivity(exercises);
        });
    }
}