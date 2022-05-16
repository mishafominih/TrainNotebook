package com.example.trainnotebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.trainnotebook.Database.DatabaseManager;

import java.util.ArrayList;
import java.util.HashMap;

public class Exercises extends AppCompatActivity {

    DatabaseManager db_manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

        db_manager = new DatabaseManager(getApplicationContext());

        add_exercises(db_manager);  // Добавить упражнения из бд

        findViewById(R.id.to_add_exercise_button).setOnClickListener(view -> {
            Intent exercises = new Intent(this, NewExercise.class);
            startActivity(exercises);
        });
    }

    private void add_exercises(DatabaseManager db_manager) {
        // Список клиентов
        ArrayList<HashMap<String, Object>> exercises = db_manager.GetAllExercises();

        // Какие параметры клиента мы будем отображать в соответствующих
        // элементах из разметки adapter_item.xml
        String[] from = { "Name", "Description", "Unit", "Time"};
        int[] to = {
                R.id.item_exercise_name,
                R.id.item_exercise_description,
                R.id.item_exercise_unit,
                R.id.item_exercise_time
        };

        // Создаем адаптер
        SimpleAdapter adapter = new SimpleAdapter(this, exercises, R.layout.adapter_item_exercise, from, to);
        ListView listView = (ListView) findViewById(R.id.layout_all_exercises);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        add_exercises(db_manager);
    }
}