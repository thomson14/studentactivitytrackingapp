package com.example.studentactivitytrackingapp.habitTracker;


import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.util.LongSparseArray;
import android.util.SparseArray;
import android.util.SparseIntArray;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    HashMap<Integer, Integer> hashMap = new HashMap<>();
    HashMap<Integer, Integer> hashMapTwo = new HashMap<>();
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
                Log.d(TAG, "onChanged: IT CHANGED *************");
                    //TODO :: CLEAR THE HASH MAP HERE

                }
        });
        adapter.setOnItemClickListener(new HabitAdapter.OnItemClickListner() {
            @Override
            public void onItemClick(final Habit habit) {
                flag =0;
                Log.d(TAG, "onChanged: TITLE ******** "+ habit.getTitle());
                 LiveData<List<RecordHabit>> record = recordHabitViewModel.getAllRecords();
                 List<RecordHabit> recordHabits = record.getValue();


//                Log.d(TAG, "onItemClick: RECORD#######"+ record);
//                Log.d(TAG, "onItemClick: RECORD HABITS ********"+ recordHabits);
                for(RecordHabit recordHabit : recordHabits){
                    if(habit.getTitle().equals(recordHabit.getTitle())){
                        Log.d(TAG, "onItemClick: TIME STAMP For "+habit.getTitle() +" " + recordHabit.getTimeStamp());
                        hashMapTwo = makeMap(recordHabit.getTimeStamp());

                    }
                }

               getAllMapData(hashMapTwo);
//                Bundle bundle= new Bundle();
//                bundle.putSerializable("SPARSE_ARRAY_KEY",hashMap);
//                Intent in  = new Intent(HabitTrackerActivity.this,HabitCalendar.class);
//                startActivity(in);


                Log.d(TAG, "onItemClick: FLAG  --->  "+ flag);
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

    private void getAllMapData(HashMap<Integer, Integer> hashMapTwo) {

        for(Map.Entry<Integer,Integer> entry : hashMapTwo.entrySet()){
          Log.d(TAG, "getAllMapData: KEY **  "+ entry.getKey() + "VALUE ** "+ entry.getValue());
        }
    }



    private HashMap<Integer,Integer> makeMap(String timeStamp) {

        long timeStampt = Long.parseLong(timeStamp);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeStampt);
        int  date = calendar.get(Calendar.DATE);
        final int  month = calendar.get(Calendar.MONTH);

        Log.d(TAG, "HASHMAP "+ hashMap.get(month) +" makeMap: DATE "+ date + " Month "+ month + " TimeStamp " + timeStamp);

        //TODO: : if date for that particular month already exists in the map (Like if once a date is
        // added for that month) don't again add it in the Map
        recordHabitViewModel.getAllRecords().observeForever(new Observer<List<RecordHabit>>() {
            @Override
            public void onChanged(List<RecordHabit> recordHabits) {
                Log.d(TAG, "onChanged: IT CHANGED *************");
                //TODO :: CLEAR THE HASH MAP HERE
                if(hashMap.get(month) != null){
                    if(hashMap.get(month) == dateToday){
                        hashMap.clear();
                    }
                }

            }
        });
        if(hashMap.get(month) == null || hashMap.get(month) != date){
            flag++;
            hashMap.put(month,date);
            Log.d(TAG, "makeMap: "+ hashMap.get(month)  + "  : " + date);
        }


        return hashMap;

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
