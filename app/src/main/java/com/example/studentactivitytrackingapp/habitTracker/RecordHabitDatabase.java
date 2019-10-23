package com.example.studentactivitytrackingapp.habitTracker;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {RecordHabit.class}, version = 1)
public abstract  class RecordHabitDatabase extends RoomDatabase {
      private static RecordHabitDatabase instance;
      public  abstract RecordHabitDao recordHabitDao();

      public static synchronized RecordHabitDatabase getInstance(Context context){
          if(instance == null){
              instance = Room.databaseBuilder(context.getApplicationContext(),
              RecordHabitDatabase.class,"record_habit_db")
                      .fallbackToDestructiveMigration()
                      .addCallback(roomCallback).build();
          }
          return instance;
      }
      private static RoomDatabase.Callback  roomCallback = new RoomDatabase.Callback(){
          @Override
          public void onCreate(@NonNull SupportSQLiteDatabase db) {
              super.onCreate(db);
              new PopulateDatbaseAsyncTask(instance).execute();
          }
      };

      private static class PopulateDatbaseAsyncTask extends AsyncTask<Void,Void,Void>{
          private RecordHabitDao recordHabitDao;

          private PopulateDatbaseAsyncTask(RecordHabitDatabase database){
              recordHabitDao = database.recordHabitDao();
          }

          @Override
          protected Void doInBackground(Void... voids) {
              return null;
          }
      }

}
