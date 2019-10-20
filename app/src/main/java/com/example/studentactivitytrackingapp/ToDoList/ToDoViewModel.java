package com.example.studentactivitytrackingapp.ToDoList;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


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
