package com.example.studentactivitytrackingapp.noteTaking;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.studentactivitytrackingapp.R;

public class AddNoteActivity extends AppCompatActivity {

    public static final String EXTRA_ID=
            "com.example.studentactivitytrackingapp.EXTRA_ID";

    public static final String EXTRA_TITLE =
            "com.example.studentactivitytrackingapp.EXTRA_TITLE";

    public static final String EXTRA_DESCRIPTION =
            "com.example.studentactivitytrackingapp.EXTRA_DESCRIPTION";

    private EditText editTextTitle;
    private EditText editTextDescription;
    private Button saveButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTextTitle = findViewById(R.id.title_note);
        editTextDescription = findViewById(R.id.description_note);
        saveButton = findViewById(R.id.saveButton);


//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);
//        setTitle("Add Note");

        Intent in = getIntent();
        if(in.hasExtra(EXTRA_ID)){
            editTextTitle.setText(in.getStringExtra(EXTRA_TITLE));
            editTextDescription.setText(in.getStringExtra(EXTRA_DESCRIPTION));

        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void saveNote() {
       String title = editTextTitle.getText().toString();
       String desc = editTextDescription.getText().toString();

       if(title.trim().isEmpty() || desc.trim().isEmpty()){
           Toast.makeText(this,"Please Insert title and description",Toast.LENGTH_SHORT).show();
           return;
       }

       Intent data = new Intent();
       data.putExtra(EXTRA_TITLE,title);
       data.putExtra(EXTRA_DESCRIPTION,desc);

       int id = getIntent().getIntExtra(EXTRA_ID,-1);
       if(id != -1){
           data.putExtra(EXTRA_ID,id);
       }
       setResult(RESULT_OK,data);
       finish();
    }


}
