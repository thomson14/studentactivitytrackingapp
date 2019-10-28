package com.example.studentactivitytrackingapp.habitTracker;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RecordHabitDao {

    @Insert
    void insert(RecordHabit recordHabit);

    @Update
    void update(RecordHabit recordHabit);


    @Delete
    void delete(RecordHabit recordHabit);

    @Query("SELECT * FROM habit_record")
    LiveData<List<RecordHabit>> getAllRecords();

    @Query("SELECT timeStamp FROM habit_record WHERE title = :title")
    String getAllTimeStamp(String title);

    @Query("DELETE FROM habit_record")
    void deleteAllRecordHabit();

    @Query("DELETE FROM habit_record WHERE title = :title")
    void deleteAllWithTitle(String title);

}
