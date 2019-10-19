package com.example.studentactivitytrackingapp.habitTracker;

import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.studentactivitytrackingapp.R;

import java.util.ArrayList;
import java.util.List;

public class HabitAdapter extends RecyclerView.Adapter<HabitAdapter.HabitHolder> {

    private List<Habit> habits = new ArrayList<>();

    @NonNull
    @Override
    public HabitHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.habit_item,viewGroup,false);
        return new HabitHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HabitHolder habitHolder, int i) {
         Habit currentHabit = habits.get(i);
         habitHolder.habitName.setText(currentHabit.getTitle());
         habitHolder.checkedMark.setSelected(currentHabit.isStatus());
         habitHolder.reminderTime.setText(currentHabit.getReminderHour() + " : " +currentHabit.getReminderMin());
    }

    @Override
    public int getItemCount() {
        return habits.size();
    }

    public void setHabits(List<Habit> habits){
        this.habits = habits;
        notifyDataSetChanged();
    }

    public Habit getHabitAt(int position){
        return habits.get(position);
    }
    class HabitHolder extends RecyclerView.ViewHolder{
        private TextView habitName;
        private ImageView checkedMark;
        private TextView setReminderTime;
        private ImageView setReminderIcon;
        private TextView reminderTime;

        public HabitHolder(View itemView){
            super(itemView);
            habitName = itemView.findViewById(R.id.habit_name);
            checkedMark  = itemView.findViewById(R.id.checked);
            setReminderTime = itemView.findViewById(R.id.set_reminder_time);
            setReminderIcon = itemView.findViewById(R.id.set_reminder_icon);
            reminderTime = itemView.findViewById(R.id.reminder_time_text);
        }

    }
}
