package com.example.studentactivitytrackingapp.ToDoList;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "todo_table")
public class ToDo {

    private  String title;
    private boolean checkable_list;


    @PrimaryKey(autoGenerate = true)
    private  int id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCheckable_list() {
        return checkable_list;
    }

    public void setCheckable_list(boolean checkable_list) {
        this.checkable_list = checkable_list;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ToDo(String title, boolean checkable_list) {
        this.title = title;
        this.checkable_list = checkable_list;
    }
}
