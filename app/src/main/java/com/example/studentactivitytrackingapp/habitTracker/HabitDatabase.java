package com.example.studentactivitytrackingapp.habitTracker;

import android.content.Context;
import android.os.AsyncTask;


import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Habit.class}, version = 1)
public abstract class HabitDatabase extends RoomDatabase {
    private static HabitDatabase instance;

    public abstract HabitDao habitDao();

    public static synchronized HabitDatabase getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    HabitDatabase.class, "habit_database")
                    .fallbackToDestructiveMigration().addCallback(roomCallback).build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDatabaseAsyncTask(instance).execute();

        }
    };

    private static class PopulateDatabaseAsyncTask extends AsyncTask<Void, Void, Void> {
        private HabitDao habitDao;

        private PopulateDatabaseAsyncTask(HabitDatabase db) {
            habitDao = db.habitDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            habitDao.insert(new Habit("Sky Diving",12,24,false,19,10));
            habitDao.insert(new Habit("Guitar Practice",2,24,false,19,10));
            habitDao.insert(new Habit("Rolls",16,11,false,13,10));
            habitDao.insert(new Habit("Gym",11,11,false,17,10));
            habitDao.insert(new Habit("Polygraphy",21,24,false,11,10));
            habitDao.insert(new Habit("Music",20,24,false,17,10));
            habitDao.insert(new Habit("Worship",15,14,false,18,10));
            habitDao.insert(new Habit("Typing Practice",15,14,false,18,10));

            return null;
        }
    }


}
