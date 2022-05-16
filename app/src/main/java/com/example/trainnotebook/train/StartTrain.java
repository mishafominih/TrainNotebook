package com.example.trainnotebook.train;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.trainnotebook.Database.DatabaseManager;
import com.example.trainnotebook.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StartTrain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_train);

        // Создадим начальные записи в бд
        DatabaseManager db_manager = new DatabaseManager(getApplicationContext());
        int train_id = db_manager.StartTrain("1");

        // Выберем первое упражнение
        Intent exercises = new Intent(this, ChooseExercise.class);
        exercises.putExtra("train_id", train_id);
        startActivity(exercises);

        int index = 1;
        HashMap<String, HashMap<String, HashMap<String, String>>> info = db_manager.GetTrainInfo(train_id);
        for (String exercise_name: info.keySet()){
            HashMap<String, HashMap<String, String>> sub_info = info.get(exercise_name);

            TextView main = new TextView(this);
            main.setText(exercise_name);

            for(String num: sub_info.keySet()){
                HashMap<String, String> dict = sub_info.get(num);

            }

        }
    }
}