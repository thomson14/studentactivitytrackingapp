package com.example.studentactivitytrackingapp;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class ToDoRepository {

    private ToDoDao to_do_Dao;
    private LiveData<List<ToDo>> allTodo;

    public ToDoRepository(Application application) {
        ToDoDatabase database = ToDoDatabase.getInstance(application);

        to_do_Dao = database.to_do_Dao();
        allTodo = to_do_Dao.getAllTodo();
    }

    public void insert(ToDo todo){
        new InsertToDoAsyncTask(to_do_Dao).execute(todo);
    }

    public void delete(ToDo todo){

        new DeleteToDoAsyncTask(to_do_Dao).execute(todo);

    }

    public void update(ToDo todo){
        new UpdateToDoAsyncTask(to_do_Dao).execute(todo);
    }
    public void deleteAllTodos(){
        new DeleteAllToDoAsyncTask(to_do_Dao).execute();
    }

    public LiveData<List<ToDo>> getAllTodo()
        {
        return  allTodo;
    }

    private static class InsertToDoAsyncTask extends AsyncTask<ToDo,Void,Void> {

        private ToDoDao toDoDao;

        private InsertToDoAsyncTask(ToDoDao toDoDao){
            this.toDoDao = toDoDao;

        }
        @Override
        protected Void doInBackground(ToDo... toDos) {
            toDoDao.insert(toDos[0]);
            return null;
        }
    }


    private static class UpdateToDoAsyncTask extends AsyncTask<ToDo,Void,Void> {

        private ToDoDao toDoDao;


        private UpdateToDoAsyncTask(ToDoDao toDoDao){
            this.toDoDao = toDoDao;

        }
        @Override
        protected Void doInBackground(ToDo... toDos) {
            toDoDao.update(toDos[0]);
            return null;
        }
    }




    private static class DeleteToDoAsyncTask extends AsyncTask<ToDo,Void,Void> {

        private ToDoDao toDoDao;

        private DeleteToDoAsyncTask(ToDoDao toDoDao){
            this.toDoDao = toDoDao;

        }
        @Override
        protected Void doInBackground(ToDo... toDos) {
            toDoDao.delete(toDos[0]);
            return null;
        }
    }


    private static class DeleteAllToDoAsyncTask extends AsyncTask<ToDo,Void,Void> {

        private ToDoDao toDoDao;


        private DeleteAllToDoAsyncTask(ToDoDao toDoDao){
            this.toDoDao = toDoDao;


        }
        @Override
        protected Void doInBackground(ToDo... toDos ) {
            toDoDao.deleteAllTodos();
            return null;
        }
    }


}
