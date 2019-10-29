package com.example.studentactivitytrackingapp;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;

import com.example.studentactivitytrackingapp.ToDoList.ToDoListActivity;
import com.example.studentactivitytrackingapp.habitTracker.HabitTrackerActivity;
import com.example.studentactivitytrackingapp.habitTrackerNew.HabitTrackAct;
import com.example.studentactivitytrackingapp.noteTaking.NoteTakingActivity;
import com.example.studentactivitytrackingapp.studyTimer.StudyTimerActivity;
import com.shrikanthravi.customnavigationdrawer2.data.MenuItem;
import com.shrikanthravi.customnavigationdrawer2.widget.SNavigationDrawer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SNavigationDrawer sNavigationDrawer;
    Class fragmentClass;
    public static Fragment fragment;

    //our activity
    CardView Cardtodolist,cardNoteTaking,cardHabitTracker,cardStudyTimer,cardTimeTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Cardtodolist = findViewById(R.id.card_view);
        cardNoteTaking = findViewById(R.id.card_view_note);
        cardStudyTimer = findViewById(R.id.card_view_study);
        cardTimeTable  = findViewById(R.id.card_view_time_table);


        Cardtodolist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, ToDoListActivity.class);
                startActivity(intent);
            }
        });

        cardNoteTaking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NoteTakingActivity.class);
                startActivity(intent);
            }
        });

        cardStudyTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StudyTimerActivity.class);
                startActivity(intent);
            }
        });


        cardHabitTracker = findViewById(R.id.card_view_habit);
        cardHabitTracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, HabitTrackAct.class);
                startActivity(in);
            }
        });

        cardTimeTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, com.example.studentactivitytrackingapp.timetable.activities.MainActivity.class);
                startActivity(intent);
            }
        });



        sNavigationDrawer = findViewById(R.id.navigationDrawer);

        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("HOME",R.drawable.home));
        menuItems.add(new MenuItem("To Do List",R.drawable.todolistnav));
        //menuItems.add(new MenuItem("Messages",R.drawable.photo6));
        //menuItems.add(new MenuItem("Music",R.drawable.photo7));


        //then add them to navigation drawer

        sNavigationDrawer.setMenuItemList(menuItems);
        fragmentClass =  Home.class;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).replace(R.id.frameLayout, fragment).commit();
        }

        sNavigationDrawer.setOnMenuItemClickListener(new SNavigationDrawer.OnMenuItemClickListener() {
            @Override
            public void onMenuItemClicked(int position) {
                System.out.println("Position " + position);

                switch (position) {
                    case 0: {
                        fragmentClass = Home.class;
                        break;
                    }
                   case 1: {
                        fragmentClass = ToDoListActivity.class;
                        break;
                    }
                  /*  case 2: {
                        fragmentClass = MessagesFragment.class;
                        break;
                    }
                    case 3: {
                        fragmentClass = MusicFragment.class;
                        break;
                    }*/

                }

                //Listener for drawer events such as opening and closing.
                sNavigationDrawer.setDrawerListener(new SNavigationDrawer.DrawerListener() {

                    @Override
                    public void onDrawerOpened() {

                    }

                    @Override
                    public void onDrawerOpening(){

                    }

                    @Override
                    public void onDrawerClosing(){
                        System.out.println("Drawer closed");

                        try {
                            fragment = (Fragment) fragmentClass.newInstance();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        if (fragment != null) {
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).replace(R.id.frameLayout, fragment).commit();

                        }
                    }

                    @Override
                    public void onDrawerClosed() {

                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        System.out.println("State "+newState);
                    }
                });
            }
        });
    }
}
