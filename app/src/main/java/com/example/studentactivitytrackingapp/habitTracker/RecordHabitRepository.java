package com.example.studentactivitytrackingapp.habitTracker;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class RecordHabitRepository {
    private RecordHabitDao habitDao;
    private LiveData<List<RecordHabit>> allRecords;

    private String timeStamp;
    public RecordHabitRepository(Application application){
        RecordHabitDatabase database= RecordHabitDatabase.getInstance(application);
        habitDao = database.recordHabitDao();
        allRecords = habitDao.getAllRecords();

    }

    public  void insert(RecordHabit recordHabit){
        new InsertRecordAsyncTask(habitDao).execute(recordHabit);

    }
    public void update(RecordHabit recordHabit){

        new UpdateRecordAsyncTask(habitDao).execute(recordHabit);
    }
    public  void delete(RecordHabit recordHabit){
        new DeleteRecordAsyncTask(habitDao).execute(recordHabit);
    }

    public void deleteAllRecords(){
        new DeleteAllHabitAsyncTask(habitDao).execute();
    }

    public void deleteAllWithTitle(RecordHabit recordHabit){
        new DeleteAllWithTitle(habitDao).execute(recordHabit);
    }

    public LiveData<List<RecordHabit>>  getAllRecords(){
        return allRecords;
    }


    public String getTimestamp(RecordHabit recordHabit){
        try {
            timeStamp = new GetTimeStamp(habitDao).execute(recordHabit).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  timeStamp;
    }

    private static  class InsertRecordAsyncTask extends AsyncTask<RecordHabit, Void,Void>{
        private RecordHabitDao recordHabitDao;

        private  InsertRecordAsyncTask(RecordHabitDao recordHabitDao){
            this.recordHabitDao = recordHabitDao;
        }

        @Override
        protected Void doInBackground(RecordHabit... recordHabits) {
            recordHabitDao.insert(recordHabits[0]);

            return null;
        }

    }

    private static class DeleteRecordAsyncTask extends AsyncTask<RecordHabit,Void,Void>{
        private  RecordHabitDao recordHabitDao;

        private DeleteRecordAsyncTask(RecordHabitDao recordHabitDao){
            this.recordHabitDao = recordHabitDao;
        }

        @Override
        protected Void doInBackground(RecordHabit... recordHabits) {
            recordHabitDao.delete(recordHabits[0]);
            return null;
        }
    }

    private static class UpdateRecordAsyncTask extends AsyncTask<RecordHabit,Void,Void>{
        private RecordHabitDao recordHabitDao;

        private UpdateRecordAsyncTask(RecordHabitDao recordHabitDao){
            this.recordHabitDao = recordHabitDao;
        }

        @Override
        protected Void doInBackground(RecordHabit... recordHabits) {
            recordHabitDao.delete(recordHabits[0]);
            return null;
        }
    }

    private static class GetTimeStamp extends  AsyncTask<RecordHabit,Void,String>{

        private RecordHabitDao recordHabitDao;
        private GetTimeStamp(RecordHabitDao recordHabitDao){
            this.recordHabitDao = recordHabitDao;
        }

        @Override
        protected String doInBackground(RecordHabit... recordHabits) {
            String timestamp = recordHabitDao.getAllTimeStamp(recordHabits[0].getTitle());
            return timestamp;
        }
    }
    private static class DeleteAllWithTitle extends  AsyncTask<RecordHabit,Void,Void>{

        private RecordHabitDao recordHabitDao;
        private DeleteAllWithTitle(RecordHabitDao recordHabitDao){
            this.recordHabitDao = recordHabitDao;
        }

        @Override
        protected Void doInBackground(RecordHabit... recordHabits) {
            recordHabitDao.deleteAllWithTitle(recordHabits[0].getTitle());
            return  null;

        }
    }

    private static class DeleteAllHabitAsyncTask extends AsyncTask<Void, Void, Void> {

        private RecordHabitDao habitDao;

        private DeleteAllHabitAsyncTask(RecordHabitDao habitDao) {
            this.habitDao = habitDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            habitDao.deleteAllRecordHabit();
            return null;
        }
    }




}
