package com.example.studentactivitytrackingapp.habitTracker;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.studentactivitytrackingapp.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HabitTrackerActivity extends AppCompatActivity {

    public static final int HABIT_REQUEST = 5;
    private static final String TAG =" Habit Tracker Activity" ;
    private  int dateToday;
    private  int monthToday;
    Calendar c = Calendar.getInstance();
    private  HabitViewModel habitViewModel;
    FloatingActionButton addHabit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_tracker);

        addHabit = findViewById(R.id.addHabitfab);
        dateToday = c.get(Calendar.DATE);
        monthToday = c.get(Calendar.MONTH);

        final RecyclerView habitTrackerRecyclerView = findViewById(R.id.recycler_view_habit);
        habitTrackerRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        habitTrackerRecyclerView.setHasFixedSize(true);

        final HabitAdapter adapter = new HabitAdapter(this);
        habitTrackerRecyclerView.setAdapter(adapter);

        habitViewModel = ViewModelProviders.of(this).get(HabitViewModel.class);
        habitViewModel.getAllHabits().observe(this, new Observer<List<Habit>>() {
            @Override
            public void onChanged(@Nullable List<Habit> habits) {
                //update Our recycler view later

                adapter.setHabits(habits);
                Log.d(TAG, "onChanged: Habits "+ habits);

                for(Habit habit : habits){
                    Log.d(TAG, "onChanged: "+ habit.getTitle() + " ID "+ habit.getId() + " STATUS "+ habit.isStatus());
                }

            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                  habitViewModel.delete(adapter.getHabitAt(viewHolder.getAdapterPosition()));
                  Toast.makeText(HabitTrackerActivity.this,"Habit DELETED", Toast.LENGTH_SHORT).show();

            }
        }).attachToRecyclerView(habitTrackerRecyclerView);

        addHabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(HabitTrackerActivity.this,AddHabitActivity.class);
                startActivityForResult(in,HABIT_REQUEST);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == HABIT_REQUEST && resultCode == RESULT_OK){
           String habitName=  data.getStringExtra(AddHabitActivity.EXTRA_HABIT_NAME);
           int reminderHour = data.getIntExtra(AddHabitActivity.EXTRA_SELECTED_HOUR,12);
           int reminderMinute = data.getIntExtra(AddHabitActivity.EXTRA_SELECTED_MINUTE,20);

           Habit habit = new Habit(habitName,reminderHour,reminderMinute,false,dateToday,monthToday);
           habitViewModel.insert(habit);

            Toast.makeText(this,"Habit Saved",Toast.LENGTH_SHORT).show();
            Log.d(TAG, "onActivityResult: Habit Name "+ habit.getTitle()+ " Reminder Time "+ habit.getReminderHour() + " : "+
                    habit.getReminderMin() + " HABIT ID ---> "+ habit.getId() + " Habit status "+ habit.isStatus()
                    + " current Date and month   " + habit.getCurrentDate() + " : "+ habit.getCurrentMonth()
            );

        }
        else{
            Toast.makeText(this,"Not Saved",Toast.LENGTH_SHORT).show();
        }
    }


}
