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


//              recordHabitDao.insert(new RecordHabit("Yoga","1571751113"));
//              recordHabitDao.insert(new RecordHabit("Yoga","1571405557"));
//              recordHabitDao.insert(new RecordHabit("Yoga","1571319157"));
//              recordHabitDao.insert(new RecordHabit("Yoga","1571401957"));
//              recordHabitDao.insert(new RecordHabit("Yoga","1568813557"));
//              recordHabitDao.insert(new RecordHabit("Yoga","1569850440"));
//              recordHabitDao.insert(new RecordHabit("Yoga","1569764040"));
//              recordHabitDao.insert(new RecordHabit("Yoga","1566480880"));
//
//              recordHabitDao.insert(new RecordHabit("Exercise","1571923625"));
//              recordHabitDao.insert(new RecordHabit("Exercise","1571837225"));
//              recordHabitDao.insert(new RecordHabit("Exercise","1571318825"));
//              recordHabitDao.insert(new RecordHabit("Exercise","1571751113"));
//              recordHabitDao.insert(new RecordHabit("Exercise","1571405557"));
//              recordHabitDao.insert(new RecordHabit("Exercise","1571319157"));
//              recordHabitDao.insert(new RecordHabit("Exercise","1571401957"));
//              recordHabitDao.insert(new RecordHabit("Exercise","1568813557"));
//              recordHabitDao.insert(new RecordHabit("Exercise","1569850440"));
//              recordHabitDao.insert(new RecordHabit("Exercise","1569764040"));
//              recordHabitDao.insert(new RecordHabit("Exercise","1566480880"));
//
//              recordHabitDao.insert(new RecordHabit("Water","1571923625"));
//              recordHabitDao.insert(new RecordHabit("Water","1571837225"));
//              recordHabitDao.insert(new RecordHabit("Water","1571318825"));
//              recordHabitDao.insert(new RecordHabit("Water","1571751113"));
//              recordHabitDao.insert(new RecordHabit("Water","1571405557"));
//              recordHabitDao.insert(new RecordHabit("Water","1571319157"));
//              recordHabitDao.insert(new RecordHabit("Water","1571401957"));
//              recordHabitDao.insert(new RecordHabit("Water","1568813557"));
//              recordHabitDao.insert(new RecordHabit("Water","1569850440"));
//              recordHabitDao.insert(new RecordHabit("Water","1569764040"));
//              recordHabitDao.insert(new RecordHabit("Water","1566480880"));
//
//              recordHabitDao.insert(new RecordHabit("Cycling","1571923625"));
//              recordHabitDao.insert(new RecordHabit("Cycling","1571837225"));
//              recordHabitDao.insert(new RecordHabit("Cycling","1571318825"));
//              recordHabitDao.insert(new RecordHabit("Cycling","1571751113"));
//              recordHabitDao.insert(new RecordHabit("Cycling","1571405557"));
//              recordHabitDao.insert(new RecordHabit("Cycling","1571319157"));
//              recordHabitDao.insert(new RecordHabit("Cycling","1571401957"));
//              recordHabitDao.insert(new RecordHabit("Cycling","1568813557"));
//              recordHabitDao.insert(new RecordHabit("Cycling","1569850440"));
//              recordHabitDao.insert(new RecordHabit("Cycling","1569764040"));
//              recordHabitDao.insert(new RecordHabit("Cycling","1566480880"));


              return null;
          }
      }

}
