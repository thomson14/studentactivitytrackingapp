package com.example.studentactivitytrackingapp;


import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

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
