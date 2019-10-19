package com.example.studentactivitytrackingapp.ToDoList;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.studentactivitytrackingapp.R;

public class AddToDoListActivity extends AppCompatActivity {

    public static final String EXTRA_ID=
            "com.example.studentactivitytrackingapp.EXTRA_ID";

    public static final String EXTRA_TITLE =
            "com.example.studentactivitytrackingapp.EXTRA_TITLE";

    public static final Boolean EXTRA_CHECKBOX = Boolean.valueOf("com.example.studentactivitytrackingapp.EXTRA_CHECKBOX");


    private EditText editTextTitleToDo;
    private CheckBox checkBoxToDo;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_do_list);
        editTextTitleToDo = findViewById(R.id.title_To_do);
        checkBoxToDo = findViewById(R.id.checkbox);
        Button saveButtonToDo = findViewById(R.id.saveButtonToDo);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        Intent in = getIntent();
        if(in.hasExtra(EXTRA_ID)){
            editTextTitleToDo.setText(in.getStringExtra(EXTRA_TITLE));
            checkBoxToDo.setChecked(in.getBooleanExtra("EXTRA_CHECKBOX",false));

        }

        saveButtonToDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTODO();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.to_do_menu, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.save_note_ToDo:
                saveTODO();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


    private void saveTODO() {
        String title = editTextTitleToDo.getText().toString();
        boolean isChecked = checkBoxToDo.isChecked();


        if(title.trim().isEmpty()){
            Toast.makeText(this,"Please Add Task..",Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE,title);
        data.putExtra("EXTRA_CHECKBOX",isChecked);

        int id = getIntent().getIntExtra(EXTRA_ID,-1);
        if(id != -1){
            data.putExtra(EXTRA_ID,id);
        }
        setResult(RESULT_OK,data);
        finish();
    }



}
