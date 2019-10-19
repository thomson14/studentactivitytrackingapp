package com.example.studentactivitytrackingapp;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ToDoDao {

    @Insert
    void insert(ToDo note);

    @Update
    void update(ToDo note);

    @Delete
    void delete(ToDo note);

    @Query("DELETE FROM todo_table ")
    void deleteAllTodos();

    @Query("SELECT * FROM todo_table ")
    LiveData<List<ToDo>> getAllTodo();



}
