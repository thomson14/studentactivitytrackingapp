package com.example.studentactivitytrackingapp;

public class ToDoItem {

    private String work;
    private String time;
    private boolean isChecked;

    public ToDoItem(String work,String time){

        this.work = work;
        this.time = time;
        isChecked = false;
    }

    public String getWork()
    {
        return work;
    }

    public void setWork(String work)
    {
        this.work = work;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public boolean isChecked()
    {
        return isChecked;
    }

    public void setChecked(boolean checked)
    {
        isChecked = checked;
    }
}
