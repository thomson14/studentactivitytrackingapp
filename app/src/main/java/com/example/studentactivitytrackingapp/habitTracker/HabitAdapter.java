package com.example.studentactivitytrackingapp.habitTracker;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.studentactivitytrackingapp.R;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class HabitAdapter extends RecyclerView.Adapter<HabitAdapter.HabitHolder> {

    private List<Habit> habits = new ArrayList<>();
    Context context;
    private  HabitViewModel habitViewModel;

    public  HabitAdapter(Context context){
        this.context = context;
        habitViewModel= ViewModelProviders.of((FragmentActivity) context).get(HabitViewModel.class);
    }

    @NonNull
    @Override
    public HabitHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.habit_item,viewGroup,false);

        return new HabitHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final HabitHolder habitHolder, final int i) {
         final Habit currentHabit = habits.get(i);
         habitHolder.habitName.setText(currentHabit.getTitle());
         habitHolder.checkedMark.setSelected(currentHabit.isStatus());
         habitHolder.reminderTime.setText(currentHabit.getReminderHour() + " : " +currentHabit.getReminderMin());

        habitHolder.checkedMark.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d(TAG, "onCheckedChanged: isChecked" + isChecked + "at position "+ i + " ID " + currentHabit.getId());

                currentHabit.setStatus(isChecked);
                habitViewModel.update(currentHabit);

            }
        });


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
        private CheckBox checkedMark;
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
