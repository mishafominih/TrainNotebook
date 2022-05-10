package com.example.trainnotebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.DisplayContext;
import android.os.Bundle;
import android.view.View;

import com.example.trainnotebook.Database.DatabaseManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button_start_train).setOnClickListener(view -> {

        });

        findViewById(R.id.button_exercises).setOnClickListener(view -> {
            Intent exercises = new Intent(this, Exercises.class);
            startActivity(exercises);
        });
    }
}