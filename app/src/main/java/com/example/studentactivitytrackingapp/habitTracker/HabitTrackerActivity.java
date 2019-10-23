package com.example.studentactivitytrackingapp.habitTracker;


import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentactivitytrackingapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jakewharton.threetenabp.AndroidThreeTen;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HabitTrackerActivity extends AppCompatActivity {

    public static final int HABIT_REQUEST = 5;
    private static final String TAG = " Habit Tracker Activity";
    private int dateToday;
    private int monthToday;
    Calendar c = Calendar.getInstance();
    private HabitViewModel habitViewModel;
    private RecordHabitViewModel recordHabitViewModel;
    FloatingActionButton addHabit, delete;
    int flag = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_tracker);
        AndroidThreeTen.init(this);

        addHabit = findViewById(R.id.addHabitfab);
        delete = findViewById(R.id.delete);
        dateToday = c.get(Calendar.DATE);
        monthToday = c.get(Calendar.MONTH);

        final RecyclerView habitTrackerRecyclerView = findViewById(R.id.recycler_view_habit);
        habitTrackerRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        habitTrackerRecyclerView.setHasFixedSize(true);

        final HabitAdapter adapter = new HabitAdapter(this);
        habitTrackerRecyclerView.setAdapter(adapter);

        habitViewModel = ViewModelProviders.of(this).get(HabitViewModel.class);
        recordHabitViewModel = ViewModelProviders.of(this).get(RecordHabitViewModel.class);



        habitViewModel.getAllHabits().observeForever(new Observer<List<Habit>>() {
            @Override
            public void onChanged(@Nullable List<Habit> habits) {
                //update Our recycler view later

                adapter.setHabits(habits);
                Log.d(TAG, "onChanged: Habits " + habits);

                for (Habit habit : habits) {
                    Log.d(TAG, "onChanged: " + habit.getTitle() + " ID " + habit.getId() + " STATUS " + habit.isStatus());
                }

            }
        });



        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                habitViewModel.delete(adapter.getHabitAt(viewHolder.getAdapterPosition()));
                Toast.makeText(HabitTrackerActivity.this, "Habit DELETED", Toast.LENGTH_SHORT).show();

            }
        }).attachToRecyclerView(habitTrackerRecyclerView);
        recordHabitViewModel.getAllRecords().observeForever(new Observer<List<RecordHabit>>() {
            @Override
            public void onChanged(List<RecordHabit> recordHabits) {

              //  for(RecordHabit recordHabit : recordHabits){
                 //   Log.d(TAG, "onChanged: RECORD HABIT TITLE  " + recordHabit.getTitle() +"  ");
//                            if(recordHabit.getTitle().equals(habit.getTitle())){
////                                String timestamps =  recordHabitViewModel.getTimestamp(recordHabit);
////                                Log.d(TAG, "onChanged: TIMESTAMP******** "+  timestamps);
//                            }

                }


          //  }
        });
        adapter.setOnItemClickListener(new HabitAdapter.OnItemClickListner() {
            @Override
            public void onItemClick(final Habit habit) {

//                RecordHabit re = new RecordHabit(habit.getTitle(),"");
                Log.d(TAG, "onChanged: TITLE ******** "+ habit.getTitle());
                //TODO:: When the List item is clicked , I need to fetch all the timestamps for that particular title;
                 LiveData<List<RecordHabit>> record = recordHabitViewModel.getAllRecords();
                 List<RecordHabit> recordHabits = record.getValue();


                Log.d(TAG, "onItemClick: RECORD#######"+ record);
                Log.d(TAG, "onItemClick: RECORD HABITS ********"+ recordHabits);
     //           assert recordHabits != null;
                for(RecordHabit recordHabit : recordHabits){
                    if(habit.getTitle().equals(recordHabit.getTitle())){
                        Log.d(TAG, "onItemClick: TIME STAMP For "+habit.getTitle() +" " + recordHabit.getTimeStamp());

                    }
                }
//                int sizw = record.getValue().size();
//                Log.d(TAG, "onItemClick: All records *******"+ record + " SIZE " + sizw);
//


            }
        });
        addHabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(HabitTrackerActivity.this, AddHabitActivity.class);
                startActivityForResult(in, HABIT_REQUEST);

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                habitViewModel.deleteAllHabits();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == HABIT_REQUEST && resultCode == RESULT_OK) {
            String habitName = data.getStringExtra(AddHabitActivity.EXTRA_HABIT_NAME);
            int reminderHour = data.getIntExtra(AddHabitActivity.EXTRA_SELECTED_HOUR, 12);
            int reminderMinute = data.getIntExtra(AddHabitActivity.EXTRA_SELECTED_MINUTE, 20);

            Habit habit = new Habit(habitName, reminderHour, reminderMinute, false);
            habitViewModel.insert(habit);

            Toast.makeText(this, "Habit Saved", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "onActivityResult: Habit Name " + habit.getTitle() + " Reminder Time " + habit.getReminderHour() + " : " +
                    habit.getReminderMin() + " HABIT ID ---> " + habit.getId() + " Habit status " + habit.isStatus()
                    + " current Date and month   "
            );

        } else {
            Toast.makeText(this, "Not Saved", Toast.LENGTH_SHORT).show();
        }
    }


}
