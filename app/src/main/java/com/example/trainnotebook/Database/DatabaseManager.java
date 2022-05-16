package com.example.trainnotebook.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import androidx.core.app.CoreComponentFactory;

import com.example.trainnotebook.Exercise;

import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;


public class DatabaseManager {
    DatabaseHelper mDBHelper;

    public DatabaseManager(Context context){
        mDBHelper = new DatabaseHelper(context);
    }

    public String[] GetUnits(){
        Cursor cursor = GetQuery("SELECT * FROM Units");

        return ToStringArr(cursor);
    }

    public void AddExercise(Exercise ex){
        String sql = String.format(
            "Insert into Exercises (Name, Description, Unit, Time) values('%s', '%s', '%s', '%s')",
            ex.Name, ex.Description, ex.Unit, ex.Time
        );
        SetQuery(sql);
    }

    public HashMap<String, HashMap<String, HashMap<String, String>>> GetTrainInfo(int train_id){
        HashMap<String, HashMap<String, HashMap<String, String>>> result = new HashMap<>();
        String sql = "select tr.Train, ex.Name, ex.Unit, ex.Time, tr.Num, tr.Value, tr.Result, tr.Comment " +
                "from TrainExercisesResults tr " +
                "LEFT JOIN Exercises ex " +
                "on tr.Exercise = ex.id " +
                "where tr.Train = 2";
        ArrayList<HashMap<String, String>> query = ToNormal(GetQuery(sql));

        // Создадим нужную иерархию
        for (HashMap<String, String> val: query) {
            String exercise_name = val.get("Name");
            String num = val.get("Num");

            if(result.containsKey(exercise_name)){
                result.get(exercise_name).put(num, val);
            }
            else{
                HashMap<String, HashMap<String, String>> item = new HashMap<>();
                item.put(num, val);
                result.put(exercise_name, item);
            }
        }
        return result;
    }

    public ArrayList<HashMap<String, Object>> GetAllExercises(){
        Cursor cursor = GetQuery("Select * from Exercises");

        ArrayList<HashMap<String, Object>> result = new ArrayList<>();
        HashMap<String, Object> item;

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            item = new HashMap<String, Object>();

            // Заполняем клиента
            item.put("Name",  cursor.getString(1));
            item.put("Description",  cursor.getString(2));
            item.put("Unit",  cursor.getString(3));
            item.put("Time",  cursor.getString(4));

            // Закидываем клиента в список клиентов
            result.add(item);
            // Переходим к следующему клиенту
            cursor.moveToNext();
        }
        cursor.close();
        return result;
    }

    public String[] GetTimes(){
        Cursor cursor = GetQuery("SELECT * FROM Times");

        return ToStringArr(cursor);
    }

    public int StartTrain(String comment){
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
        String sql = String.format("insert into Trains (StartTime, Date, Comment) values('%s', '%s', '%s');", currentTime, currentDate, comment);
        SetQuery(sql);
        Cursor cursor = GetQuery("SELECT last_insert_rowid() from Trains");
        return Integer.parseInt(ToStringArr(cursor)[0]);
    }

    public void ChooseExercise(int train_id, String exercise_name, int count_repeat){
        Cursor cursor = GetQuery(String.format("SELECT id FROM Exercises where Name = '%s'", exercise_name));
        int exercise_id = Integer.parseInt(ToStringArr(cursor)[0]);

        cursor = GetQuery(String.format("SELECT * FROM TrainExercisesResults where Train = '%s' and Exercise = '%s'", train_id, exercise_id));
        if(ToStringArr(cursor).length == 0){
            String sql = String.format("Insert into TrainExercisesResults (Train, Exercise) values('%s', '%s')", train_id, exercise_id);
            SetQuery(sql);
        }
    }

    public Cursor GetQuery(String sql){
        SQLiteDatabase mDb;
        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }

        try {
            mDb = mDBHelper.getReadableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }
        return mDb.rawQuery(sql, null);
    }

    public void SetQuery(String sql){
        SQLiteDatabase mDb;
        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }

        try {
            mDb = mDBHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }
        mDb.execSQL(sql);
    }

    public String[] ToStringArr(Cursor cursor){
        String[] result = new String[cursor.getCount()];
        int index = 0;

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            result[index] = cursor.getString(0);
            index++;
            cursor.moveToNext();
        }
        cursor.close();
        return result;
    }

    public ArrayList<HashMap<String, String>> ToNormal(Cursor cursor){
        ArrayList<HashMap<String, String>> result = new ArrayList<>();
        HashMap<String, String> item;

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            item = new HashMap<String, String>();

            for(String column_name: cursor.getColumnNames()){
                int column_index = cursor.getColumnIndex(column_name);
                String value = cursor.getString(column_index);
                item.put(column_name, value);
            }

            result.add(item);

            cursor.moveToNext();
        }
        cursor.close();
        return result;
    }
}
