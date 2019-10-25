package com.example.studentactivitytrackingapp.habitTracker;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.studentactivitytrackingapp.R;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.content.ContentValues.TAG;

public class HabitAdapter extends RecyclerView.Adapter<HabitAdapter.HabitHolder> {

    private List<Habit> habits = new ArrayList<>();
    private List<RecordHabit> allRecords = new ArrayList<>();
    Context context;
    private HabitViewModel habitViewModel;
    private RecordHabitViewModel recordHabitViewModel;

    private OnItemClickListner listner;

    private int dateToday;

    public HabitAdapter(Context context) {
        this.context = context;
        habitViewModel = ViewModelProviders.of((FragmentActivity) context).get(HabitViewModel.class);
        recordHabitViewModel = ViewModelProviders.of((FragmentActivity) context).get(RecordHabitViewModel.class);
    }

    @NonNull
    @Override
    public HabitHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.habit_item, viewGroup, false);


        Calendar c = Calendar.getInstance();
        dateToday = c.get(Calendar.DATE);

        return new HabitHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final HabitHolder habitHolder, final int i) {
        final Habit currentHabit = habits.get(i);
        //   final RecordHabit currentRecord = allRecords.get(i);
        habitHolder.habitName.setText(currentHabit.getTitle());
        habitHolder.checkedMark.setSelected(currentHabit.isStatus());
        habitHolder.reminderTime.setText(currentHabit.getReminderHour() + " : " + currentHabit.getReminderMin());
//
        habitHolder.checkedMark.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //   Log.d(TAG, "onCheckedChanged: isChecked" + isChecked + "at position "+ i + " ID " + currentHabit.getId());
                RecordHabit recordHabit = new RecordHabit(currentHabit.getTitle(), String.valueOf(System.currentTimeMillis()));

                if (isChecked) {
                    currentHabit.setStatus(true);
                    habitViewModel.update(currentHabit);

                    recordHabitViewModel.insert(recordHabit);

                    recordHabitViewModel.getAllRecords().observeForever(new Observer<List<RecordHabit>>() {
                        @Override
                        public void onChanged(List<RecordHabit> recordHabits) {
                            for (RecordHabit recordHabit : recordHabits) {
                                Log.d(TAG, "onChanged: " + "ID " + recordHabit.getTitle() + " TIME STAMP " + recordHabit.getTimeStamp());
                            }
                        }
                    });
                }
                if (!isChecked) {

                    //  recordHabitViewModel.insert(recordHabit);
                    currentHabit.setStatus(false);
                    habitViewModel.update(currentHabit);
                    Log.d(TAG, "onCheckedChanged: " + getDateFromtimestamp(recordHabitViewModel.getTimestamp(recordHabit))
                            + " : " + dateToday
                    );

                    if (getDateFromtimestamp(recordHabitViewModel.getTimestamp(recordHabit)) == dateToday) {
                        Log.d(TAG, "onCheckedChanged: SHOULD DELETE");
                        recordHabitViewModel.deleteWithTitile(recordHabit);

                    }


                }

            }
        });


    }

    private int getDateFromtimestamp(String timestamp) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(Long.parseLong(timestamp));
        return c.get(Calendar.DATE);
    }

    @Override
    public int getItemCount() {
        return habits.size();
    }

    public void setHabits(List<Habit> habits) {
        this.habits = habits;
        notifyDataSetChanged();
    }

    public Habit getHabitAt(int position) {
        return habits.get(position);
    }

    class HabitHolder extends RecyclerView.ViewHolder {
        private TextView habitName;
        private CheckBox checkedMark;
        private TextView setReminderTime;
        private ImageView setReminderIcon;
        private TextView reminderTime;

        public HabitHolder(View itemView) {
            super(itemView);
            habitName = itemView.findViewById(R.id.habit_name);
            checkedMark = itemView.findViewById(R.id.checked);
            setReminderTime = itemView.findViewById(R.id.set_reminder_time);
            setReminderIcon = itemView.findViewById(R.id.set_reminder_icon);
            reminderTime = itemView.findViewById(R.id.reminder_time_text);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listner != null && position != RecyclerView.NO_POSITION)
                        listner.onItemClick(habits.get(position));
                }
            });
        }


    }

    public interface OnItemClickListner {
        void onItemClick(Habit habit);
    }

    public void setOnItemClickListener(OnItemClickListner listener) {
        this.listner = listener;
    }
}
