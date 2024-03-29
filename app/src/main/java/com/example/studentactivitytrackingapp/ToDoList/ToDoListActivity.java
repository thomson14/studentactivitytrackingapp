package com.example.studentactivitytrackingapp.ToDoList;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentactivitytrackingapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class ToDoListActivity extends AppCompatActivity {

    private ToDoViewModel todoViewModel;
    public static final int TODO_REQUEST =1;
    public static final int EDIT_TODO_REQUEST =2;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);



        FloatingActionButton fabtodo = findViewById(R.id.add_to_do_fab);

        fabtodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ToDoListActivity.this, AddToDoListActivity.class);
                startActivityForResult(it,TODO_REQUEST);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view_To_do);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final ToDoAdapter adapter = new ToDoAdapter();
        recyclerView.setAdapter(adapter);

        todoViewModel = ViewModelProviders.of(this).get(ToDoViewModel.class);
        todoViewModel.getAllTodos().observeForever(new Observer<List<ToDo>>() {
            @Override
            public void onChanged(@Nullable List<ToDo> todo) {
                //update Recycler view
                adapter.setDoTo(todo);
            }
        });


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                todoViewModel.delete(adapter.getToDoAt(viewHolder.getAdapterPosition()));
                Toast.makeText(ToDoListActivity.this,"ToDoListActivity DELETED",Toast.LENGTH_SHORT).show();

            }
        }).attachToRecyclerView(recyclerView);


        adapter.setOnItemClickListener(new ToDoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ToDo todo) {


                Intent intent = new Intent(ToDoListActivity.this,AddToDoListActivity.class);
                intent.putExtra(AddToDoListActivity.EXTRA_ID,todo.getId());
                intent.putExtra(AddToDoListActivity.EXTRA_TITLE,todo.getTitle());
                // intent.putExtra(String.valueOf(AddNoteActivity.EXTRA_CHECKBOX),note.getCheckbox());
                intent.putExtra(String.valueOf(AddToDoListActivity.EXTRA_CHECKBOX), false);

                startActivityForResult(intent,EDIT_TODO_REQUEST);

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == TODO_REQUEST && resultCode == RESULT_OK){
            String title = data.getStringExtra(AddToDoListActivity.EXTRA_TITLE);
            Boolean check = Boolean.valueOf(data.getStringExtra(String.valueOf(AddToDoListActivity.EXTRA_CHECKBOX)));

            ToDo todo = new ToDo(title,check);
            todoViewModel.insert(todo);

            Toast.makeText(this," LIST SAVED ",Toast.LENGTH_SHORT).show();
        }

        else  if(requestCode == EDIT_TODO_REQUEST && resultCode == RESULT_OK){
            int id = data.getIntExtra(AddToDoListActivity.EXTRA_ID,-1);

            if(id == -1){
                Toast.makeText(this," LIST CAN'T BE UPDATED",Toast.LENGTH_SHORT).show();
                return;
            }

            String title = data.getStringExtra(AddToDoListActivity.EXTRA_TITLE);
            Boolean check = Boolean.valueOf(data.getStringExtra(String.valueOf(AddToDoListActivity.EXTRA_CHECKBOX)));

            ToDo toDo = new ToDo(title,check);
            toDo.setId(id);
            todoViewModel.update(toDo);

            Toast.makeText(this," LIST  UPDATED",Toast.LENGTH_SHORT).show();

        }

        else {
            Toast.makeText(this," List Not Saved ",Toast.LENGTH_LONG).show();
        }
    }


}
