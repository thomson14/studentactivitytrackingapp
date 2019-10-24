package com.example.studentactivitytrackingapp.habitTracker;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class RecordHabitViewModel extends AndroidViewModel {

    private RecordHabitRepository recordHabitRepository;
    private LiveData<List<RecordHabit>> allRecords;
    private String timestamp;

    private RecordHabitDao habitDao;

    public RecordHabitViewModel(@NonNull Application application){
            super(application);

            recordHabitRepository = new RecordHabitRepository(application);
            allRecords = recordHabitRepository.getAllRecords();
    }

    public void insert(RecordHabit recordHabit){
        recordHabitRepository.insert(recordHabit);
    }
    public  void update(RecordHabit recordHabit){
        recordHabitRepository.update(recordHabit);
    }
    public void delete(RecordHabit recordHabit){
        recordHabitRepository.update(recordHabit);
    }
    public void deleteWithTitile(RecordHabit recordHabit){
        recordHabitRepository.deleteAllWithTitle(recordHabit);
    }

    public String getTimestamp(RecordHabit recordHabit){
        timestamp = recordHabitRepository.getTimestamp(recordHabit);
        return  timestamp;
    }


    public LiveData<List<RecordHabit>> getAllRecords(){
        return allRecords;
    }

}
