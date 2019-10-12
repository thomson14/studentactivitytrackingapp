package com.example.studentactivitytrackingapp.noteTaking;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {

    private  String title;
    private  String description;


    @PrimaryKey(autoGenerate = true)
    private  int id;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Note(String title, String description) {
        this.title = title;
        this.description = description;

    }
}
