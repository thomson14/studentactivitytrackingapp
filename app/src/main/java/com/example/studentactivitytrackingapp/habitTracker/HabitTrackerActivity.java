package com.example.studentactivitytrackingapp.habitTracker;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.studentactivitytrackingapp.R;

import java.util.List;

public class HabitTrackerActivity extends AppCompatActivity {

    private  HabitViewModel habitViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_tracker);

        habitViewModel = ViewModelProviders.of(this).get(HabitViewModel.class);
        habitViewModel.getAllHabits().observe(this, new Observer<List<Habit>>() {
            @Override
            public void onChanged(@Nullable List<Habit> habits) {
                //update Our recycler view later

            }
        });
    }
}
