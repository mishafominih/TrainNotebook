package com.example.trainnotebook.train;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.trainnotebook.Database.DatabaseManager;
import com.example.trainnotebook.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ChooseExercise extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_exercise);

        DatabaseManager databaseManager = new DatabaseManager(getApplicationContext());

        ArrayList<HashMap<String, Object>> all_exercises = databaseManager.GetAllExercises();
        List<String> arr_exercises = new ArrayList<>();
        for (HashMap<String, Object> exec: all_exercises) {
            arr_exercises.add(Objects.requireNonNull(exec.get("Name")).toString());
        }

        Spinner exercises = findViewById(R.id.choose_all_exercises);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arr_exercises);
        exercises.setAdapter(adapter);

        String[] numbers = { "1", "2", "3", "4", "5"};
        Spinner spin_count_repeat = findViewById(R.id.choose_count_repeat);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, numbers);
        spin_count_repeat.setAdapter(adapter);

        findViewById(R.id.choose_button).setOnClickListener(v -> {
            String name = get_name_by_spinner(R.id.choose_all_exercises);
            int count_repeat = Integer.parseInt(get_name_by_spinner(R.id.choose_count_repeat));

            Bundle arguments = getIntent().getExtras();
            int train_id = Integer.parseInt(arguments.get("train_id").toString());
            databaseManager.ChooseExercise(train_id, name, count_repeat);
            finish();
        });
    }

    @NonNull
    private String get_name_by_spinner(int view_id) {
        Spinner spinner = findViewById(view_id);
        return String.valueOf(spinner.getSelectedItem());
    }
}