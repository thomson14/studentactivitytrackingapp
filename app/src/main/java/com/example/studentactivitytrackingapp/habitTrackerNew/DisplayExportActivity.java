package com.example.studentactivitytrackingapp.habitTrackerNew;

import android.content.Intent;
import android.os.Bundle;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentactivitytrackingapp.R;

public class DisplayExportActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.export);

        Intent intent = getIntent();
        String habits = intent.getStringExtra("habits");
        String events = intent.getStringExtra("events");

        TextView textView = findViewById(R.id.export_data);
        textView.setText(
                "Habits\n" +
                Habit.csvHeader() +
                habits +
                "\n" +
                "\nEvents\n" +
                Event.csvHeader() +
                events);
    }
}
