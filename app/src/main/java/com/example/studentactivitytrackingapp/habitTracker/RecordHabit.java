package com.example.studentactivitytrackingapp.habitTracker;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "habit_record")
public class RecordHabit {
    private String title;
    private String timeStamp;

    @PrimaryKey(autoGenerate = true)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public RecordHabit(String title, String timeStamp) {
        this.title = title;
        this.timeStamp = timeStamp;
    }
}
