package com.example.trainnotebook;

public class Exercise {
    public String Name;
    public String Description;
    public String Unit;
    public String Time;

    public Exercise(String name, String description, String unit, String time) {
        Name = name;
        Description = description;
        Unit = unit;
        Time = time;
    }

    public boolean is_correct(){
        return check_field(Name) && check_field(Description) && check_field(Unit) && check_field(Time);
    }

    private boolean check_field(String field){
        return field != null && !field.equals("");
    }
}
