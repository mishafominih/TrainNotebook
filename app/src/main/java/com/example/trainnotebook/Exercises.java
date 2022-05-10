package com.example.trainnotebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Exercises extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

        findViewById(R.id.to_add_exercise_button).setOnClickListener(view -> {
            Intent exercises = new Intent(this, NewExercise.class);
            startActivity(exercises);
        });
    }
}