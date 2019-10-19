package com.example.studentactivitytrackingapp.noteTaking;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.studentactivitytrackingapp.R;

import java.util.List;

public class NoteTakingActivity extends AppCompatActivity {


    private static final String TAG = "Note Taking Activity";
    private NoteViewModel noteViewModel;
    public static final int NOTE_REQUEST = 1;
    public static final int EDIT_NOTE_REQUEST = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_taking);

        FloatingActionButton fab = findViewById(R.id.add_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(NoteTakingActivity.this, AddNoteActivity.class);
                startActivityForResult(it, NOTE_REQUEST);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view_note);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final NoteAdapter adapter = new NoteAdapter();
        recyclerView.setAdapter(adapter);

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                //update Recycler view
                adapter.setNotes(notes);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                noteViewModel.delete(adapter.getNoteAt(viewHolder.getAdapterPosition()));
                Toast.makeText(NoteTakingActivity.this, "NOTE DELETED", Toast.LENGTH_SHORT).show();

            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new NoteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Note note) {


                Intent intent = new Intent(NoteTakingActivity.this, AddNoteActivity.class);
                intent.putExtra(AddNoteActivity.EXTRA_ID, note.getId());
                intent.putExtra(AddNoteActivity.EXTRA_TITLE, note.getTitle());
                intent.putExtra(AddNoteActivity.EXTRA_DESCRIPTION, note.getDescription());

                startActivityForResult(intent, EDIT_NOTE_REQUEST);

            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NOTE_REQUEST && resultCode == RESULT_OK) {
            String title = data.getStringExtra(AddNoteActivity.EXTRA_TITLE);
            String desc = data.getStringExtra(AddNoteActivity.EXTRA_DESCRIPTION);

            Note note = new Note(title, desc);
            noteViewModel.insert(note);

            Log.d(TAG, "onActivityResult: Note Title "+ note.getTitle() + " Note description "+ note.getDescription()
              + " Note ID " + note.getId()

            );
            Toast.makeText(this, " NOTE SAVED ", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_NOTE_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(AddNoteActivity.EXTRA_ID, -1);

            if (id == -1) {
                Toast.makeText(this, " NOTE CAN'T BE UPDATED", Toast.LENGTH_SHORT).show();
                return;
            }

            String title = data.getStringExtra(AddNoteActivity.EXTRA_TITLE);
            String desc = data.getStringExtra(AddNoteActivity.EXTRA_DESCRIPTION);

            Note note = new Note(title, desc);
            note.setId(id);
            noteViewModel.update(note);
            Log.d(TAG, "onActivityResult: Note Title "+ note.getTitle() + " Note description "+ note.getDescription()
                    + " Note ID " + note.getId()

            );

            Toast.makeText(this, " NOTE  UPDATED", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, " Note Not Saved ", Toast.LENGTH_LONG).show();
        }
    }
}
