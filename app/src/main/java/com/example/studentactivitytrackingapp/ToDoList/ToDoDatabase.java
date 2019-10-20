package com.example.studentactivitytrackingapp.ToDoList;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities =  {ToDo.class},version = 1,exportSchema = false)
public abstract class ToDoDatabase extends RoomDatabase {

    private static ToDoDatabase instancetodo;
    public  abstract ToDoDao to_do_Dao();

    public  static  synchronized ToDoDatabase getInstance(Context context){
        if(instancetodo == null){
            instancetodo = Room.databaseBuilder(context.getApplicationContext(),
                    ToDoDatabase.class,"todo_database"
            ).fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return  instancetodo;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDatabaseAsyncTask(instancetodo).execute();
        }
    };

    private static class PopulateDatabaseAsyncTask extends AsyncTask<Void,Void,Void> {

        private ToDoDao to_do_Dao;

        private PopulateDatabaseAsyncTask(ToDoDatabase db){

            to_do_Dao = db.to_do_Dao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            return null;
        }
    }


}
