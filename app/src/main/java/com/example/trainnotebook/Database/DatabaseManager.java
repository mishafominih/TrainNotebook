package com.example.trainnotebook.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.trainnotebook.Exercise;

import java.io.IOException;
import java.util.List;
import java.util.Set;


public class DatabaseManager {
    DatabaseHelper mDBHelper;

    public DatabaseManager(Context context){
        mDBHelper = new DatabaseHelper(context);
    }

    public String[] GetUnits(){
        Cursor cursor = SetQuery("SELECT * FROM Units");

        return ToStringArr(cursor);
    }

    public void AddExercise(Exercise ex){
        String sql = String.format(
            "Insert into Exercises (Name, Description, Unit, Time) values('%s', '%s', '%s', '%s')",
            ex.Name, ex.Description, ex.Unit, ex.Time
        );
        SetQuery(sql);
    }

    public String[] GetTimes(){
        Cursor cursor = SetQuery("SELECT * FROM Times");

        return ToStringArr(cursor);
    }

    public Cursor SetQuery(String sql){
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
        return mDb.rawQuery(sql, null);
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
}
