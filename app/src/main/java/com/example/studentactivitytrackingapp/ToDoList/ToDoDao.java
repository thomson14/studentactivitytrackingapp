package com.example.studentactivitytrackingapp.ToDoList;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.studentactivitytrackingapp.ToDoList.ToDo;

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
