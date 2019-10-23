package com.example.studentactivitytrackingapp.habitTracker;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "habit_table")
public class Habit {
    private  String title;
    private  int reminderHour;
    private  int reminderMin;
    private  boolean status;

    @PrimaryKey(autoGenerate = true)
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public int getReminderHour() {
        return reminderHour;
    }

    public int getReminderMin() {
        return reminderMin;
    }

    public boolean isStatus() {
        return status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReminderHour(int reminderHour) {
        this.reminderHour = reminderHour;
    }

    public void setReminderMin(int reminderMin) {
        this.reminderMin = reminderMin;
    }

    public int getId() {
        return id;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    public Habit(String title, int reminderHour, int reminderMin, boolean status) {
        this.title = title;
        this.reminderHour = reminderHour;
        this.reminderMin = reminderMin;
        this.status = status;

    }

    @Ignore
    public Habit() {
    }
}
