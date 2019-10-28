package com.example.studentactivitytrackingapp.habitTrackerNew;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Habit.class, Event.class}, version = 2)
abstract class HabitDatabase extends RoomDatabase {
    abstract HabitDao habitDao();
}
