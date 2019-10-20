package com.example.studentactivitytrackingapp.habitTracker;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "habit_table")
public class Habit {
    private  String title;
    private  int reminderHour;
    private  int reminderMin;
    private  boolean status;
    private  int currentDate;
    private  int currentMonth;

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

    public int getCurrentDate() {
        return currentDate;
    }

    public int getCurrentMonth() {
        return currentMonth;
    }

    public int getId() {
        return id;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Habit(String title, int reminderHour, int reminderMin, boolean status, int currentDate, int currentMonth) {
        this.title = title;
        this.reminderHour = reminderHour;
        this.reminderMin = reminderMin;
        this.status = status;
        this.currentDate = currentDate;
        this.currentMonth = currentMonth;
    }
    @Ignore
    public Habit(String title, int reminderHour, int reminderMin, boolean status) {
        this.title = title;
        this.reminderHour = reminderHour;
        this.reminderMin = reminderMin;
        this.status = status;
    }

    @Ignore
    public Habit(boolean status){
        this.status = status;
    }

    @Ignore
    public Habit() {}
}
