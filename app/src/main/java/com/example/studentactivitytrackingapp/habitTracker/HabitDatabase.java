package com.example.studentactivitytrackingapp.habitTracker;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Habit.class},version = 1)
public abstract class HabitDatabase extends RoomDatabase {
    private static HabitDatabase instance;

    public abstract HabitDao habitDao();

    public static synchronized HabitDatabase getInstance(Context context){

        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    HabitDatabase.class,"habit_database")
                    .fallbackToDestructiveMigration().addCallback(roomCallback).build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };

    private static class PopulateDatabaseAsyncTask extends AsyncTask<Void,Void,Void>{
           private HabitDao habitDao;

           private PopulateDatabaseAsyncTask(HabitDatabase db){
               habitDao = db.habitDao();
           }

        @Override
        protected Void doInBackground(Void... voids) {
               return null;
        }
    }



}
