package com.example.studentactivitytrackingapp.habitTracker;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class HabitViewModel extends AndroidViewModel {

    private HabitRepository repository;
    private LiveData<List<Habit>> allHabits;
//    private List<Integer> allDates;

    public HabitViewModel(@NonNull Application application) {
        super(application);
        repository = new HabitRepository(application);
        allHabits = repository.getAllHabits();

    }

    public void insert(Habit habit) {
        repository.insert(habit);
    }

    public void update(Habit habit) {
        repository.update(habit);
    }

    public void delete(Habit habit) {
        repository.delete(habit);
    }

    public void deleteAllHabits() {
        repository.deleteAllHabits();
    }

    public LiveData<List<Habit>> getAllHabits() {
        return allHabits;
    }

//    public List<Integer> getDates(Habit habit) {
//        allDates = repository.getAllDates(habit);
//        Log.d(TAG, "getDates: " + allDates);
//        return allDates;
//    }


}
