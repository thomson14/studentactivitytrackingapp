package com.example.studentactivitytrackingapp.habitTracker;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class HabitRepository {
    private HabitDao habitDao;
    private LiveData<List<Habit>> allHabits;
    public HabitRepository(Application application) {
        HabitDatabase database = HabitDatabase.getInstance(application);
        habitDao = database.habitDao();
        allHabits = habitDao.getAllHabits();
    }

    public void insert(Habit habit) {
        new InsertHabitAsyncTask(habitDao).execute(habit);
    }

    public void update(Habit habit) {
        new UpdateHabitAsyncTask(habitDao).execute(habit);
    }

    public void delete(Habit habit) {
        new DeleteNoteAsyncTask(habitDao).execute(habit);
    }

    public void deleteAllHabits() {

        new DeleteAllHabitAsyncTask(habitDao).execute();
    }

    public LiveData<List<Habit>> getAllHabits() {
        return allHabits;
    }

//    public List<Integer> getAllDates(Habit habit){
//        new GetDatesAsyncTask(habitDao).execute(habit);
//        return allDates;
//    }

    private static class InsertHabitAsyncTask extends AsyncTask<Habit, Void, Void> {

        private HabitDao habitDao;

        private InsertHabitAsyncTask(HabitDao habitDao) {
            this.habitDao = habitDao;
        }

        @Override
        protected Void doInBackground(Habit... habits) {
            habitDao.insert(habits[0]);
            return null;
        }
    }


    private static class UpdateHabitAsyncTask extends AsyncTask<Habit, Void, Void> {

        private HabitDao habitDao;

        private UpdateHabitAsyncTask(HabitDao habitDao) {
            this.habitDao = habitDao;
        }

        @Override
        protected Void doInBackground(Habit... habits) {
            habitDao.update(habits[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<Habit, Void, Void> {

        private HabitDao habitDao;

        private DeleteNoteAsyncTask(HabitDao habitDao) {
            this.habitDao = habitDao;
        }

        @Override
        protected Void doInBackground(Habit... habits) {
            habitDao.delete(habits[0]);
            return null;
        }
    }


    private static class DeleteAllHabitAsyncTask extends AsyncTask<Void, Void, Void> {

        private HabitDao habitDao;

        private DeleteAllHabitAsyncTask(HabitDao habitDao) {
            this.habitDao = habitDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            habitDao.deleteAllHabit();
            return null;
        }
    }

//    private static class GetDatesAsyncTask extends AsyncTask<Habit, Void, List<Integer>> {
//        private HabitDao habitDao;
//
//        private GetDatesAsyncTask(HabitDao habitDao) {
//            this.habitDao = habitDao;
//        }
//
//        @Override
//        protected List<Integer> doInBackground(Habit... habits) {
//            return habitDao.getAllDates(habits[0].getId());
//        }
//    }


}
