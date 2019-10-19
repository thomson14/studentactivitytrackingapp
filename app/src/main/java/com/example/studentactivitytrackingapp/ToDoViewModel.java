package com.example.studentactivitytrackingapp;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class ToDoViewModel extends AndroidViewModel {

    private ToDoRepository repository;
    private LiveData<List<ToDo>> allTodos;


    public ToDoViewModel(@NonNull Application application) {
        super(application);

        repository = new ToDoRepository(application);
        allTodos = repository.getAllTodo();
    }

    public  void insert(ToDo note){
        repository.insert(note);
    }

    public void update(ToDo note){
        repository.update(note);
    }

    public void delete(ToDo note){
        repository.delete(note);
    }

    public void deleteAllTodos(){
        repository.deleteAllTodos();
    }

    public LiveData<List<ToDo>> getAllTodos(){
        return allTodos;
    }
}
