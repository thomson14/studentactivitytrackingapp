package com.example.studentactivitytrackingapp.habitTracker;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface HabitDao {

    @Insert
    void insert(Habit habit);

    @Update
    void update(Habit habit);

    @Delete
    void delete(Habit habit);

    @Query("DELETE FROM habit_table")
    void deleteAllHabit();

    @Query("SELECT * FROM habit_table")
    LiveData<List<Habit>> getAllHabits();

    @Query("SELECT currentDate FROM habit_table WHERE title =:title")
    List<Integer> getDates(String title);


}

