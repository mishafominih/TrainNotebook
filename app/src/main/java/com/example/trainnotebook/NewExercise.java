package com.example.trainnotebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.trainnotebook.Database.DatabaseManager;

public class NewExercise extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_exercise);

        DatabaseManager db_manager = new DatabaseManager(getApplicationContext());

        // Заполним единицами измерения
        String[] units = db_manager.GetUnits();
        Spinner spin = (Spinner)findViewById(R.id.units);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, units);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);

        // Заполним способами измерения
        String[] times = db_manager.GetTimes();
        spin = (Spinner)findViewById(R.id.time);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, times);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);

        findViewById(R.id.add_exercise_button).setOnClickListener(view -> {
            String name = get_name_by_edit_text(R.id.exercise_name);
            String description = get_name_by_edit_text(R.id.exercise_description);
            String unit = get_name_by_spinner(R.id.units);
            String time = get_name_by_spinner(R.id.time);
            Exercise ex = new Exercise(name, description, unit, time);
            if(ex.is_correct()){
                db_manager.AddExercise(ex);
            }
        });
    }

    @NonNull
    private String get_name_by_edit_text(int view_id) {
        return ((EditText) findViewById(view_id)).getText().toString();
    }

    @NonNull
    private String get_name_by_spinner(int view_id) {
        Spinner spinner = findViewById(view_id);
        return String.valueOf(spinner.getSelectedItem());
    }
}