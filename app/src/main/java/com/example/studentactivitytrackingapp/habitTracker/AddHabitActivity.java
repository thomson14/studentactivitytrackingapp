package com.example.studentactivitytrackingapp.habitTracker;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.studentactivitytrackingapp.R;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.sql.Time;
import java.util.Calendar;

public class AddHabitActivity extends AppCompatActivity {
    Context context = this;
    public static final String EXTRA_HABIT_NAME =
            "com.example.studentactivitytrackingapp.habitTracker.habitName";
    public static final String EXTRA_SELECTED_HOUR =
            "com.example.studentactivitytrackingapp.habitTracker.habitHour";
    public static final String EXTRA_SELECTED_MINUTE =
            "com.example.studentactivitytrackingapp.habitTracker.habitMinute";


    MaterialEditText habitNameEditText ;
    Button setReminderTime, saveHabit;
    TextView time ;
    private int mHour,mMinute;
    final Calendar c = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habit);
        time = findViewById(R.id.text_time);
        saveHabit = findViewById(R.id.save_habit_button);
        habitNameEditText = findViewById(R.id.editText_habit_name);
        setReminderTime = findViewById(R.id.button_reminder_time);
        setReminderTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mHour = c.get(Calendar.HOUR_OF_DAY);
               mMinute  = c.get(Calendar.MINUTE);

               TimePickerDialog timePickerDialog  = new TimePickerDialog(context,
                       new TimePickerDialog.OnTimeSetListener() {
                           @Override
                           public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                               time.setText(hourOfDay + " : "+ minute);
                               saveHabit(hourOfDay,minute);
                           }
                       },mHour,mMinute,false);
               timePickerDialog.show();
            }

            private void saveHabit(int hour,int minute) {
                String habitName = habitNameEditText.getText().toString();
                int  selectedHour = hour;
                int selectedMin = minute;


                Intent data = new Intent();
                data.putExtra(EXTRA_HABIT_NAME,habitName);
                data.putExtra(EXTRA_SELECTED_HOUR,selectedHour);
                data.putExtra(EXTRA_SELECTED_MINUTE,selectedMin);

                setResult(RESULT_OK,data);
                finish();

            }
        });
    }
}
